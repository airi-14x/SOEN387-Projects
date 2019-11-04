package calculator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Airi
 */
public class OperatingClass {

    private int number1;
    private int number2;
    private String operator;
    private int result1;

    public OperatingClass(int number1, int number2, String operator) {
        this.number1 = number1;
        this.number2 = number2;
        this.operator = operator;
    }

    public int getResult() {
        return result1;
    }

    public double calc() {
        switch (operator) {
            case "+":
                result1 = number1 + number2;
                break;
            case "-":
                result1 = number1 - number2;
                break;
            case "*":
                result1 = number1 * number2;
                break;
            case "/":
                result1 = number1 / number2;
                break;
        }
        return result1;
    }

}
