package Thread.ThreadTest.ThreadMutexTest;

import java.sql.Time;
import java.util.stream.IntStream;

class MyThread01 extends Thread{

    @Override
    public void run() {
        IntStream.rangeClosed(1,100).forEach(x->{
            System.out.println("number : " + ++ThreadMutexTest.var + " : " + this.getName() + " : " +(System.currentTimeMillis() - ThreadMutexTest.startTime)/1000.0);
        });
    }
}

class MyThread02 implements Runnable{
    @Override
    public void run() {
        IntStream.rangeClosed(1,100).forEach(x->{
            System.out.println("number : " + ++ThreadMutexTest.var + " : " + Thread.currentThread().getName() + " : " +(System.currentTimeMillis() - ThreadMutexTest.startTime)/1000.0);
        });
    }
}

public class ThreadMutexTest {

    public static long var = 0L;
    public static long startTime = 0L;

    public static void main(String[] args) {


        Thread thread01 = new MyThread01();
        Thread thread02 = new Thread(new MyThread02());


        thread01.start();
        thread02.start();

        new Thread(()->{
            IntStream.rangeClosed(1,100).forEach(x->{
                System.out.println("number : " + ++ThreadMutexTest.var + " : " + Thread.currentThread().getName() + " : " +(System.currentTimeMillis() - ThreadMutexTest.startTime)/10000.0);
            });
        }).start();
        startTime = System.currentTimeMillis();

    }

}
