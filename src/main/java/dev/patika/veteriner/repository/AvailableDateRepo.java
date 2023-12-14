package dev.patika.veteriner.repository;

import dev.patika.veteriner.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableDateRepo extends JpaRepository<AvailableDate,Long> {


}
