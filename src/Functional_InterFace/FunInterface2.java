// Lambda are used primarily to define inline implementation of a functional interface,
// i.e. an interface with a single method only
// Lambda eliminates the need of anonymous class and gives a very simple yet
// powerful functional programming capability to Java

package Functional_InterFace;

import java.util.function.*;

public class FunInterface2 {
    
   public static void main(String args[]) {
      FunInterface2 tester = new FunInterface2();
		
      //with type declaration
      MathOperation addition = (int a, int b) -> a + b;
		
      //with out type declaration
      MathOperation subtraction = (a, b) -> a - b;
		
      //with return statement along with curly braces
      MathOperation multiplication = (int a, int b) -> { return a * b; };
		
      //without return statement and without curly braces
      MathOperation division = (int a, int b) -> a / b;

      BiFunction<Integer,Integer,Integer>addition_2 = (x,y)->{return x+y;};
      System.out.println("1 + 2 = "+addition_2.apply(1,2));
		
      System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
      System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
      System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
      System.out.println("10 / 5 = " + tester.operate(10, 5, division));
		
      //without parenthesis
      GreetingService greetService1 = message ->
      System.out.println("Hello " + message);
		
      //with parenthesis
      GreetingService greetService2 = (message) ->
      System.out.println("Hello " + message);
		
      greetService1.sayMessage("Mahesh");
      greetService2.sayMessage("Suresh");
   }

   @FunctionalInterface
   interface MathOperation { int operation(int a, int b);  }

   @FunctionalInterface
   interface GreetingService { void sayMessage(String message); }
	
   private int operate(int a, int b, MathOperation mathOperation) {
      return mathOperation.operation(a, b);
   }
}
