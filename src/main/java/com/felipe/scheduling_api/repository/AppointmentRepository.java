package com.felipe.scheduling_api.repository;

import com.felipe.scheduling_api.model.Appointment;
import com.felipe.scheduling_api.model.Appointment.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDate(LocalDate date);
    List<Appointment> findByStatus(Status status);
    List<Appointment> findByClientEmail(String email);
    List<Appointment> findByDateBetween(LocalDate start, LocalDate end);
}