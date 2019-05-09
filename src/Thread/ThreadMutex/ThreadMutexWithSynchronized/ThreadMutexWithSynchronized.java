package Thread.ThreadMutex.ThreadMutexWithSynchronized;

import java.util.stream.IntStream;

class MyThread01 extends Thread{
    @Override
    public void run() {
        ThreadMutexWithSynchronized.printNum();
    }
}

class MyThread02 implements Runnable{
    @Override
    public void run() {
        ThreadMutexWithSynchronized.printNum();
    }
}


public class ThreadMutexWithSynchronized {

    public static Long number=0L;

    synchronized public static void printNum(){
        IntStream.rangeClosed(1,300).forEach(x->{
            System.out.println("number : " + ++ThreadMutexWithSynchronized.number + " : " + Thread.currentThread().getName());
        });
    }

    public static void main(String[] args) {

        Thread thread01 = new MyThread01();
        Thread thread02 = new Thread(new MyThread02());

        thread01.start();
        thread02.start();

    }
}
