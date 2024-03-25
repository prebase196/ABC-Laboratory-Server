package com.lab.LabAppointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.LabAppointment.model.Appointment;
import com.lab.LabAppointment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByAppointment(Appointment appointment);

    Payment findByAppointment_AppointmentID(Long appointmentId);
}
