package com.lab.LabAppointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.LabAppointment.model.Report;

public interface ReportRepo extends JpaRepository<Report,Long> {
}
