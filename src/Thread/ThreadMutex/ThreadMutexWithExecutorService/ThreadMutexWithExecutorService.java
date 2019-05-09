package Thread.ThreadMutex.ThreadMutexWithExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadMutexWithExecutorService {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(()->{
            System.out.println();
        });
    }
}
