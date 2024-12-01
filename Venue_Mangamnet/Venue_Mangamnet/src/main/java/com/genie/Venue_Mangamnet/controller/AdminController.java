package com.genie.Venue_Mangamnet.controller;

import com.genie.Venue_Mangamnet.entity.ClubEvent;
import com.genie.Venue_Mangamnet.entity.EventStatus;
import com.genie.Venue_Mangamnet.service.ClubEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private ClubEventService clubEventService;

    @GetMapping("/dashboard")
    public List<ClubEvent> getAdminDashBoard() {
        return clubEventService.findAllEvents();
    }
    @PutMapping("/approve-event/{eventId}")
    public String approveEvent(@PathVariable Long eventId) {
        String response = clubEventService.approveEvent(eventId);
        return response;
    }

    @PutMapping("/decline-event/{eventId}")
    public String declineEvent(@PathVariable Long eventId) {
        String response = clubEventService.declineEvent(eventId);
        return response;
    }

    @GetMapping("/approved-events")
    public List<ClubEvent> getApprovedEvents() {
        return clubEventService.findEventByStatus(EventStatus.APPROVED);
    }

    @GetMapping("/pending-events")
    public List<ClubEvent> getPendingEvents() {
        return clubEventService.findEventByStatus(EventStatus.PENDING);
    }

    @GetMapping("/declined-events")
    public List<ClubEvent> getDeclinedEvents() {
        return clubEventService.findEventByStatus(EventStatus.DECLINED);
    }
}
