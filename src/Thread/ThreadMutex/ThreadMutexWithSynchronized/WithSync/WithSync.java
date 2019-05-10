package Thread.ThreadMutex.ThreadMutexWithSynchronized.WithSync;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

//Copy from me

class FooObject {

    private Long number;

    public FooObject(Long number) {
        this.number = number;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    synchronized void addNumber(Long number){
        this.number = this.number + number;
        System.out.println(number + " ´õÇÔ ÃÑ : "+this.number);
    }

    synchronized boolean subNumber(Long number){

        if(this.number > number){
            this.number = this.number - number;
            System.out.println(number + " »­  ÃÑ : " + this.number);
            return true;
        }
        else {
            System.out.println(this.number + " < " + number +" ¸ø»­");
            return false;
        }
    }
}


public class WithSync {

    public static void checkNegativeNumber(Long number){
        if(number < 0) {
            System.out.println("*****************************************");
            new Exception().printStackTrace();
        }
    }

    public static void main(String[] args) {

        FooObject obj = new FooObject(0L);

        new Thread(()->{
            IntStream.rangeClosed(1,100).forEach(x->{
                obj.addNumber(ThreadLocalRandom.current().nextLong(100));
            });
        }).start();

        new Thread(()->{
            IntStream.rangeClosed(1,100).forEach(x->{
                obj.subNumber(ThreadLocalRandom.current().nextLong(30,130));
            });
        }).start();

        new Thread(()->{
            IntStream.rangeClosed(1,100).forEach(x->{
                obj.subNumber(ThreadLocalRandom.current().nextLong(20,120));
            });
        }).start();

        new Thread(()->{
            checkNegativeNumber(obj.getNumber());
        }).start();
    }
}

