package com.felipe.scheduling_api.controller;

import com.felipe.scheduling_api.dto.AppointmentDTO;
import com.felipe.scheduling_api.model.Appointment;
import com.felipe.scheduling_api.model.Appointment.Status;
import com.felipe.scheduling_api.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> findAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.findById(id));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Appointment>> findByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointmentService.findByDate(date));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Appointment>> findByStatus(@PathVariable Status status) {
        return ResponseEntity.ok(appointmentService.findByStatus(status));
    }

    @GetMapping("/client/{email}")
    public ResponseEntity<List<Appointment>> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(appointmentService.findByEmail(email));
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> summary() {
        return ResponseEntity.ok(appointmentService.summary());
    }

    @PostMapping
    public ResponseEntity<Appointment> create(@Valid @RequestBody AppointmentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.create(dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Appointment> updateStatus(@PathVariable Long id, @RequestParam Status status) {
        return ResponseEntity.ok(appointmentService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}