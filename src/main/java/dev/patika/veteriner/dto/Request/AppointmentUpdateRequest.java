package dev.patika.veteriner.dto.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {
    @Positive(message = "ID Değeri pozitif olmak zorunda")
    private long id;


    private LocalDateTime appointmentDateTime;

    private long doctorId;
    private long animalId;
}
