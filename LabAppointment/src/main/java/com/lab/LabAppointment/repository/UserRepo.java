package com.lab.LabAppointment.repository;

import com.lab.LabAppointment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User getUserById(Long id);

    List<User> findByUserRole(String userRole);
}
