/*

package org.resala.core.volunteer.services;

import org.resala.core.volunteer.repository.GeneralRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralService<T> {


    private final GeneralRepository generalRepository;

    public GeneralService(final GeneralRepository xgeneralRepository) {
        generalRepository = xgeneralRepository;
    }

    public void save(final T entity) {
        generalRepository.save(entity);
    }

    public List<T> getAllVolunteers() {
        return generalRepository.findAll();
    }

    public Object getVolunteerById(final Long id) {
        return generalRepository.findById(id).get();
    }

    public void saveVolunteerRegistrationCount(long branchId, long eventId) {
        generalRepository.insertCountIntoSelectCount(branchId, eventId);
    }

}

*/
