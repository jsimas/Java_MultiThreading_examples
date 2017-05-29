package examples5;

import java.util.Scanner;

public class Processor {

	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer thread running...");
			wait();
			System.out.println("Resumed");
		}
	}

	public void consume() throws InterruptedException {
		try (Scanner s = new Scanner(System.in)) {
			Thread.sleep(2000);
			synchronized (this) {
				System.out.println("Waiting for Return Key");
				s.nextLine();
				notify();
				System.out.println("Returned Key Pressed");
			}
		}
	}
}
