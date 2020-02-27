package org.resala.core.volunteer.services;

import org.resala.core.volunteer.entities.VolunteerEntity;
import org.resala.core.volunteer.repository.VolunteerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    VolunteerService(final VolunteerRepository xVolunteerRepository) {
        volunteerRepository = xVolunteerRepository;
    }

    public VolunteerEntity saveData(final VolunteerEntity volunteerEntity) {
        volunteerRepository.save(volunteerEntity);
        return volunteerEntity;
    }

    public  VolunteerEntity find(Long id){
        return volunteerRepository.findById(id).orElse(null);
    }

    public List<VolunteerEntity> getAllVolunteers() {
        return volunteerRepository.findAll();
    }



    public  List<VolunteerEntity> GetByName(String name){

        return volunteerRepository.findByName(name.toLowerCase());
    }
    public VolunteerEntity getVolunteerById(final Long id) {
        return volunteerRepository.findById(id).orElse(null);
    }

    public Boolean isVolunteerExist(String identificationNumber,String phone) {

        return  volunteerRepository.findByIdOrPhone(identificationNumber,phone) != null ? true : false;
    }

    public String findMaxCode(String firstSegment){
        Long code = volunteerRepository.findMaxCode(firstSegment);
       return code == null ? "1": code.toString();
    }

}
