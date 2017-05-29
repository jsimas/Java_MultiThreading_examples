package examples1;


class Runner2 implements Runnable {
	
	private String name;
		
	public Runner2(String name) {
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


public class StartThreadExample2 {

	public static void main(String[] args) {
		new Thread(new Runner2("Thread1")).start();
		new Thread(new Runner2("Thread2")).start();

	}

}
