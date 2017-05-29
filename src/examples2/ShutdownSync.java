package examples2;

import java.util.Scanner;

class Processor extends Thread {
	
	private volatile boolean running = true;
	
	@Override
	public void run() {
		while(running) {
			System.out.println("Still Running...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		this.running = false;
	}
}

public class ShutdownSync {

	public static void main(String[] args) {
		Processor p = new Processor();
		p.start();	
		
		System.out.println("Please return to stop...");
		Scanner s = new Scanner(System.in);
		s.nextLine();
		p.shutdown();
		System.out.println("Completed");
	}
	
}
