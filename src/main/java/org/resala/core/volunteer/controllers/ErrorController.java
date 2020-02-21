package org.resala.core.volunteer.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("error")
public class ErrorController {
    @ExceptionHandler(Exception.class)
    public void handleNotFoundException(Exception e) {
        Logger.getLogger("yahia").log(Level.WARNING, e.getMessage());
    }
}
