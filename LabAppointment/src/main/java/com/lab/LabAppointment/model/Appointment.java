package com.lab.LabAppointment.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "appointments")

public class Appointment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AppointmentID")
    private Long appointmentID;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @Column(name = "Appointment_Date")
    private Date appointmentDate;

    @Column(name = "Appointment_Time")
    private String appointmentTime;

    @Column(name = "Test_Type")
    private String testType;

    @Column(name = "Assigned_Technician")
    private String assignedTechnician;

    @Column(name = "Recommending_Doctor")
    private String recommendingDoctor;

	public Long getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(Long appointmentID) {
		this.appointmentID = appointmentID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getAssignedTechnician() {
		return assignedTechnician;
	}

	public void setAssignedTechnician(String assignedTechnician) {
		this.assignedTechnician = assignedTechnician;
	}

	public String getRecommendingDoctor() {
		return recommendingDoctor;
	}

	public void setRecommendingDoctor(String recommendingDoctor) {
		this.recommendingDoctor = recommendingDoctor;
	}
}
