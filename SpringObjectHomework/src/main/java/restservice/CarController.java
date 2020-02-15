package restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CarController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/car")
    public Car car(@RequestParam(value = "brand") String brand, @RequestParam(value = "model") String model,
                   @RequestParam(value = "hp") int hp, @RequestParam(value = "color") String color) {
        return new Car(counter.incrementAndGet(), brand, model, hp, color);
    }
}
