package org.resala.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Getter
@Setter

public class EventEntity {
    @Id
    private Long id;
    private Long eventDate;
    private String name;
    private String startTime;
    private String lastTimeToReg;
}

