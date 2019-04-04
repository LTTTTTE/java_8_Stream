package Func0325;

import java.util.function.*;

@FunctionalInterface
interface Square<T,R>{
    int calculate(int width,int height);
}

@FunctionalInterface
interface isA_1<T>{
    boolean test(char t);
}

public class FunInterfaceHW {
    public static void main(String[] args){

        Square s = (width, height) -> width*height;


        isA_1 isa_1 = t->t=='A';
        System.out.println(isa_1.test('A'));
        System.out.println(isa_1.test('B')); //인터페이스사용


        Predicate<Character>isA_2 = x->x=='A';
        System.out.println(isA_2.test('A'));
        System.out.println(isA_2.test('B')); //Java 꺼사용
    }
}

