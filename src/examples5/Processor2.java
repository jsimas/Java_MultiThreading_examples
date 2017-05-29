package examples5;

import java.util.LinkedList;
import java.util.Random;

public class Processor2 {

	private LinkedList<Integer> list = new LinkedList<>();
	private final static int LIMIT = 10;
	private Object lock = new Object();

	public void produce() throws InterruptedException {
		int value = 0;
		while (true) {
			synchronized (lock) {
				if (list.size() == LIMIT) {
					lock.wait();
				}
				list.add(value++);
				lock.notify();
			}
		}
	}

	public void consume() throws InterruptedException {
		while (true) {
			synchronized (lock) {
				if (list.size() == 0) {
					lock.wait();
				}
				System.out.print("List size: " + list.size());
				int value = list.removeFirst();
				System.out.println("; value is: " + value);
				lock.notify();
			}
			Thread.sleep(new Random().nextInt(1000));
		}
	}
}
