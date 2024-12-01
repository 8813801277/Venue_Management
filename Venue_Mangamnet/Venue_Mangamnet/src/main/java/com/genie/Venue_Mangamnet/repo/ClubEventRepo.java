package com.genie.Venue_Mangamnet.repo;
import java.util.*;
import com.genie.Venue_Mangamnet.entity.ClubEvent;
import com.genie.Venue_Mangamnet.entity.EventStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubEventRepo extends JpaRepository<ClubEvent, Long> {

    Optional<ClubEvent> findByEventName(String EventName);
    List<ClubEvent>findAll(Sort sort);
    List<ClubEvent> findByApprovalStatus(EventStatus eventStatus);
    List<ClubEvent> findByClubNameAndApprovalStatus(String clubName, EventStatus eventStatus);

}
