package dev.patika.veteriner.repository;

import dev.patika.veteriner.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepo extends JpaRepository<Animal,Long> {
    List<Animal> findByNameIgnoreCaseContaining(String name);
    List<Animal> findByCustomerId(Long customerId);


}
