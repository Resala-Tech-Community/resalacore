package org.resala.core.response;

import org.resala.core.response.entities.ErrorResponse;
import org.resala.core.volunteer.Dto.RegistrationPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
public class ErrorMessageResponse {

    @Autowired
    private Environment env;

    public ResponseEntity badRequest() {
        return ResponseEntity.badRequest().body(new ErrorResponse(env.getProperty("request.bad")));
    }

    public ResponseEntity notExist() {
        return ResponseEntity.badRequest().body(new ErrorResponse(env.getProperty("request.bad.not.exist")));
    }

    public ResponseEntity alreadyRegistered() {
        return ResponseEntity.badRequest().body(new ErrorResponse(env.getProperty("request.conflict")));
    }

    public ResponseEntity getViolations(Set<ConstraintViolation<RegistrationPostDTO>>  violations) {
        return ResponseEntity.badRequest().body(violations.stream().findFirst().get().getMessage());
    }
    public ResponseEntity volunteerExist(boolean isSaved) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(env.getProperty("request.volunteer.exist") + isSaved));
    }


}
