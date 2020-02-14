package org.resala.core.volunteer.Dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegistrationParamsDTO implements Serializable {
    private Long branchId;
    private Long eventId;
    private String code;
    private String phone;
}
