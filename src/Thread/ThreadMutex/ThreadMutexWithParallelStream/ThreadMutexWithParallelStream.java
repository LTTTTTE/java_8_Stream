package Thread.ThreadMutex.ThreadMutexWithParallelStream;

import java.util.stream.IntStream;

class Printer {

    public void print(){
        IntStream.rangeClosed(1,300).parallel().forEach(x->{
            System.out.println("number : " + ++ThreadMutexWithParallelStream.number + " : " + Thread.currentThread().getName());
        });
    }
}

class MyThread01 extends Thread{
    @Override
    public void run() {
        new Printer().print();
    }
}

class MyThread02 implements Runnable{
    @Override
    public void run() {
        new Printer().print();
    }
}

public class ThreadMutexWithParallelStream {
    public static volatile Long number = 0L;

    public static void main(String[] args) {

        Thread thread01 = new MyThread01();
        Thread thread02 = new Thread(new MyThread02());

        thread01.start();
        thread02.start();
    }
}
