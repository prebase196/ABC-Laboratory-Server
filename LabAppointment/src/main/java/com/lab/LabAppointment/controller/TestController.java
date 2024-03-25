package com.lab.LabAppointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.LabAppointment.model.Test;
import com.lab.LabAppointment.model.User;
import com.lab.LabAppointment.repository.TestRepo;
import com.lab.LabAppointment.repository.UserRepo;

@RestController
@RequestMapping("/api/v1/tests")
public class TestController {
	
	@Autowired
    private final TestRepo testRepo;
	@Autowired
    private final UserRepo userRepo;

    public TestController(TestRepo testRepo, UserRepo userRepo) {
        this.testRepo = testRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<Test> getAllTest() {
        return testRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Test> createTest(@RequestBody Test test) {
        Test savedTest = testRepo.save(test);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTest);

    }

    @GetMapping("/{userId}")
    public List<Test> getByPatient(@PathVariable Long userId){

        User user = userRepo.getUserById(userId);
        return testRepo.getByUserId(user);
    }
}
