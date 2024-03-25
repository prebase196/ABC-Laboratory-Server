package com.lab.LabAppointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.LabAppointment.model.Test;
import com.lab.LabAppointment.model.User;

public interface TestRepo extends JpaRepository<Test,Long> {

    List<Test> getByUserId(User user);
}
