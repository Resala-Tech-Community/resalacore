package org.resala.core.event.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.convert.DurationFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Convert;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class EventReqParamDto implements Serializable {

    private String branch;
    private String date;
    private String id;
}
