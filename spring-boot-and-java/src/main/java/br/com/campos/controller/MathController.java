package br.com.campos.controller;

import br.com.campos.converters.NumberConverter;
import br.com.campos.math.SimpleMath;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8080/math/sum/3/5

@RestController
@RequestMapping("/math")
public class MathController {

    SimpleMath simpleMath = new SimpleMath();

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedOperationException("Please set a numeric value!");
        Double num1 = NumberConverter.convertToDouble(numberOne);
        Double num2 = NumberConverter.convertToDouble(numberTwo);

        return simpleMath.sum(num1, num2);
    }

    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtract(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedOperationException("Please set a numeric value!");

        Double num1 = NumberConverter.convertToDouble(numberOne);
        Double num2 = NumberConverter.convertToDouble(numberTwo);

        return simpleMath.subtraction(num1, num2);
    }

    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedOperationException("Please set a numeric value!");
        Double num1 = NumberConverter.convertToDouble(numberOne);
        Double num2 = NumberConverter.convertToDouble(numberTwo);

        return simpleMath.multiplication(num1, num2);
    }

    @RequestMapping("/divide/{numberOne}/{numberTwo}")
    public Double divide(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedOperationException("Please set a numeric value!");
        if (NumberConverter.convertToDouble(numberTwo) == 0) {
            throw new UnsupportedOperationException("Division by zero is not allowed!!");
        }
        Double num1 = NumberConverter.convertToDouble(numberOne);
        Double num2 = NumberConverter.convertToDouble(numberTwo);

        return simpleMath.divide(num1, num2);
    }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (!NumberConverter.isNumeric(numberOne) ||!NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedOperationException("Please set a numeric value!");
        Double num1 = NumberConverter.convertToDouble(numberOne);
        Double num2 = NumberConverter.convertToDouble(numberTwo);

        return simpleMath.mean(num1, num2);
    }

    @RequestMapping("/square/{numberOne}")
    public Double square(
            @PathVariable("numberOne") String numberOne
    ) {
        if (!NumberConverter.isNumeric(numberOne))
            throw new UnsupportedOperationException("Please set a numeric value!");
        if (NumberConverter.convertToDouble(numberOne) <= 0) {
            throw new UnsupportedOperationException("Please, the number could not be zero or negative!");
        }
        Double num1 = NumberConverter.convertToDouble(numberOne);

        return simpleMath.square(num1);
    }
}
