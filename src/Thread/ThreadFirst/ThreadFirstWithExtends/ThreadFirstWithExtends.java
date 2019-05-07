package Thread.ThreadFirst.ThreadFirstWithExtends;

import java.util.stream.IntStream;

class MyThread01 extends Thread{
    @Override
    public void run() {
        IntStream.rangeClosed(1,100).forEach(x->{
            System.out.println(String.format("HelloWorld %2d:: ",x) +this.getName());
        });
        System.out.println(this.getName()+ " 의 우선순위 : (main/default == 5) : " +  currentThread().getPriority());
    }
}

public class ThreadFirstWithExtends {
    public static void main(String[] args) {
        MyThread01 thread01 = new MyThread01();
        MyThread01 thread02 = new MyThread01();
        thread01.start();
        thread02.start();
    }
}
