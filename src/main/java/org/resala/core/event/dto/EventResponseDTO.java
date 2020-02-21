package org.resala.core.event.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EventResponseDTO implements Serializable {
    private long branchId;
    private long eventId;
    private String eventDate;
    private String name;
    private String startTime;
    private String lastTimeToReg;
}
