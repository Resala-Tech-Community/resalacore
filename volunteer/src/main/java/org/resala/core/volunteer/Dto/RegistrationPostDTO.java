package org.resala.core.volunteer.Dto;

import lombok.Getter;
import lombok.Setter;
import org.resala.core.volunteer.entities.GenderEnum;

import java.io.Serializable;

@Getter
@Setter
public class RegistrationPostDTO implements Serializable {
    private Long branchId;
    private Long eventId;
    private String name;
    private String phoneNumber;
    private GenderEnum gender;
    private int regionId;
    private String eMail;


}
