package org.resala.core.volunteer.controllers;


import org.resala.core.volunteer.Dto.EditUniversityDto;
import org.resala.core.volunteer.Dto.EditVolunteerDto;
import org.resala.core.volunteer.entities.*;
import org.resala.core.volunteer.mapper.UniversityMapper;
import org.resala.core.volunteer.repository.GeneralRepository;
import org.resala.core.volunteer.services.UniversityService;
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
import java.util.List;
import java.util.Locale;
import java.util.Set;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("University")
public class UniversityController {

    private final UniversityService universityService;
    private final GeneralRepository<CollageEntity> collageEntityGeneralRepository;


    public UniversityController(UniversityService universityService, GeneralRepository<CollageEntity> collageEntityGeneralRepository) {
        this.universityService = universityService;
        this.collageEntityGeneralRepository = collageEntityGeneralRepository;
    }

    @GetMapping("all")
    public ResponseEntity getUniversity() {
        return new ResponseEntity<>(universityService.getAllUniversity(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getUniversityByID(@PathVariable long id) {
        UniversityEntitiy university = universityService.getUniversityById(id);
        if (university == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(university, HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}")
    public ResponseEntity updateUniversity(@PathVariable long id, @RequestBody EditUniversityDto editUniversityDto) {
        UniversityEntitiy universityEntitiy = universityService.find(id);

        if (universityEntitiy == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            //TODO: need refactory
           // universityEntitiy.setCollage(getCollage(editUniversityDto));
            UniversityMapper.instance.updateUniversityMapper(universityEntitiy, editUniversityDto);

            Set<ConstraintViolation<UniversityEntitiy>> constraintViolations = getUniversityByID(universityEntitiy);
            if (constraintViolations.size() > 0) {
                return new ResponseEntity<>(
                        constraintViolations.stream().findFirst().get().getMessage(), HttpStatus.BAD_REQUEST);

            }
            universityService.saveData(universityEntitiy);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    private Set<ConstraintViolation<UniversityEntitiy>> getUniversityByID(UniversityEntitiy universityEntitiy) {
        return null;
    }

    private void getCollage(EditUniversityDto editUniversityDto) {
    }
    @PostMapping("new")
    public ResponseEntity addVolunteer(@RequestBody UniversityEntitiy universityEntitiy) throws ParseException {

        Set<ConstraintViolation<UniversityEntitiy>> constraintViolations = getUniversityByID(universityEntitiy);

        if (constraintViolations.size() > 0) {
            return new ResponseEntity<>(
                    constraintViolations.stream().findFirst().get().getMessage(),
                    HttpStatus.BAD_REQUEST);
        }


        if (universityService.isUniversityExist(universityEntitiy.getName(), universityEntitiy.getName())) {
            return new ResponseEntity<>(
                    "There is another university with the same data.",
                    HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private Set<ConstraintViolation<UniversityEntitiy>> getConstraintViolations(UniversityEntitiy universityEntitiy) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(universityEntitiy);
    }

    private CollageEntity getCollageEntity (@RequestBody EditUniversityDto editUniversityDto) {
        CollageEntity collageEntity = new CollageEntity();
        collageEntity.setId(editUniversityDto.collage);
        return collageEntity;
    }
}
