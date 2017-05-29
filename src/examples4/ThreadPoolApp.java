package examples4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable  {

	private int id;
	
	public Processor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Start process with id " + this.id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ended process with id " + this.id);
	}
	
}

public class ThreadPoolApp {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; i++) {
			service.submit(new Processor(i));
		}
		service.shutdown();
		
		System.out.println("All tasks submited");
		
		try {
			service.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Completed all the processes");
	}
}
