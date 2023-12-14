package dev.patika.veteriner.business.concretes;

import dev.patika.veteriner.business.abstracts.IDoctorService;
import dev.patika.veteriner.core.config.exception.NotFoundException;
import dev.patika.veteriner.core.utiles.Msg;
import dev.patika.veteriner.entities.Doctor;
import dev.patika.veteriner.repository.DoctorRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class DoctorManager implements IDoctorService {
    // DoctorRepo bağımlılığını enjekte etmek için constructor
    private final DoctorRepo doctorRepo;

    // Constructor enjeksiyonu
    public DoctorManager(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    // Doktoru kaydetmek için
    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepo.save(doctor); // DoctorRepo'nun save metodu kullanılır
    }

    // Doktoru ID'ye göre getirmek için
    @Override
    public Doctor get(Long id) {
        // DoctorRepo'daki findById kullanılır, eğer bulunamazsa NotFound exception fırlatılır
        return this.doctorRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    // Sayfalı olarak doktorları getirmek için
    @Override
    public Page<Doctor> cursor(int page, int pageSize) {
        // Sayfalama yapmak için PageRequest kullanılır
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.doctorRepo.findAll(pageable);// DoctorRepo'nun findAll metodu kullanılır
    }

    // Doktoru güncellemek için
    @Override
    public Doctor update(Doctor doctor) {
        this.get(doctor.getId());
        return this.doctorRepo.save(doctor);// DoctorRepo'nun save metodu kullanılır
    }

    // Doktoru silmek için
    @Override
    public boolean delete(long id) {
        Doctor doctor=this.get(id);
        this.doctorRepo.delete(doctor);// DoctorRepo'nun delete metodu ile silinir
        return true;// Silme işlemi başarılı olduğu için true döndürülür
    }
}
