package dat3.car.api;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }

    //Admin only
    @GetMapping
    List<CarResponse> getCars(){ return carService.getCars();}

    //Admin ???
    @GetMapping(path = "/{id}")
    CarResponse getCarById(@PathVariable Long id) throws Exception {
        return carService.getCarById(id);}

    //Anon --> ??????
    //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    //Member
    @PutMapping("/{id}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable Long id){
        CarService.editCar(body, id);
        return ResponseEntity.ok(true);
    }

    // Admin ????
    @DeleteMapping("/{id}")
    void deleteMemberByUsername(@PathVariable Long id) {
        carService.deleteCar(id);
    }


}
