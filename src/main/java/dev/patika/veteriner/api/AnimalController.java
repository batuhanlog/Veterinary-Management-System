package dev.patika.veteriner.api;
import dev.patika.veteriner.business.abstracts.IAnimalService;
import dev.patika.veteriner.business.abstracts.ICustomerService;
import dev.patika.veteriner.core.config.modelMapper.IModelMapperService;
import dev.patika.veteriner.core.result.Result;
import dev.patika.veteriner.core.result.ResultData;
import dev.patika.veteriner.core.utiles.ResultHelper;
import dev.patika.veteriner.dto.Request.AnimalSaveRequest;
import dev.patika.veteriner.dto.Request.AnimalUpdateRequest;
import dev.patika.veteriner.dto.response.AnimalResponse;
import dev.patika.veteriner.dto.response.VaccineResponse;
import dev.patika.veteriner.entities.Animal;
import dev.patika.veteriner.entities.Customer;
import dev.patika.veteriner.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")

public class AnimalController {
    private final IAnimalService animalService;
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    // Constructor-based dependency injection
    public AnimalController(IAnimalService animalService,
                            ICustomerService customerService,
                            IModelMapperService modelMapper) {
        this.animalService = animalService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    // Belirli bir hayvanın detaylarını getiren endpoint
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get (@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal,AnimalResponse.class));
    }

    // Yeni hayvan ekleme endpoint'i
    @PostMapping("/createdNew")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest ){
        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest,Animal.class);

        Customer customer =this.customerService.get(animalSaveRequest.getCustomerId());
        saveAnimal.setCustomer(customer);

        this.animalService.save(saveAnimal);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAnimal,AnimalResponse.class));
    }

    // Hayvan güncelleme endpoint'i
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest ){
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest,Animal.class);
        this.animalService.update(updateAnimal);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAnimal,AnimalResponse.class));
    }

    // Hayvan silme endpoint'i
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
        return ResultHelper.Ok();
    }
    // Hayvana ait aşıları getiren endpoint
    @GetMapping("/{id}/vaccines")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getVaccinesForAnimal(@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(id);

        // Burada, animal.getVaccines() şeklinde hayvana ait tüm aşı kayıtlarını alabilirsiniz
        List<Vaccine> vaccines = animal.getVaccines();

        // Bu aşı kayıtlarını VaccineResponse'a dönüştürerek döndürebilirsiniz
        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(vaccineResponses);
    }
    @GetMapping("/filter") //http://localhost:8047/v1/animals/filter?name=Şila
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByName(@RequestParam("name") String name) {
        List<Animal> animals = this.animalService.getAnimalsByName(name);

        // İsmine göre filtrelenmiş hayvanları AnimalResponse formatına dönüştürüyoruz
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(animalResponses);
    }
    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByCustomerId(@PathVariable("customerId") Long customerId) {
        List<Animal> animals = this.animalService.getAnimalsByCustomerId(customerId);

        // Sahibinin hayvanlarını AnimalResponse formatına dönüştürüyoruz
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(animalResponses);
    }
}
