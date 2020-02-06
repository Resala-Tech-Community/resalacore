package org.resala.core.volunteer.controllers;

import org.resala.core.volunteer.Dto.EditVolunteerDto;
import org.resala.core.volunteer.mapper.VolunteerMapper;
import org.resala.core.volunteer.services.VolunteerService;
import org.resala.core.volunteer.repository.GeneralRepository;
import org.resala.core.volunteer.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

@RestController
@RequestMapping("volunteer")
public class VolunteerController {

    private final VolunteerService volunteerService;
    private final GeneralRepository<RegionEntity> regionEntityGeneralService;
    private final GeneralRepository<NetworkTypeEntity> networkTypeEntityGeneralService;
    private final GeneralRepository<UniversitySpecializationEntity> universitySpecializationEntityService;
    private final GeneralRepository<VolunteerTypeEntity> volunteerTypeEntityService;

    public VolunteerController(final VolunteerService service, GeneralRepository<RegionEntity> regionEntityGeneralService, GeneralRepository<NetworkTypeEntity> networkTypeEntityGeneralService, GeneralRepository<UniversitySpecializationEntity> universitySpecializationEntityService, GeneralRepository<VolunteerTypeEntity> volunteerTypeEntityService) {
        volunteerService = service;
        this.regionEntityGeneralService = regionEntityGeneralService;
        this.networkTypeEntityGeneralService = networkTypeEntityGeneralService;
        this.universitySpecializationEntityService = universitySpecializationEntityService;
        this.volunteerTypeEntityService = volunteerTypeEntityService;
    }

