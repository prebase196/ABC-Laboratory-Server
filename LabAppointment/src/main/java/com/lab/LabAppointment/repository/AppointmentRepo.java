package com.lab.LabAppointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.LabAppointment.model.Appointment;
import com.lab.LabAppointment.model.User;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    List<Appointment> findByUser(User user);

}
