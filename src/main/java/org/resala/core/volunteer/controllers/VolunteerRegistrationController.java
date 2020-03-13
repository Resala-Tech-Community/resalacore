package org.resala.core.volunteer.controllers;

import com.google.common.base.Strings;
import org.resala.core.domain.VolunteerEntity;
import org.resala.core.utils.ValidationUtils;
import org.resala.core.volunteer.Dto.RegistrationParamsDTO;
import org.resala.core.volunteer.Dto.RegistrationPostDTO;
import org.resala.core.volunteer.mapper.VolunteerMapper;
import org.resala.core.volunteer.services.VolunteerRegistrationService;
import org.resala.core.volunteer.services.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired private Environment env;
    VolunteerRegistrationController(VolunteerRegistrationService vrService, VolunteerService volunteer) {
        volunteerRegistrationService = vrService;
        volunteerService = volunteer;
    }

    @PostMapping("volunteer")
    public ResponseEntity RegisterVolunteerByCode(RegistrationParamsDTO parms) {

        if (isValidParam(parms)) {
            return ResponseEntity.badRequest().build();
        }
        VolunteerEntity volunteerEntity = volunteerService.findByParams(parms);
        ResponseEntity invalidResponseEntity = getInvalidResponseEntity(parms, volunteerEntity);
        if (invalidResponseEntity != null) {
            return invalidResponseEntity;
        }


        volunteerRegistrationService.save(parms.getBranchId(), parms.getEventId(), volunteerEntity.getId());
        return ResponseEntity.ok().body(volunteerEntity);
    }

    @PostMapping()
    public ResponseEntity RegisterVolunteer(RegistrationPostDTO body) {
        Set<ConstraintViolation<RegistrationPostDTO>> violations = ValidationUtils.getConstraintViolations(body);
        if (!violations.isEmpty()) {
            return ResponseEntity.badRequest().body(violations.stream().findFirst().get().getMessage());
        }
        VolunteerEntity entity = volunteerService.findAny(body.getPhoneNumber(), null, body.getEMail());
        if (null != entity) {
            boolean isSaved = volunteerRegistrationService.validateAndSave(body.getBranchId(), body.getEventId(), entity.getId());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Volunteer is already exist and registration is " + isSaved);
        }
        VolunteerEntity volunteerEntity = VolunteerMapper.instance.toVolunteerEntitiy(body);
        volunteerService.saveData(volunteerEntity);
        volunteerRegistrationService.save(body.getBranchId(), body.getEventId(), volunteerEntity.getId());
        return ResponseEntity.ok().body(volunteerEntity);
    }

    private boolean isValidParams(RegistrationParamsDTO parms) {
        return Objects.isNull(parms) || Objects.isNull(parms.getBranchId()) || Objects.isNull(parms.getEventId());
    }

    private boolean isValidParam(RegistrationParamsDTO parms) {
        return isValidParams(parms) || (Strings.isNullOrEmpty(parms.getCode()) && Strings.isNullOrEmpty(parms.getPhone()));
    }

    private ResponseEntity getInvalidResponseEntity(RegistrationParamsDTO parms, VolunteerEntity volunteerEntity) {
        if (Objects.isNull(volunteerEntity)) {
            return ResponseEntity.badRequest().body(env.getProperty("request.bad"));
        }

        boolean isRegistered = volunteerRegistrationService.isRegistered(parms.getBranchId(), parms.getEventId(), volunteerEntity.getId());
        if (isRegistered) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(env.getProperty("request.conflict"));
        }
        return null;
    }
}
