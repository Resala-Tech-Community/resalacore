package org.resala.core.event.entities;

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
    private Long id;
    private String eventDate;
    private String name;
    private String startTime;
    private String lastTimeToReg;
}

