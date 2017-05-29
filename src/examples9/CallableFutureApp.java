package examples9;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureApp {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Future<Integer> f = exec.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("Thread started");
				int sleepDurantion = new Random().nextInt(4000);
				
				if(sleepDurantion > 2000) {
					throw new IOException("Sleeping for too long...");
				}
				try {
					Thread.sleep(sleepDurantion);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread finished");
				return sleepDurantion;
			}
		});

		exec.shutdown();
		
		try {
			System.out.println("Sleep value: " + f.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
