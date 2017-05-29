package examples3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkerBad {

	private Random random = new Random();
	
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();
	
	public void stage1() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list1.add(random.nextInt(100));
	}
	
	public void stage2() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list2.add(random.nextInt(100));
	}
	
	public void process() {
		for (int i = 0; i < 1000; i++) {
			stage1();
			stage2();
		}
	}
	
	public void main() {
		System.out.println("Hello");
		long start = System.currentTimeMillis();
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				process();
				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				process();
				
			}
		});
		t.start();
		t2.start();
		try {
			t.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Took: " + (System.currentTimeMillis() - start));
		System.out.println("List 1 size:" + list1.size() + ", List 2 size:" + list2.size());
	}

}
