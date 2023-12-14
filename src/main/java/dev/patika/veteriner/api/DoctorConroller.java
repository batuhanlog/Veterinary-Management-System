package dev.patika.veteriner.api;

import dev.patika.veteriner.business.abstracts.IDoctorService;
import dev.patika.veteriner.core.config.modelMapper.IModelMapperService;
import dev.patika.veteriner.core.result.Result;
import dev.patika.veteriner.core.result.ResultData;
import dev.patika.veteriner.core.utiles.ResultHelper;
import dev.patika.veteriner.dto.Request.DoctorSaveRequest;
import dev.patika.veteriner.dto.Request.DoctorUpdateRequest;
import dev.patika.veteriner.dto.response.DoctorResponse;
import dev.patika.veteriner.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/doctors")
public class DoctorConroller {
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    // Constructor based dependency injection
    public DoctorConroller(IDoctorService doctorService, IModelMapperService modelMapper) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    // Doktorun detaylarını almak için GET endpoint'i
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get (@PathVariable("id") Long id) {
        Doctor doctor = this.doctorService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(doctor,DoctorResponse.class));
    }

    // Yeni bir doktor kaydetmek için POST endpoint'i
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest ){
        Doctor saveDoctor = this.modelMapper.forRequest().map(doctorSaveRequest,Doctor.class);
        this.doctorService.save(saveDoctor);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveDoctor,DoctorResponse.class));
    }

    // Doktor bilgilerini güncellemek için PUT endpoint'i
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest ){
        Doctor updateDoctor = this.modelMapper.forRequest().map(doctorUpdateRequest, Doctor.class);
        this.doctorService.update(updateDoctor);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateDoctor, DoctorResponse.class));
    }

    // Doktoru silmek için DELETE endpoint'i
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.doctorService.delete(id);
        return ResultHelper.Ok();
    }

}
