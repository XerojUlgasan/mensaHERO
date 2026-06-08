package com.mensahero.mensahero.controller;

import com.mensahero.mensahero.Enums.DateFilters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @GetMapping
    public void getDashboardData(UUID ownerId, DateFilters dateFilters) {
        return;
    }
}
