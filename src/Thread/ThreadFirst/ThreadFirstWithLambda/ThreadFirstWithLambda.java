package Thread.ThreadFirst.ThreadFirstWithLambda;

import java.util.stream.IntStream;

public class ThreadFirstWithLambda {
    public static void main(String[] args) {
        new Thread(()->{
            IntStream.rangeClosed(1,100).forEach(x->{
                System.out.println(String.format("HelloWorld %2d ::",x)+Thread.currentThread().getName());
            });
            System.out.println(Thread.currentThread().getName() + " �� �켱���� : "+Thread.currentThread().getPriority());
        }).start();

        new Thread(()->{
            IntStream.rangeClosed(1,100).forEach(x->{
                System.out.println(String.format("HelloWorld %2d ::",x)+Thread.currentThread().getName());
            });
            System.out.println(Thread.currentThread().getName() + " �� �켱���� : "+Thread.currentThread().getPriority());
        }).start();
    }
}
