package dat3.car.dto;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarRequest {
    Long id;
    String brand;
    String model;
    double pricePerDay;
    int bestDiscount;

    public static Car getCarEntity(CarRequest c){
        return new Car(c.id, c.getBrand(), c.getModel(), c.getPricePerDay(), c.getBestDiscount());
    }

}
