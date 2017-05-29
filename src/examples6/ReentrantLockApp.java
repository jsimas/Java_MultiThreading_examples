package examples6;

public class ReentrantLockApp {

	public static void main(String[] args) {
		Runner r = new Runner();
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					r.firstThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					r.secondThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
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
		
		r.finished();
		
	}
}
