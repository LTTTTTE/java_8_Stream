package Thread.ThreadScheduling.ThreadWaitNotify02;

class FooObject{
    private Long index;

    public FooObject(Long number) {
        this.index = number;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    synchronized public void addIndex(Long index){
        this.index = this.index + index;
    }

    public void subIndex(Long index){

        synchronized (this){
            if(getIndex()>0) {

                System.out.print(this.getIndex() + " - " + index + " = ");
                this.index = this.index - index;
                System.out.println(this.getIndex() + " : " + Thread.currentThread().getName());
            }
        }
    }
}

class MyThread implements Runnable{

    private FooObject object;

    @Override
    public void run() {
        while(object.getIndex()>0)
            object.subIndex(1L);
    }

    public MyThread(FooObject object) {
        this.object = object;
    }
}

public class ThreadWaitNotify02 {
    public static void main(String[] args) {

        FooObject foo01 = new FooObject(1000L);

        new Thread(()->{
            while(foo01.getIndex()>0) {
                foo01.subIndex(1L);
            }
        }).start();

        new Thread(()->{
            while(foo01.getIndex()>0) {
                foo01.subIndex(1L);
            }
        }).start();

        Thread thread01 = new Thread(new MyThread(foo01));
        Thread thread02 = new Thread(new MyThread(foo01));
        thread01.start();
        thread02.start();

    }
}
