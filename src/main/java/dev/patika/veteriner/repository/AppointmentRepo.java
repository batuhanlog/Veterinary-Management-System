package dev.patika.veteriner.repository;


import dev.patika.veteriner.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {

    List<Appointment> findByAnimalId(Long animalId);

    // Doktor ID'sine göre randevuları bulma
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByAppointmentDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

}