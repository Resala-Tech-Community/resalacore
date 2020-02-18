package org.resala.core.volunteer.Dto;

import java.security.Timestamp;

import org.resala.core.volunteer.entities.GenderEnum;

public class EditVolunteerDto {
    public String name;
    public Timestamp joinDate;
    public String notes;
    public String identificationNumber;
    public Boolean miniCamp;
    public Boolean tshirt;
    public GenderEnum gender;
    public String phoneNumber;
    public Timestamp birthDate;
    public Long networkTypeId;
    public Long universitySpecializationId;
    public Long volunteerTypeId;
    public  Long regionId;
}
