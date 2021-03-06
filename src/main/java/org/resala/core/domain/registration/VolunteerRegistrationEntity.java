package org.resala.core.domain.registration;

import lombok.Getter;
import lombok.Setter;
import org.resala.core.domain.registration.key.VolunteerRegistrationKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Getter
@Setter
@IdClass(VolunteerRegistrationKey.class)
public class VolunteerRegistrationEntity implements Serializable {


    @Id
    private Long volunteerId;
    @Id
    private Long branchId;
    @Id
    private Long eventId;

    public VolunteerRegistrationEntity() {

    }

    public VolunteerRegistrationEntity(Long volunteerId, Long branchId, Long eventId) {
        this.volunteerId = volunteerId;
        this.branchId = branchId;
        this.eventId = eventId;

    }
}
