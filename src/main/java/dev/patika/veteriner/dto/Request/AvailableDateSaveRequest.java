package dev.patika.veteriner.dto.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateSaveRequest {
    private LocalDate availableDate;
    private long doctorId;
}
