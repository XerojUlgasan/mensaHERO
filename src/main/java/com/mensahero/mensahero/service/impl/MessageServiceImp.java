package com.mensahero.mensahero.service.impl;

import com.mensahero.mensahero.DTO.dashboard.DashboardCount;
import com.mensahero.mensahero.DTO.dashboard.MessagesByKey;
import com.mensahero.mensahero.DTO.dashboard.MessagesOverTime;
import com.mensahero.mensahero.DTO.messages.CreateMessage;
import com.mensahero.mensahero.Enums.DateFilters;
import com.mensahero.mensahero.Enums.MessageStatus;
import com.mensahero.mensahero.exception.ApiException;
import com.mensahero.mensahero.model.Key;
import com.mensahero.mensahero.model.Message;
import com.mensahero.mensahero.projections.messages.MessageCounterProjection;
import com.mensahero.mensahero.repository.KeyRepository;
import com.mensahero.mensahero.repository.MessageRepository;
import com.mensahero.mensahero.service.MessageService;
import jakarta.transaction.Transactional;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImp implements MessageService{
    MessageRepository messageRepository;
    KeyRepository keyRepository;

    public MessageServiceImp(MessageRepository messageRepository, KeyRepository keyRepository) {
        this.messageRepository = messageRepository;
        this.keyRepository = keyRepository;
    }

    @Override
    public List<Message> getRecipientsHistory(UUID ownerId,
                                              UUID apiId,
                                              DateFilters dateFilters,
                                              int page,
                                              int pageSize) {

        return messageRepository.findByApiId(apiId, pageSize, (page-1)*pageSize);
    }

    @Override
    public List<Message> getMessagesByRecipients(UUID ownerId,
                                                 UUID apiId,
                                                 DateFilters dateFilters,
                                                 String recipient,
                                                 MessageStatus messageStatus,
                                                 int page,
                                                 int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdAt").descending());

        return messageRepository.findByApiIdAndReceiver(apiId, recipient, pageable);
    }

    @Override
    @Transactional
    public Message createMessage(UUID ownerId,
                                 CreateMessage createMessage) {
        UUID keyId = keyRepository.findByKey(createMessage.getApiKey())
                .orElseThrow(() -> new ApiException("API KEY DOES NOT EXISTS", HttpStatus.NOT_FOUND))
                .getId();

        return messageRepository.save(new Message(createMessage.getMessage(), createMessage.getTo(), createMessage.getFrom(), keyId));
    }

    /////////////////////////////////// HELPER FUNCTIONS FOR OTHER SERVICES

    @Override
    public DashboardCount getDashboardCount(List<UUID> apiIds){ /// TOTAL COUNTERS
        MessageCounterProjection counterProjection = messageRepository.getDashboardCounters(apiIds);
        System.out.println("TOTAL MESSAGES: " + counterProjection.getTotalMessages());
        System.out.println("FAILED MESSAGES: " + counterProjection.getFailedMessages());

        return new DashboardCount(counterProjection.getTotalMessages(), counterProjection.getFailedMessages());
    }

    @Override
    public List<MessagesOverTime> getMessagesOverTime(List<UUID> apiIds, DateFilters dateFilters){ /// GRAPHS
        List<MessagesOverTime> result =
                messageRepository.getMessagesOverTime(apiIds, dateFilters.name())
                        .stream()
                        .map(r -> new MessagesOverTime(
                                ((Instant) r[0]).atOffset(ZoneOffset.UTC),
                                ((Number) r[1]).longValue()
                        ))
                        .toList();

        return result;
    }

    @Override
    public List<MessagesByKey> getMessagesByKey(List<UUID> apiIds){   /// API KEY COUNTERS
        List<Object[]> objs = messageRepository.countByApiIds(apiIds);

        List<MessagesByKey> list = new ArrayList<>();

        for (Object[] obj : objs) {
            UUID apiId = (UUID) obj[0];
            Long count = (Long) obj[1];

            list.add(new MessagesByKey(apiId, null, count));
        }

        return list;
    }

    @Override
    public List<Message> getRecentMessages(List<UUID> apiIds){  /// 5 RECENT MESSAGES
        int limit = 5;

        Pageable pageable = PageRequest.of(0, limit, Sort.by("created_at").descending());

        return messageRepository.findByApiIds(apiIds, pageable);
    }

    /////////////////////////////////// HELPER FUNCTIONS FOR OTHER SERVICES
}
