package dev.patika.veteriner.business.concretes;
import dev.patika.veteriner.business.abstracts.IAvailableDateService;
import dev.patika.veteriner.core.config.exception.NotFoundException;
import dev.patika.veteriner.core.utiles.Msg;
import dev.patika.veteriner.entities.AvailableDate;
import dev.patika.veteriner.repository.AvailableDateRepo;
import org.springframework.stereotype.Service;
import dev.patika.veteriner.entities.AvailableDate;


@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;

    public AvailableDateManager(AvailableDateRepo availableDateRepo) {
        this.availableDateRepo = availableDateRepo;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }
}
