package dev.patika.veteriner.business.concretes;

import dev.patika.veteriner.business.abstracts.IAppointmentService;
import dev.patika.veteriner.core.config.exception.NotFoundException;
import dev.patika.veteriner.core.utiles.Msg;
import dev.patika.veteriner.entities.Animal;
import dev.patika.veteriner.entities.Appointment;
import dev.patika.veteriner.repository.AppointmentRepo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentManager implements IAppointmentService {
    private final AppointmentRepo appointmentRepo;

    public AppointmentManager(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }


    @Override
    public Appointment get(Long id) {
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }


    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        return null;
    }


    @Override
    public Appointment update(Appointment appointment) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return true;
    }

    @Override
    public List<Appointment> getByDoctorId(Long doctorId) {
        if (doctorId == null || doctorId <= 0){
            throw new IllegalArgumentException(Msg.NOT_FOUND);
        }
        return this.appointmentRepo.findByDoctorId(doctorId);

    }

    @Override
    public List<Appointment> getByAnimalId(Long animalId) {
        if (animalId == null || animalId <= 0){
            throw new IllegalArgumentException(Msg.NOT_FOUND);
        }
        return this.appointmentRepo.findByAnimalId(animalId);
    }

    // Belirli bir tarih aralığındaki tüm randevuları getirir
    @Override
    public List<Appointment> getAppointmentsByDateRange(String startDate, String endDate) {
        LocalDateTime startDateTime = LocalDateTime.parse(startDate);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate);
        List<Appointment> appointments = appointmentRepo.findByAppointmentDateTimeBetween(startDateTime, endDateTime);
        return appointments;
    }
}