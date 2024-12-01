package com.genie.Venue_Mangamnet.entity;

import jakarta.persistence.*;

import javax.naming.spi.StateFactory;
import java.util.Date;

@Entity
@Table(name = "club_event_details")
public class ClubEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Column(nullable = false)
    private String clubName;

    @Column(unique = true, nullable = false)
    private String eventName;

    @Column(nullable = false)
    private String eventType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date eventDate;

    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String venueName;


    @Enumerated(EnumType.STRING)
    EventStatus approvalStatus = EventStatus.PENDING;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @PrePersist
    public void onCreate(){ //automatically called when data is stored in database and set createdAt to current date
        this.createdAt = new Date();
    }

    public ClubEvent() {

    }

    public ClubEvent(Long eventId, String clubName, String eventName, String eventType, Date eventDate, String description, String venueName, EventStatus approvalStatus, Long adminId) {
        this.eventId = eventId;
        this.clubName = clubName;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.description = description;
        this.venueName = venueName;
        this.approvalStatus = approvalStatus;

    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public EventStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(EventStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "ClubEvent{" +
                "createAt=" + createdAt +
                ", approvalStatus=" + approvalStatus +
                ", venueName='" + venueName + '\'' +
                ", description='" + description + '\'' +
                ", eventDate=" + eventDate +
                ", eventType='" + eventType + '\'' +
                ", eventName='" + eventName + '\'' +
                ", clubName='" + clubName + '\'' +
                ", eventId=" + eventId +
                '}';
    }
}
