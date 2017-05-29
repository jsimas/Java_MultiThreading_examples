package examples2;

public class SyncWord_Bad {

	private int count = 0;
	
	public static void main(String[] args) {
		SyncWord_Bad s = new SyncWord_Bad();
		s.doWork();
	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100000; i++) {
					count++;
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100000; i++) {
					count++;
				}
				
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		
		System.out.println("Final Count: " + count);
	}
}
