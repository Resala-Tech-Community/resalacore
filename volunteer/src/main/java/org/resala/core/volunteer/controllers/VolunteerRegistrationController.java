package org.resala.core.volunteer.controllers;

import org.resala.core.volunteer.Dto.RegistrationParamsDTO;
import org.resala.core.volunteer.Dto.RegistrationPostDTO;
import org.resala.core.volunteer.entities.VolunteerEntity;
import org.resala.core.volunteer.mapper.VolunteerMapper;
import org.resala.core.volunteer.services.VolunteerRegistrationService;
import org.resala.core.volunteer.services.VolunteerService;
import org.resala.core.volunteer.utils.ValidationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import java.util.Objects;
import java.util.Set;

@RestController()
@RequestMapping("/registration")
public class VolunteerRegistrationController {

    private final VolunteerRegistrationService volunteerRegistrationService;
    private final VolunteerService volunteerService;

    VolunteerRegistrationController(VolunteerRegistrationService vrService, VolunteerService volunteer) {
        volunteerRegistrationService = vrService;
        volunteerService = volunteer;
    }

    @PostMapping("volunteer")
    public ResponseEntity RegisterVolunteerByCode(RegistrationParamsDTO parms) {

        if (isValidCode(parms)) {
            return ResponseEntity.badRequest().build();
        }

        VolunteerEntity volunteerEntity = volunteerService.findByCode(parms.getCode());
        ResponseEntity invalidResponseEntity = getInvalidResponseEntity(parms, volunteerEntity);
        if (invalidResponseEntity != null) {
            return invalidResponseEntity;
        }

        volunteerRegistrationService.save(parms.getBranchId(), parms.getEventId(), volunteerEntity.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("phone")
    public ResponseEntity RegisterVolunteerByPhone(RegistrationParamsDTO parms) {

        if (isValidPhone(parms)) {
            return ResponseEntity.badRequest().build();
        }

        VolunteerEntity volunteerEntity = volunteerService.findByPhoneNumber(parms.getPhone());
        ResponseEntity invalidResponseEntity = getInvalidResponseEntity(parms, volunteerEntity);
        if (invalidResponseEntity != null) {
            return invalidResponseEntity;
        }

        volunteerRegistrationService.save(parms.getBranchId(), parms.getEventId(), volunteerEntity.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity RegisterVolunteer(RegistrationPostDTO body) {
        Set<ConstraintViolation<RegistrationPostDTO>> viloations = ValidationUtils.getConstraintViolations(body);
        if (!viloations.isEmpty()) {
            return ResponseEntity.badRequest().body(viloations.stream().findFirst().get().getMessage());
        }
        VolunteerEntity volunteerEntity = VolunteerMapper.instance.toVolunteerEntitiy(body);
        volunteerService.saveData(volunteerEntity);
        boolean isRegistered = volunteerRegistrationService.isRegistered(body.getBranchId(), body.getEventId(), volunteerEntity.getId());
        if (isRegistered) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        volunteerRegistrationService.save(body.getBranchId(), body.getEventId(), volunteerEntity.getId());
        return ResponseEntity.ok().build();
    }

    private boolean isValidParams(RegistrationParamsDTO parms) {
        return Objects.isNull(parms) || Objects.isNull(parms.getBranchId()) || Objects.isNull(parms.getEventId());
    }

    private boolean isValidCode(RegistrationParamsDTO parms) {
        return isValidParams(parms) || Objects.isNull(parms.getCode());
    }

    private boolean isValidPhone(RegistrationParamsDTO parms) {
        return isValidParams(parms) || Objects.isNull(parms.getPhone());
    }

    private ResponseEntity getInvalidResponseEntity(RegistrationParamsDTO parms, VolunteerEntity volunteerEntity) {
        if (Objects.isNull(volunteerEntity)) {
            return ResponseEntity.badRequest().build();
        }

        boolean isRegistered = volunteerRegistrationService.isRegistered(parms.getBranchId(), parms.getEventId(), volunteerEntity.getId());
        if (isRegistered) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return null;
    }
}
