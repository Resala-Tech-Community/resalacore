package org.resala.core.volunteer.repository;

import org.resala.core.volunteer.entities.registration.VolunteerRegistrationEntity;
import org.resala.core.volunteer.entities.registration.key.VolunteerRegistrationKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRegistrationRepository extends CrudRepository<VolunteerRegistrationEntity, VolunteerRegistrationKey> {

    long countByBranchIdAndEventId(long branchId, Long eventId);
}
