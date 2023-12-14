package dev.patika.veteriner.repository;

import dev.patika.veteriner.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine,Long> {
    List<Vaccine> findByAnimalId(Long animalId);
    List<Vaccine> findByAnimalIdAndProtectionStartDateBetween(Long animalId, LocalDate startDate, LocalDate endDate);
    List<Vaccine> findByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate);

}
