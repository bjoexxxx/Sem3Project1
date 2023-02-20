package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repositories.CarRepository;
import dat3.car.repositories.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class DeveloperData implements ApplicationRunner {

    CarRepository carRepository;
    MemberRepository memberRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository){
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }
    public void testDataCreation(){

        memberRepository.save(new Member("kurt-w", "kw@a.dk", "test12",
                "Kurt", "Wonnegut", "Lyngbyvej 34", "Lyngby", "2800"));
        memberRepository.save(new Member("hanne-w", "hw@a.dk", "test12",
                "Hanne", "Wonnegut", "Lyngbyvej 34", "Lyngby", "2800"));


        List<Car> newCars = new ArrayList<>(Arrays.asList(
                Car.builder().brand("Volvo").model("V70").pricePerDay(500).bestDiscount(10).build(),
                Car.builder().brand("Suzuki").model("Swift").pricePerDay(350).bestDiscount(6).build(),
                Car.builder().brand("Kia").model("Optima").pricePerDay(450).bestDiscount(18).build(),
                Car.builder().brand("WW").model("Wagon").pricePerDay(400).bestDiscount(20).build(),
                Car.builder().brand("Volvo").model("S80").pricePerDay(600).bestDiscount(12).build(),
                Car.builder().brand("Suzuki").model("SX4").pricePerDay(400).bestDiscount(16).build(),
                Car.builder().brand("Suzuki").model("SX4").pricePerDay(400).bestDiscount(16).build(),
                Car.builder().brand("Suzuki").model("SX4").pricePerDay(400).bestDiscount(16).build(),
                Car.builder().brand("Kia").model("Sorento").pricePerDay(500).bestDiscount(22).build(),
                Car.builder().brand("WW").model("Pickup").pricePerDay(450).bestDiscount(28).build(),
                Car.builder().brand("Volvo").model("V60").pricePerDay(700).bestDiscount(15).build(),
                Car.builder().brand("Suzuki").model("Grand Vitara").pricePerDay(450).bestDiscount(12).build(),
                Car.builder().brand("Kia").model("Sportage").pricePerDay(500).bestDiscount(20).build(),
                Car.builder().brand("WW").model("SUV").pricePerDay(400).bestDiscount(18).build(),
                Car.builder().brand("Volvo").model("XC90").pricePerDay(800).bestDiscount(25).build(),
                Car.builder().brand("Volvo").model("XC90").pricePerDay(800).bestDiscount(25).build(),
                Car.builder().brand("Volvo").model("XC90").pricePerDay(800).bestDiscount(25).build(),
                Car.builder().brand("Suzuki").model("Baleno").pricePerDay(450).bestDiscount(15).build(),
                Car.builder().brand("Kia").model("Stinger").pricePerDay(600).bestDiscount(12).build(),
                Car.builder().brand("WW").model("Sedan").pricePerDay(400).bestDiscount(20).build(),
                Car.builder().brand("Volvo").model("XC40").pricePerDay(700).bestDiscount(30).build(),
                Car.builder().brand("Volvo").model("XC40").pricePerDay(700).bestDiscount(30).build(),
                Car.builder().brand("Volvo").model("XC40").pricePerDay(700).bestDiscount(30).build(),
                Car.builder().brand("Suzuki").model("Ignis").pricePerDay(400).bestDiscount(14).build(),
                Car.builder().brand("Kia").model("Rio").pricePerDay(450).bestDiscount(12).build(),
                Car.builder().brand("WW").model("Hatchback").pricePerDay(450).bestDiscount(16).build()
        ));
        carRepository.saveAll(newCars);


        /*You can remove the following when we get to week2 if you like, they were only include to demonstrate
    collections of basic type*/
        Member demoMember = new Member("demo", "demo@a.dk", "test12",
                "Demo", "Wonnegut", "Lyngbyvej 34", "Lyngby", "2800");
        demoMember.setFavoriteCarColours(new ArrayList<>(Arrays.asList("blue","silver","black")));
        Map<String,String> phoneNumbers = new HashMap<>();
        phoneNumbers.put("private","12345");
        phoneNumbers.put("work","54321");
        demoMember.setPhones(phoneNumbers);
        memberRepository.save(demoMember);

    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        testDataCreation();
    }
}
