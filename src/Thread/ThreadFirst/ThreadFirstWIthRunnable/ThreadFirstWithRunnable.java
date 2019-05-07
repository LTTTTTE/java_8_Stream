package Thread.ThreadFirst.ThreadFirstWIthRunnable;

import java.util.stream.IntStream;

class MyThread01 implements Runnable{
    @Override
    public void run() {
        IntStream.rangeClosed(1,100).forEach(x->{
            System.out.println(String.format("HelloWorld %2d::",x)+Thread.currentThread().getName());
        });
        System.out.println(Thread.currentThread().getName() + " 의 우선순위 : "+Thread.currentThread().getPriority());
    }
}

public class ThreadFirstWithRunnable {
    public static void main(String[] args) {
        Runnable runnable = new MyThread01();
        Thread thread01 = new Thread(runnable);
        //또는
        Thread thread02 = new Thread(new MyThread01());

        thread01.start();
        thread02.start();

    }
}
