package org.resala.core.volunteer.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.resala.core.domain.registration.RegistrationVolunteerCountEntity;
import org.resala.core.domain.registration.VolunteerRegistrationEntity;
import org.resala.core.domain.registration.key.RegistrationVolunteerCountId;
import org.resala.core.domain.registration.key.VolunteerRegistrationKey;
import org.resala.core.volunteer.repository.VolunteerRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Service
public class VolunteerRegistrationService {

    @Value("${fire.base.url}")
    private String fireBaseUrl;

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
        int count = volunteerRegistrationRepository.countByBranchIdAndEventId(branchId, eventId);
        afterSave(eventId, branchId, count);
    }

    public boolean validateAndSave(long branchId, long eventId, Long volunteerId) {
        if (isRegistered(branchId, eventId, volunteerId)) {
            return false;
        }
        save(branchId, eventId, volunteerId);
        return true;
    }

    private void afterSave(Long eventId, Long branchId, int count) {
        AsyncRestTemplate asycTemp = new AsyncRestTemplate();
        HttpEntity entity = new HttpEntity(new RegisteredCount(count));
        asycTemp.put(fireBaseUrl + branchId + "/" + eventId + ".json", entity);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class RegisteredCount {
        RegisteredCount() {

        }

        RegisteredCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        int count;

    }
}
