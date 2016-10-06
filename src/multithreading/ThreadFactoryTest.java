package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class ThreadTask implements Runnable {

	int taskId;

	public ThreadTask(int taskId) {
		this.taskId = taskId;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "--taskId: " + taskId);

	}
}

class DaemonThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}

}

public class ThreadFactoryTest {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(3, new DaemonThreadFactory());
		for (int i = 0; i < 3; i++) {
			exec.submit(new ThreadTask(i));
		}
		exec.shutdown();
	}
}