package examples4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ProcessorLatch implements Runnable {
	private CountDownLatch latch;

	public ProcessorLatch(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Started");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();
	}
}

public class CountdownLatchesAPp {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);
		
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		for(int i = 0; i < 3; i++) {
			service.submit(new ProcessorLatch(latch));			
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		System.out.println("Completed");
	}
}
