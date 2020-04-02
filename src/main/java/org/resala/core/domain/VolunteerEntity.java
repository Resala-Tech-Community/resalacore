package org.resala.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Getter
@Setter
public class VolunteerEntity implements Serializable {

    public VolunteerEntity() {
    }

    public VolunteerEntity(String name, String joinDate, String notes, String identificationNumber,
                           Boolean miniCamp, Boolean tshirt, GenderEnum gender, String phoneNumber, String birthDate,
                           Long networkTypeId, Long universitySpecializationId,
                           Long volunteerTypeId) {

        this.name = name;
        this.joinDate = joinDate;
        this.notes = notes;
        this.identificationNumber = identificationNumber;
        this.miniCamp = miniCamp;
        this.tshirt = tshirt;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.networkType = new NetworkTypeEntity();
        this.networkType.setId(networkTypeId);
        this.UniversitySpecialization = new UniversitySpecializationEntity();
        this.UniversitySpecialization.setId(universitySpecializationId);
        this.volunteerType = new VolunteerTypeEntity();
        this.volunteerType.setId(volunteerTypeId);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Formula("CONCAT(id, '-', gender, '-', SUBSTRING(name,1,2), '-', SUBSTRING(phoneNumber,10,2))")
    private String code;
    @NotEmpty(message = "Please enter Name")
    private String name;
    private String joinDate;
    private String notes;
    private String eMail;
    @Size(max = 14, min = 14, message = "{identification Number invalid}")
    private String identificationNumber;
    private Boolean miniCamp;
    private Boolean tshirt;
    private GenderEnum gender;
    @NotEmpty(message = "Please enter Phone Number")
    private String phoneNumber;
    private String birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private NetworkTypeEntity networkType;
    @ManyToOne(fetch = FetchType.LAZY)
    private UniversitySpecializationEntity UniversitySpecialization;
    @ManyToOne(fetch = FetchType.LAZY)
    private VolunteerTypeEntity volunteerType;

    @ManyToOne(fetch = FetchType.LAZY)
    private RegionEntity regionEntity;

}
