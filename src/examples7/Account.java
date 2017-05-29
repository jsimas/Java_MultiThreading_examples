package examples7;

public class Account {

	private int balance = 10000;

	public void deposit(int ammount) {
		this.balance += ammount;
	}

	public void withdraw(int ammount) {
		this.balance -= ammount;
	}

	public static void transfer(Account acc1, Account acc2, int amount) {
		acc1.withdraw(amount);
		acc2.deposit(amount);
	}

	public int getBalance() {
		return balance;
	}
}
