package Thread.ThreadScheduling.ThreadSchedulingMethods;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

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

    synchronized public void addNumber(Long number){
        this.number = this.number + number;
        System.out.println(number + " ´õÇÔ ÃÑ : "+this.number);
    }

    synchronized public boolean subNumber(Long number){

        if(this.number<0)
            new Exception().printStackTrace();

        if(this.number > number){
            System.out.println(number + " »­  ÃÑ : " + this.number);
            this.number = this.number - number;
            return true;
        }
        else {
            System.out.println(String.format("%3d > %3d ¸ø»­",number,this.number));
            return false;
        }
    }
}


public class ThreadSchedulingMethods {

    public static void checkNegativeNumber(Long number){
        if(number < 0L) {
            System.out.println("*****************************************");
            new Exception().printStackTrace();
        }
    }

    public static void main(String[] args) {

        FooObject obj = new FooObject(0L);

        new Thread(()->{
            IntStream.rangeClosed(1,100).forEach(x->{
                obj.addNumber(ThreadLocalRandom.current().nextLong(100));
                try {
                    Thread.sleep(10);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }).start();

        new Thread(()->{
            IntStream.rangeClosed(1,100).forEach(x->{
                obj.subNumber(ThreadLocalRandom.current().nextLong(30,130));
                try {
                    Thread.sleep(10);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }).start();

        new Thread(()->{
            IntStream.rangeClosed(1,100).forEach(x->{
                obj.subNumber(ThreadLocalRandom.current().nextLong(20,120));
                try {
                    Thread.sleep(10);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }).start();

        new Thread(()->{
            checkNegativeNumber(obj.getNumber());
        }).start();
    }
}
