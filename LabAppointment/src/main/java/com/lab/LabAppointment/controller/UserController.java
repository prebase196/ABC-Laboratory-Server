package com.lab.LabAppointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.LabAppointment.exception.ResourceNotFoundException;
import com.lab.LabAppointment.model.User;
import com.lab.LabAppointment.repository.UserRepo;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
    private final UserRepo userRepo;
    
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    @GetMapping("/patient")
    public List<User> getUsersByRole() {
        return userRepo.findByUserRole("patient");
    }
    @GetMapping("/doctor")
    public List<User> getDoctorsByRole() {
        return userRepo.findByUserRole("doctor");
    }
    @GetMapping("/technician")
    public List<User> getTechByRole() {
        return userRepo.findByUserRole("technician");
    }

    // Build Crate Users REST API
    @PostMapping
    public User createUser(@RequestBody User user) {
    	if(user.getUserRole() == null) {
    		user.setUserRole("patient");
    	}
        return userRepo.save(user);
    }

    // Build Get User By Username REST API
    @PostMapping("{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }
    }

    // Verify Login Details REST API
    @PostMapping("/login")
    public ResponseEntity<User> verifyLoginDetails(@RequestBody User loginUser) {
        User user = userRepo.findByUsername(loginUser.getUsername());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {

            if (user.getUserRole().equals("patient")) {
                return ResponseEntity.ok().body(user);

            } else if (user.getUserRole().equals("doctor")) {
                return ResponseEntity.ok().body(user);

            } else if (user.getUserRole().equals("technician")) {
                return ResponseEntity.ok().body(user);

            } else if (user.getUserRole().equals("admin")) {
                return ResponseEntity.ok().body(user);

            }
            return ResponseEntity.ok().body(user);
        }
        return null;
    }
}
