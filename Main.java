
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    int numThreads = 10000;
    int poolSize = Math.min(numThreads, Runtime.getRuntime().availableProcessors() * 2); // Adjust pool size
    long startTime = System.currentTimeMillis();

    ExecutorService executor = Executors.newFixedThreadPool(poolSize);
    for (int i = 0; i < numThreads; i++) {
      executor.submit(() -> {
        try {
          Thread.sleep(Duration.ofMillis(2)); // Adjust sleep time (optional)
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }

    executor.shutdown();
    executor.awaitTermination(10, TimeUnit.SECONDS);
    long elapsedTime = System.currentTimeMillis() - startTime;
    System.out.printf("Java Fixed Thread Pool (simulating virtual threads): Completed %d threads in %d ms\n",
        numThreads, elapsedTime);
  }
}
