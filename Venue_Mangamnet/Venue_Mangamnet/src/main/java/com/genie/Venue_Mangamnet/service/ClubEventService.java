package com.genie.Venue_Mangamnet.service;

import com.genie.Venue_Mangamnet.entity.ClubEvent;
import com.genie.Venue_Mangamnet.entity.EventStatus;
import com.genie.Venue_Mangamnet.repo.ClubEventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubEventService {

    @Autowired
    private ClubEventRepo clubEventRepo;

    public ClubEvent addNewEvent(ClubEvent clubEvent) {
        clubEvent.setApprovalStatus(EventStatus.PENDING);
        return clubEventRepo.save(clubEvent);
    }

    public ClubEvent getEventByEventName(String eventName) {
        if( clubEventRepo.findByEventName(eventName).isPresent()) {
            return clubEventRepo.findByEventName(eventName).get();
        }
        return null;

    }

    public String approveEvent(Long eventId) {
        ClubEvent clubEvent = clubEventRepo.findById(eventId).orElse(null);
        if(clubEvent != null) {
            clubEvent.setApprovalStatus(EventStatus.APPROVED);

            clubEventRepo.save(clubEvent);
            return "Event has been approved";
        }

        return "Event not present";
    }

    public String declineEvent(Long eventId) {
        ClubEvent clubEvent = clubEventRepo.findById(eventId).orElse(null);

        if(clubEvent != null) {
            clubEvent.setApprovalStatus(EventStatus.DECLINED);
            clubEventRepo.save(clubEvent);
            return "Event status is Now Declined";
        }
        return "Event Not Found";
    }


    public List<ClubEvent> findAllEvents(){
        return clubEventRepo.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public List<ClubEvent> findEventByStatus(EventStatus status){
        return clubEventRepo.findByApprovalStatus(status);
    }

    public List<ClubEvent> findByClubName(String name, EventStatus eventStatus) {
        return clubEventRepo.findByClubNameAndApprovalStatus(name, eventStatus);
    }
}
