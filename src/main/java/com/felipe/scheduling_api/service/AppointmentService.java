package com.felipe.scheduling_api.service;

import com.felipe.scheduling_api.dto.AppointmentDTO;
import com.felipe.scheduling_api.model.Appointment;
import com.felipe.scheduling_api.model.Appointment.Status;
import com.felipe.scheduling_api.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado: " + id));
    }

    public List<Appointment> findByDate(LocalDate date) {
        return appointmentRepository.findByDate(date);
    }

    public List<Appointment> findByStatus(Status status) {
        return appointmentRepository.findByStatus(status);
    }

    public List<Appointment> findByEmail(String email) {
        return appointmentRepository.findByClientEmail(email);
    }

    public Appointment create(AppointmentDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setClientName(dto.getClientName());
        appointment.setClientEmail(dto.getClientEmail());
        appointment.setService(dto.getService());
        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());
        appointment.setNotes(dto.getNotes());
        return appointmentRepository.save(appointment);
    }

    public Appointment updateStatus(Long id, Status status) {
        Appointment appointment = findById(id);
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    public void delete(Long id) {
        findById(id);
        appointmentRepository.deleteById(id);
    }

    public Map<String, Object> summary() {
        List<Appointment> all = findAll();
        long pending = all.stream().filter(a -> a.getStatus() == Status.PENDING).count();
        long confirmed = all.stream().filter(a -> a.getStatus() == Status.CONFIRMED).count();
        long cancelled = all.stream().filter(a -> a.getStatus() == Status.CANCELLED).count();
        long completed = all.stream().filter(a -> a.getStatus() == Status.COMPLETED).count();
        return Map.of(
            "total", all.size(),
            "pending", pending,
            "confirmed", confirmed,
            "cancelled", cancelled,
            "completed", completed
        );
    }
}