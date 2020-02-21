package org.resala.core.domain.registration.key;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VolunteerRegistrationKey implements Serializable {
    private Long eventId;
    private Long branchId;
    private Long volunteerId;

    public VolunteerRegistrationKey() {

    }

    public VolunteerRegistrationKey(Long eventId, Long branchId, Long volunteerId) {
        this.eventId = eventId;
        this.branchId = branchId;
        this.volunteerId = volunteerId;
    }
}
