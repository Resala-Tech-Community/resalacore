package org.resala.core.services;

import org.resala.core.entities.VolunteerEntity;
import org.resala.core.repository.VolunteerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    VolunteerService(final VolunteerRepository xVolunteerRepository) {
        volunteerRepository = xVolunteerRepository;
    }

    public void saveData(final VolunteerEntity volunteerEntity) {
        volunteerRepository.save(volunteerEntity);
    }

    public List<VolunteerEntity> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public VolunteerEntity getVolunteerById(final Long id) {
        return volunteerRepository.findById(id).get();
    }

}
