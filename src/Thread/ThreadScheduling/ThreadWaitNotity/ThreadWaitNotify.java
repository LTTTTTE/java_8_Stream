package Thread.ThreadScheduling.ThreadWaitNotity;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

class Account{
    private Long balance;

    public Account(Long balance) {
        this.balance = balance;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    synchronized public void addBalance(Long balance){
        System.out.println(String.format("%4d + %4d = %4d : %s",this.balance,balance,this.balance+balance,Thread.currentThread().getName()));
        this.balance = this.balance + balance;
        notifyAll();
    }

    synchronized public void subBalance(Long balance){

        while(this.balance < balance){

            try{
                System.out.println(String.format("%d < %d I'm WaitFor until Notify : %s",this.balance,balance,Thread.currentThread().getName()));
                wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(String.format("%4d - %4d = %4d : %s",this.balance,balance,this.balance-balance,Thread.currentThread().getName()));
        this.balance = this.balance - balance;
    }
}


public class ThreadWaitNotify {

    public static void main(String[] args) {

        Account account01 = new Account(500L);

        new Thread(()->{
            IntStream.rangeClosed(1,30).forEach(x->{
                account01.addBalance(ThreadLocalRandom.current().nextLong(100));
            });
        }).start();

        new Thread(()->{
            IntStream.rangeClosed(1,30).forEach(x->{
                account01.subBalance(ThreadLocalRandom.current().nextLong(80));
            });
        }).start();

        new Thread(()->{
            IntStream.rangeClosed(1,30).forEach(x->{
                account01.subBalance(ThreadLocalRandom.current().nextLong(71));
            });
        }).start();
    }
}
