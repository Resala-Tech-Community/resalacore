package org.resala.core.response;

import org.resala.core.response.entities.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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
}
