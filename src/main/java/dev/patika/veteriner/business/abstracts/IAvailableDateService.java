package dev.patika.veteriner.business.abstracts;

import dev.patika.veteriner.entities.AvailableDate;

public interface IAvailableDateService {
    AvailableDate save(AvailableDate availableDate);
    AvailableDate get(Long id);
}