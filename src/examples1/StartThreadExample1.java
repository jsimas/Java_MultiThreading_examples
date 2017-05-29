package examples1;

class Runner extends Thread {
	
	private String name;
		
	public Runner(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("[" + this.name + "] Iteration " + i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


public class StartThreadExample1 {

	public static void main(String[] args) {
		Runner runner = new Runner("Thread1");
		runner.start();
		
		Runner runner2 = new Runner("Thread2");
		runner2.start();

	}

}
