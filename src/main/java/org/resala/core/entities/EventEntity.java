package org.resala.core.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

@Entity
@Getter
@Setter

public class EventEntity {
    @Id
    private Long eventID;
    private String eventDate;
    private String name;
    private String branch;
    private String activity;
//    private Time startTime;
//    private Time endTime;
}
