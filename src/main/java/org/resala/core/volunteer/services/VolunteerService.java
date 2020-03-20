package org.resala.core.volunteer.services;

import org.resala.core.domain.VolunteerEntity;
import org.resala.core.volunteer.Dto.RegistrationParamsDTO;
import org.resala.core.volunteer.mapper.VolunteerMapper;
import org.resala.core.volunteer.repository.VolunteerRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    VolunteerService(final VolunteerRepository xVolunteerRepository) {
        volunteerRepository = xVolunteerRepository;
    }

    public VolunteerEntity saveData(final VolunteerEntity volunteerEntity) {
        System.out.println("-----------------------" + volunteerRepository.save(volunteerEntity).getCode());
        // volunteerRepository.save(volunteerEntity);
        volunteerEntity.setCode(
                volunteerEntity.getId() + "-" + volunteerEntity.getGender().getValue() + "-" +
                        volunteerEntity.getName().substring(0, 2) + "-"+
                        volunteerEntity.getPhoneNumber().substring(volunteerEntity.getPhoneNumber().length()-2,volunteerEntity.getPhoneNumber().length())
        );
        return volunteerEntity;
    }

    public VolunteerEntity find(Long id) {
        return volunteerRepository.findById(id).orElse(null);
    }

    public List<VolunteerEntity> getAllVolunteers() {
        return volunteerRepository.findAll();
    }


    public List<VolunteerEntity> GetByName(String name) {

        return volunteerRepository.findByName(name.toLowerCase());
    }

    public VolunteerEntity getVolunteerById(final Long id) {
        return volunteerRepository.findById(id).orElse(null);
    }

    public Boolean isVolunteerExist(String identificationNumber, String phone) {

        return volunteerRepository.findByIdOrPhone(identificationNumber, phone) != null ? true : false;
    }

    public String findMaxCode(String firstSegment) {
        Long code = volunteerRepository.findMaxCode(firstSegment);
        return code == null ? "1" : code.toString();
    }

    public VolunteerEntity findByParams(final RegistrationParamsDTO params) {
        VolunteerEntity entity = VolunteerMapper.instance.toVolunteerEntity(params);
        Example<VolunteerEntity> example = Example.of(entity, ExampleMatcher.matching());
        return volunteerRepository.findOne(example).orElse(null);
    }

    public boolean isVolunteerExist(final String phoneNumber, final String idNumber, final String email) {
        Example<VolunteerEntity> modelMatcher = getVolunteerEntityExample(phoneNumber, email);

        return volunteerRepository.exists(modelMatcher);
    }

    public VolunteerEntity findAny(final String phoneNumber, final String idNumber, final String email) {
        Example<VolunteerEntity> modelMatcher = getVolunteerEntityExample(phoneNumber, email);
        return volunteerRepository.findOne(modelMatcher).orElse(null);
    }

    private Example<VolunteerEntity> getVolunteerEntityExample(String phoneNumber, String email) {
        VolunteerEntity entity = new VolunteerEntity();
        entity.setPhoneNumber(phoneNumber);
        entity.setEMail(email);
        return Example.of(entity, ExampleMatcher.matchingAny());
    }

}
