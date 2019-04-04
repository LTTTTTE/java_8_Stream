package Funtional_InterFace_test_01;

import java.util.function.*;

public class Test {

    class Test_Object{
        private int value;
        private String text;

        Test_Object(){

        }

        public int getValue() {
            return value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args){



        BiFunction<Integer,Integer,Integer> addition = (x, y)->{return x+y;};
        BiFunction<Double,Double,Double>multifly = (x,y)->{return x*y;};
        BinaryOperator<Integer>additon_2 = (x,y)->{return  x+y;};

        BiFunction<Test_Object,Test_Object,Integer>sum_value =(x,y)->{
            return x.getValue()+y.getValue();
        };

        BiFunction<Test_Object,Test_Object,String>sum_text = (x,y)->{
            return x.getText()+y.getText();
        };

        System.out.println("1 + 2 = "+addition.apply(1,2)); //BiFunc
        System.out.println("3.5 * 4.3 = "+multifly.apply(3.5,4.3));//BiFunc
        System.out.println("4 + 5 = "+additon_2.apply(4,5));//BiOper


        Consumer<String>hello = str->{
            System.out.println("Hello_"+str);
        };
        hello.accept("홍길동");

        Supplier<String>supplier = ()->{
            return "What the Fuck?";
        };
        System.out.println(supplier.get());


    }
}
