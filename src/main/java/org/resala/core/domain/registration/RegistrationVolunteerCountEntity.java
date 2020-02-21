package org.resala.core.domain.registration;

import lombok.Getter;
import lombok.Setter;
import org.resala.core.domain.registration.key.RegistrationVolunteerCountId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Getter
@Setter
@IdClass(RegistrationVolunteerCountId.class)
public class RegistrationVolunteerCountEntity implements Serializable {

    @Id
    private Long eventId;
    @Id
    private Long branchId;
    private int count;
}
