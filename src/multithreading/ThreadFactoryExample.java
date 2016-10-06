package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadFactoryExample implements ThreadFactory {

	private final AtomicInteger count = new AtomicInteger(0);

	public Thread newThread(Runnable r) {

		int c = count.incrementAndGet();
		Thread t = new Thread(r);
		t.setName("test_thread_no." + c);

		System.out.println("Create new thread, thread name: " + t.getName());

		return t;
	}

	public static void main(String[] args) throws Exception {

		ExecutorService service = Executors.newFixedThreadPool(5, new ThreadFactoryExample());

		for (int i = 0; i < 5; i++) {
			service.submit(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + " : Start execute...");
				}
			});
		}
	}
}
