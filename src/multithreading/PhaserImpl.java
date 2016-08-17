package multithreading;

import java.util.concurrent.Phaser;

public class PhaserImpl {
	public static void main(String[] args) {
		Phaser phaser = new Phaser(1);
		System.out.println("starting...");

		new Worker("Waiter", phaser).start();
		new Worker("Chef", phaser).start();
		new Worker("Waitress", phaser).start();

		for (int i = 0; i <= 3; i++) {
			phaser.arriveAndAwaitAdvance();
			System.out.println("Order " + i + " finished!");
		}
		phaser.arriveAndDeregister();
		System.out.println("All done!");
	}
}

class Worker extends Thread {
	private Phaser phaser;

	public Worker(String name, Phaser phaser) {
		super(name);
		this.phaser = phaser;
		phaser.register();
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("current order is:" + i + ":" + getName());
			if (i == 3) {
				phaser.arriveAndDeregister();
			} else {
				phaser.arriveAndAwaitAdvance();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}