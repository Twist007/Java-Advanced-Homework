package calculatorService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class calculatorController {

    @GetMapping("/calculator/add")
    public double calculatorAdd(@RequestParam(value = "firstNumber") double num1,
                                @RequestParam(value = "secNumber") double num2) {
        Calculator calculator = new Calculator(num1, num2);
        return calculator.add();
    }

    @GetMapping("/calculator/subtract")
    public double calculatorSubtract(@RequestParam(value = "firstNumber") double num1,
                                     @RequestParam(value = "secNumber") double num2) {
        Calculator calculator = new Calculator(num1, num2);
        return calculator.subtract();
    }

    @GetMapping("/calculator/multiply")
    public double calculatorMultiply(@RequestParam(value = "firstNumber") double num1,
                                     @RequestParam(value = "secNumber") double num2) {
        Calculator calculator = new Calculator(num1, num2);
        return calculator.multiply();
    }

    @GetMapping("/calculator/divide")
    public double calculatorDivide(@RequestParam(value = "firstNumber") double num1,
                                   @RequestParam(value = "secNumber") double num2) {
        Calculator calculator = new Calculator(num1, num2);
        return calculator.divide();
    }
}
