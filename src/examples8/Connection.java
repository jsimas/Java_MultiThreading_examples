package examples8;

import java.util.concurrent.Semaphore;

public class Connection {

	private static Connection instance = new Connection();

	private Semaphore sem = new Semaphore(10, true);

	private int count = 0;

	public static Connection getInstance() {
		return instance;
	}

	private Connection() {

	}

	public void connect() {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			doConnect();
		} finally {
			sem.release();
		}
	}

	public void doConnect() {

		synchronized (this) {
			count++;
			System.out.println("Current connection count: " + count);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (this) {
			count--;
		}

	}

}
