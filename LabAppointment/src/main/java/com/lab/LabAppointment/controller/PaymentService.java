package com.lab.LabAppointment.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.LabAppointment.model.Appointment;
import com.lab.LabAppointment.model.Payment;
import com.lab.LabAppointment.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment makePayment(Appointment appointment, BigDecimal amount, String paymentMethod) {
        Payment payment = new Payment();
        payment.setAppointment(appointment);
        payment.setAmount(amount);
        payment.setPaymentDate(new Date());
        payment.setPaymentMethod(paymentMethod);

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByAppointment(Appointment appointment) {
        return paymentRepository.findByAppointment(appointment);
    }
}
