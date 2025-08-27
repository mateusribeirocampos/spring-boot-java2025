package br.com.campos.math;

import br.com.campos.converters.NumberConverter;

public class SimpleMath {

    public double sum(Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }
    public double subtraction(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }
    public double multiplication(Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }
    public double divide(Double numberOne, Double numberTwo) {
        return numberOne / numberTwo;
    }
    public double mean(Double numberOne, Double numberTwo) {
        return (numberOne + numberTwo)/2;
    }
    public double square(Double numberOne) {
        return Math.sqrt(numberOne);
    }
}
