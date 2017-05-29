package examples7;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Account acc1 = new Account();
	private Account acc2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	private void aquireLocks(Lock l1, Lock l2) throws InterruptedException {
		while(true) {
			
			//Acquire locks
			boolean gotFirstLock = false;
			boolean gotSecondLock = false; 
			try {
				gotFirstLock = l1.tryLock();
				gotSecondLock = l2.tryLock();
			} finally {
				if(gotFirstLock && gotSecondLock) {
					return;
				}
				
				if(gotFirstLock) {
					l1.unlock();
				}
				
				if(gotSecondLock) {
					l2.unlock();
				}
			}
			
			//Locks not acquired
			Thread.sleep(1);
		}
	}
	
	public void firstThread() throws InterruptedException {
		Random r = new Random();
		for (int i = 0; i < 10000; i++) {
			aquireLocks(lock1, lock2);
			try {
				Account.transfer(acc1, acc2, r.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void secondThread() throws InterruptedException {
		Random r = new Random();
		for (int i = 0; i < 10000; i++) {
			aquireLocks(lock2, lock1);
			try {
				Account.transfer(acc2, acc1, r.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}

		}
	}

	public void finished() {
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("Total Balance: " + (acc1.getBalance() + acc2.getBalance()));
	}
}
