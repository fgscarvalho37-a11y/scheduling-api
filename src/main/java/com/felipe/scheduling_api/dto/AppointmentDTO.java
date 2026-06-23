package com.felipe.scheduling_api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDTO {

    @NotBlank(message = "Nome do cliente é obrigatório")
    private String clientName;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String clientEmail;

    @NotBlank(message = "Serviço é obrigatório")
    private String service;

    @NotNull(message = "Data é obrigatória")
    private LocalDate date;

    @NotNull(message = "Horário é obrigatório")
    private LocalTime time;

    private String notes;
}