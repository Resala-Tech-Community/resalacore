package org.resala.core.volunteer.services;

import org.resala.core.domain.registration.RegistrationVolunteerCountEntity;
import org.resala.core.domain.registration.VolunteerRegistrationEntity;
import org.resala.core.domain.registration.key.RegistrationVolunteerCountId;
import org.resala.core.domain.registration.key.VolunteerRegistrationKey;
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
        return volunteerRegistrationRepository.existsById(new VolunteerRegistrationKey(eventId, branchId, volunteerId));
    }

    public void save(long branchId, long eventId, Long volunteerId) {

        VolunteerRegistrationEntity volunteerRegistrationEntity = new VolunteerRegistrationEntity(volunteerId, branchId, eventId);
        VolunteerRegistrationEntity entity = volunteerRegistrationRepository.save(volunteerRegistrationEntity);
        long count = volunteerRegistrationRepository.countByBranchIdAndEventId(branchId, eventId);
        afterSave(eventId, branchId, count);
    }

    public boolean validateAndSave(long branchId, long eventId, Long volunteerId) {
        if (isRegistered(branchId, eventId, volunteerId)) {
            return false;
        }
        save(branchId, eventId, volunteerId);
        return true;
    }

    private void afterSave(Long eventId, Long branchId, long count) {
        generalService.saveVolunteerRegistrationCount(branchId, eventId, count);
    }

}
