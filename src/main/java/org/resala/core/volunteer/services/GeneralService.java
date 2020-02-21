

package org.resala.core.volunteer.services;

import org.resala.core.volunteer.repository.GeneralRepository;
import org.springframework.stereotype.Service;

@Service
public class GeneralService<T, E> {


    private final GeneralRepository generalRepository;

    public GeneralService(final GeneralRepository xgeneralRepository) {
        generalRepository = xgeneralRepository;
    }

    void saveVolunteerRegistrationCount(long branchId, long eventId, long count) {
        System.out.println("-------------------saveVolunteerRegistrationCount----------------------------");
        if (count > 1) {
            generalRepository.updateRegisterCount(branchId, eventId, count);
        } else {
            generalRepository.insertRegisterCount(branchId, eventId, count);

        }
    }

}


