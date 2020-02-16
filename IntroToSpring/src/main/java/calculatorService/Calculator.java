package calculatorService;

public class Calculator {

    private double num1;
    private double num2;
    private double result;

    public Calculator(double num1, double num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public double getNum1() {
        return num1;
    }

    public double getNum2() {
        return num2;
    }

    public double add() {
        result = this.num1 + this.num2;
        return result;
    }

    public double subtract() {
        result = this.num1 - this.num2;
        return result;
    }

    public double multiply() {
        result = this.num1 * this.num2;
        return result;
    }

    public double divide() {
        result = this.num1 / this.num2;
        return result;
    }
}
