package org.resala.core.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class VolunteerEntity {

    @Id
    private Long id;
    private String name;
    private String joinDate;
    private String notes;
    private String identificationNumber;
    private Boolean miniCamp;
    private Boolean tshirt;
    private GenderEnum gender;
    private String phoneNumber;
    private String birthDate;

    @ManyToOne
    @JoinColumn
    private NetworkTypeEntity networkType;
    @ManyToOne
    private UniversitySpecializationEntity UniversitySpecialization;
    @ManyToOne
    private VolunteerTypeEntity volunteerType;

}
