package Func0325;

import java.util.function.*;

@FunctionalInterface
interface Square_ {
    int calculate(int x);
}

@FunctionalInterface
interface Max {
    boolean isFirst(int x, int y);
}

class FunInterface {
    static UnaryOperator<Integer> fac = n -> n == 0 // static, recursive method
            ? 1
            : n * FunInterface.fac.apply(n - 1);

    public static void main(String args[]) {
// Unary Functions
        Square_ square = x -> x * x;
        Square_ square2 = x -> x * x;
        UnaryOperator<Integer> square3 = x -> x * x;
        System.out.println(square.calculate(5));
        System.out.println(square2.calculate(5));
        System.out.println(square3.apply(5));
        System.out.println(fac.apply(5));
// Binary Functions
        Max isBigger = (x, y) -> x > y;
        BiPredicate<Integer, Integer> isBigger2 = (x, y) -> x > y;
        BiPredicate<Integer, Integer> isBigger3 = (x, y) -> x > y;
// lambda definition with multiple statements
        BinaryOperator<Integer> smallerSquare = ((x, y) -> {
            Integer smaller = x > y ? y : x;
            return square2.calculate(smaller);
        });
// Consumer : no return value
        BiConsumer<Integer,Integer> smallerSquare2 = ((x, y) -> {
            Integer smaller = x > y ? y : x;
            System.out.println("BiConsumer : " + square2.calculate(smaller));
        });
        System.out.println(isBigger.isFirst(10, 20));
        System.out.println(isBigger2.test(10, 20));
        System.out.println(isBigger3.test(10, 20));
        System.out.println(smallerSquare.apply(10, 20));
        smallerSquare2.accept(10, 20);
    }
}