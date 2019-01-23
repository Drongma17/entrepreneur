package com.thonglam.entrepreneur.dto;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Event_Table")
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String eventName;
    private String eventVanue;
    private Date eventDate;
    private String organizer;
    private String aboutEvent;


    private String fileName;
    private Date createDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private List<Guest> chiefGuest;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrepre_id")
    private List<Entrepreneurs> entrepreneurs;


    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventVanue() {
        return eventVanue;
    }

    public void setEventVanue(String eventVanue) {
        this.eventVanue = eventVanue;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public List<Guest> getChiefGuest() {
        return chiefGuest;
    }

    public void setChiefGuest(List<Guest> chiefGuest) {
        this.chiefGuest = chiefGuest;
    }

    public List<Entrepreneurs> getEntrepreneurs() {
        return entrepreneurs;
    }

    public void setEntrepreneurs(List<Entrepreneurs> entrepreneurs) {
        this.entrepreneurs = entrepreneurs;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAboutEvent() {
        return aboutEvent;
    }

    public void setAboutEvent(String aboutEvent) {
        this.aboutEvent = aboutEvent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", eventVanue='" + eventVanue + '\'' +
                ", eventDate=" + eventDate +
                ", organizer='" + organizer + '\'' +
                ", createDate='" + createDate + '\'' +
                ", chiefGuest=" + chiefGuest +
                ", fileName=" + fileName +
                ", aboutEvent=" + aboutEvent +
                ", entrepreneurs=" + entrepreneurs +
                '}';
    }
}
