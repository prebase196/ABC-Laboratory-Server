package com.lab.LabAppointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.LabAppointment.model.Appointment;
import com.lab.LabAppointment.model.User;
import com.lab.LabAppointment.repository.AppointmentRepo;
import com.lab.LabAppointment.repository.UserRepo;

@RestController
@RequestMapping("/api/v1/appointments")
@CrossOrigin(origins = "*")

public class AppointmentController {

	private final EmailService emailService;

	@Autowired
    private final AppointmentRepo appointmentRepo;
	@Autowired
    private final UserRepo userRepo;

    public AppointmentController(AppointmentRepo appointmentRepo,UserRepo userRepo,EmailService emailService) {
        this.appointmentRepo = appointmentRepo;
        this.userRepo = userRepo;
        this.emailService = emailService;
    }

    @GetMapping
    public List<Appointment> getAllAppointment() {
        return appointmentRepo.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Appointment> getAppointmentsByUser(@PathVariable Long userId) {
        User user = userRepo.getUserById(userId);

        // Now you can use the user object to fetch appointments
        return appointmentRepo.findByUser(user);
    }

    @PostMapping
     public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentRepo.save(appointment);

        User user = userRepo.getUserById(appointment.getUser().getId());
        String body = appointment.getAppointmentID().toString() + "\n" + user.getFullname();

        sendConfirmationEmail(user.getEmail(), "Appointment Confirmation", "Your appointment has been scheduled."+"\n"+body);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
    }

    private void sendConfirmationEmail(String to, String subject, String body) {
        emailService.sendEmail(to, subject, body);
    }


}
