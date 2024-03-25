package com.lab.LabAppointment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ApiController {
    @GetMapping(value = "/")
    public String getPage(){
        return "Welcome Home!!";
    }
}
