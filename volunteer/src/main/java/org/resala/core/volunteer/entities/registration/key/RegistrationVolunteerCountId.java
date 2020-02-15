package org.resala.core.volunteer.entities.registration.key;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegistrationVolunteerCountId implements Serializable {
    private Long eventId;
    private Long branchId;

    public RegistrationVolunteerCountId() {

    }

    public RegistrationVolunteerCountId(Long eventId, Long branchId) {
        this.eventId = eventId;
        this.branchId = branchId;
    }
}
