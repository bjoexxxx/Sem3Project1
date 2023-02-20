package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repositories.CarRepository;
import dat3.car.repositories.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    static CarRepository carRepository;

    public CarService(CarRepository carRepository){
        CarService.carRepository = carRepository;
    }


    public CarResponse addCar(CarRequest carRequest){
        //if(memberRepository.existsById())
        Car car = CarRequest.getCarEntity(carRequest);
        car = carRepository.save(car);
        return new CarResponse(car);
    }

    public List<CarResponse> getCars(){
        List<Car> cars = carRepository.findAll();
        List<CarResponse> carResponses = new ArrayList<>();
        carResponses = cars.stream().map(c-> new CarResponse(c)).toList();
        return carResponses;
    }

    public CarResponse getCarById(Long id){
        Car car = carRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("car not found"));
        return new CarResponse(car);

    }


    public static ResponseEntity<Boolean> editCar(CarRequest body, Long id){
        Car editThis = carRepository.findById(id).
                orElseThrow(()-> new EntityNotFoundException("nothing to edit"));
        if (body.getBrand() != null){
            editThis.setBrand(body.getBrand());
        }
        if (body.getModel() != null){
            editThis.setModel(body.getModel());
        }
        if (body.getPricePerDay() != 0){
            editThis.setPricePerDay(body.getPricePerDay());
        }
        if (body.getBestDiscount() != 0){
            editThis.setBestDiscount(body.getBestDiscount());
        }
        carRepository.save(editThis);



        return ResponseEntity.ok(true);
    }

    public void deleteCar(Long id) {

        carRepository.deleteById(id);


    }
}
