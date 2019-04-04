package Func0325;

import java.util.function.*;

public class MathHW {

    public static void main(String[] args) {

        BiFunction<Double, Double, Double> addition = (aDouble, aDouble2) -> aDouble + aDouble2;
        BiFunction<Double, Double, Double> subtraction = (aDouble, aDouble2) -> aDouble - aDouble2;
        BiFunction<Double, Double, Double> multiplication = (aDouble, aDouble2) -> aDouble * aDouble2;
        BiFunction<Double, Double, Double> division = (aDouble, aDouble2) -> aDouble / aDouble2;


        System.out.println("10 + 5 = " + addition.apply(10.0, 5.0));
        System.out.println("10 - 5 = " + subtraction.apply(10.0, 5.0));
        System.out.println("10 x 5 = " + multiplication.apply(10.0, 5.0));
        System.out.println("10 / 5 = " + division.apply(10.0, 5.0));


        Consumer<String> greetService1 = message -> System.out.println("Hello " + message);

        greetService1.accept("Mahesh");

    }
}