    @GetMapping("all")
    public ResponseEntity getVolunteers() {
        return new ResponseEntity<>(volunteerService.getAllVolunteers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getVolunteersByID(@PathVariable long id) {
        VolunteerEntity volunteer = volunteerService.getVolunteerById(id);
        if (volunteer == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(volunteer, HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}")
    public ResponseEntity updateVolunteer(@PathVariable long id, @RequestBody EditVolunteerDto editVolunteerDto) {
        VolunteerEntity volunteerEntity = volunteerService.find(id);

        if (volunteerEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            //TODO: need refactory
            volunteerEntity.setNetworkType(getNetworkTypeEntity(editVolunteerDto));
            volunteerEntity.setUniversitySpecialization(getUniversitySpecializationEntity(editVolunteerDto));
            volunteerEntity.setVolunteerType(getVolunteerTypeEntity(editVolunteerDto));
            volunteerEntity.setRegionEntity(getRegionEntity(editVolunteerDto));

            VolunteerMapper.instance.updateVolunteerFromDto(volunteerEntity, editVolunteerDto);

//            volunteerEntity.setNetworkType(networkTypeEntityGeneralService.findById(editVolunteerDto.networkTypeId).get());
//            volunteerEntity.setUniversitySpecialization(universitySpecializationEntityService.findById(editVolunteerDto.universitySpecializationId).get());
//            volunteerEntity.setRegionEntity(regionEntityGeneralService.findById(editVolunteerDto.regionId).get());
//            volunteerEntity.setVolunteerType(volunteerTypeEntityService.findById(editVolunteerDto.volunteerTypeId).get());

            Set<ConstraintViolation<VolunteerEntity>> constraintViolations = getConstraintViolations(volunteerEntity);
            if (constraintViolations.size() > 0) {
                return new ResponseEntity<>(
                        constraintViolations.stream().findFirst().get().getMessage(),
                        HttpStatus.BAD_REQUEST);

            }
            volunteerService.saveData(volunteerEntity);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("new")
    public ResponseEntity addVolunteer(@RequestBody VolunteerEntity volunteerEntity) throws ParseException {

        Set<ConstraintViolation<VolunteerEntity>> constraintViolations = getConstraintViolations(volunteerEntity);

        if (constraintViolations.size() > 0) {
            return new ResponseEntity<>(
                    constraintViolations.stream().findFirst().get().getMessage(),
                    HttpStatus.BAD_REQUEST);
        }


        if (volunteerService.isVolunteerExist(volunteerEntity.getIdentificationNumber(), volunteerEntity.getPhoneNumber())) {
            return new ResponseEntity<>(
                    "There is another Volunteer with the same data.",
                    HttpStatus.BAD_REQUEST);
        }

//        check if all mandatory fields are add
        if (volunteerEntity.getRegionEntity() != null && volunteerEntity.getRegionEntity().getId() != 0) {
            Date joinDate = geJointDate(volunteerEntity);
            String codePatt = volunteerEntity.getGender().getValue() + new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).format(joinDate);
            codePatt += volunteerService.findMaxCode(codePatt);
            volunteerEntity.setCode(codePatt);

            volunteerService.saveData(volunteerEntity);
            return new ResponseEntity<>(volunteerEntity, HttpStatus.CREATED);
        } else
            return new ResponseEntity<>("Please enter Region", HttpStatus.BAD_REQUEST);

    }

//    @PostMapping("Add")
//    public ResponseEntity addVolunteer(@RequestBody volunteerDto volunteer) {
//        if(volunteerService.isVolunteerExist(volunteer.identificationNumber,volunteer.phoneNumber)){
//            return new ResponseEntity<>(
//                    "There is another Volunteer with the same data.",
//                    HttpStatus.BAD_REQUEST);
//        }
//        VolunteerEntity volunteerEntity = new VolunteerEntity(volunteer.name,volunteer.joinDate,volunteer.notes,
//                            volunteer.identificationNumber, volunteer.miniCamp,volunteer.tshirt,
//                            volunteer.gender,volunteer.phoneNumber,volunteer.birthDate,
//                            volunteer.networkTypeId,volunteer.universitySpecializationId,volunteer.volunteerTypeId);
////        volunteer code will be represented by :
////        first digit : gender
////        four digit for join year two digit for month and two digit for day and then max number in database
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        String pattern = volunteer.gender.getValue() + simpleDateFormat.format(volunteer.joinDate);
//        volunteer.code = pattern + volunteerService.findMaxCode(pattern);
//
//        volunteerEntity.setCode(volunteer.code);
//        volunteerService.saveData(volunteerEntity);
//        return new ResponseEntity<>(volunteer,HttpStatus.OK);
//    }

    private Set<ConstraintViolation<VolunteerEntity>> getConstraintViolations(VolunteerEntity volunteerEntity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(volunteerEntity);
    }

    private RegionEntity getRegionEntity(@RequestBody EditVolunteerDto editVolunteerDto) {
        RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(editVolunteerDto.regionId);
        return regionEntity;
    }

    private VolunteerTypeEntity getVolunteerTypeEntity(@RequestBody EditVolunteerDto editVolunteerDto) {
        VolunteerTypeEntity volunteerTypeEntity = new VolunteerTypeEntity();
        volunteerTypeEntity.setId(editVolunteerDto.volunteerTypeId);
        return volunteerTypeEntity;
    }

    private UniversitySpecializationEntity getUniversitySpecializationEntity(@RequestBody EditVolunteerDto editVolunteerDto) {
        UniversitySpecializationEntity universitySpecializationEntity = new UniversitySpecializationEntity();
        universitySpecializationEntity.setId(editVolunteerDto.universitySpecializationId);
        return universitySpecializationEntity;
    }

    private NetworkTypeEntity getNetworkTypeEntity(@RequestBody EditVolunteerDto editVolunteerDto) {
        NetworkTypeEntity networkTypeEntity = new NetworkTypeEntity();
        networkTypeEntity.setId(editVolunteerDto.networkTypeId);
        return networkTypeEntity;
    }

    private Date geJointDate(@RequestBody VolunteerEntity volunteerEntity) throws ParseException {
        Date joinDate = new Date();
        if (!volunteerEntity.getJoinDate().isEmpty()) {
            joinDate = new SimpleDateFormat("dd/MM/yyyy").parse(volunteerEntity.getJoinDate());
        }
        return joinDate;
    }
}
