package org.resala.core.volunteer.services;

import org.resala.core.volunteer.entities.registration.RegistrationVolunteerCountEntity;
import org.resala.core.volunteer.entities.registration.VolunteerRegistrationEntity;
import org.resala.core.volunteer.entities.registration.key.RegistrationVolunteerCountId;
import org.resala.core.volunteer.entities.registration.key.VolunteerRegistrationKey;
import org.resala.core.volunteer.repository.VolunteerRegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public class VolunteerRegistrationService {
    private final VolunteerRegistrationRepository volunteerRegistrationRepository;

    private final GeneralService<RegistrationVolunteerCountEntity, RegistrationVolunteerCountId> generalService;


    VolunteerRegistrationService(final VolunteerRegistrationRepository repository, final GeneralService generalService) {
        volunteerRegistrationRepository = repository;
        this.generalService = generalService;
    }

    public boolean isRegistered(long branchId, long eventId, Long volunteerId) {
        return volunteerRegistrationRepository.existsById(new VolunteerRegistrationKey(2l, branchId, volunteerId));
    }

    public void save(long branchId, long eventId, Long volunteerId) {

        VolunteerRegistrationEntity volunteerRegistrationEntity = new VolunteerRegistrationEntity(volunteerId, branchId, eventId);
        VolunteerRegistrationEntity entity = volunteerRegistrationRepository.save(volunteerRegistrationEntity);
        afterSave(eventId, branchId);
    }

    public boolean validateAndSave(long branchId, long eventId, Long volunteerId) {
        if (isRegistered(branchId, eventId, volunteerId)) {
            return false;
        }
        save(branchId, eventId, volunteerId);
        return true;
    }

    private void afterSave(Long eventId, Long branchId) {
        generalService.saveVolunteerRegistrationCount(branchId, eventId);
    }

}
