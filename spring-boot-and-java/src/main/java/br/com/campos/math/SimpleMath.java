package br.com.campos.math;

import br.com.campos.converters.NumberConverter;

public class SimpleMath {

    public double sum(String numberOne, String numberTwo) {
        return NumberConverter.convertToDouble(numberOne) + NumberConverter.convertToDouble(numberTwo);
    }
    public double subtraction(String numberOne, String numberTwo) {
        return NumberConverter.convertToDouble(numberOne) - NumberConverter.convertToDouble(numberTwo);
    }
    public double multiplication(String numberOne, String numberTwo) {
        return NumberConverter.convertToDouble(numberOne) * NumberConverter.convertToDouble(numberTwo);
    }
    public double divide(String numberOne, String numberTwo) {
        return NumberConverter.convertToDouble(numberOne) / NumberConverter.convertToDouble(numberTwo);
    }
    public double mean(String numberOne, String numberTwo) {
        return (NumberConverter.convertToDouble(numberOne) + NumberConverter.convertToDouble(numberTwo))/2;
    }
    public double square(String numberOne) {
        return Math.sqrt(NumberConverter.convertToDouble(numberOne));
    }
}
