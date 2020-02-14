package org.resala.core.event.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EventReqParamDto implements Serializable {

    private String branchId;
    private String eventDate;

}
