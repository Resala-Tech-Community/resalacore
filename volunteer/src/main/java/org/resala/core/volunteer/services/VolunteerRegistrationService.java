package org.resala.core.volunteer.services;

import org.resala.core.volunteer.entities.registration.RegistrationVolunteerCountEntity;
import org.resala.core.volunteer.entities.registration.VolunteerRegistrationEntity;
import org.resala.core.volunteer.entities.registration.key.VolunteerRegistrationKey;
import org.resala.core.volunteer.repository.VolunteerRegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public class VolunteerRegistrationService {
    private final VolunteerRegistrationRepository volunteerRegistrationRepository;
/*
    private final GeneralService<RegistrationVolunteerCountEntity> generalService;
*/

    VolunteerRegistrationService(final VolunteerRegistrationRepository repository/*, final GeneralService<RegistrationVolunteerCountEntity> generalService*/) {
        volunteerRegistrationRepository = repository;
      //  this.generalService = generalService;
    }

    public boolean isRegistered(long branchId, long eventId, Long volunteerId) {
        return volunteerRegistrationRepository.existsById(new VolunteerRegistrationKey(eventId, branchId, volunteerId));
    }

    public void save(long branchId, long eventId, Long volunteerId) {
        VolunteerRegistrationEntity volunteerRegistrationEntity = new VolunteerRegistrationEntity(volunteerId, branchId, eventId);
        volunteerRegistrationRepository.save(volunteerRegistrationEntity);
        afterSave(eventId, branchId);
    }

    private void afterSave(Long eventId, Long branchId) {
       // generalService.saveVolunteerRegistrationCount(branchId, eventId);
    }

}
