package com.genie.Venue_Mangamnet.controller;

import com.genie.Venue_Mangamnet.entity.ClubEvent;
import com.genie.Venue_Mangamnet.service.ClubEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/club")
@PreAuthorize("hasAnyRole('ADMIN', 'CLUB_MEMBER')")
public class ClubEventController {

    @Autowired
    private ClubEventService clubEventService;

    @PostMapping("/post-event")
    public ClubEvent postEvent(@RequestBody ClubEvent clubEvent){
        return clubEventService.addNewEvent(clubEvent);
    }

    @GetMapping("/get-event")
    public ClubEvent getEvent(@RequestBody String event_name) {
        return clubEventService.getEventByEventName(event_name);
    }
}
