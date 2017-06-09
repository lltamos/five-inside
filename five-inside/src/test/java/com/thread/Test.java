package com.thread;

public class Test {

	public static void main(String[] args) {

		
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				Nume n1 = new Nume();
				n1.set(1000);
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Nume n2 = new Nume();
					Thread.sleep(1000);
					System.err.println(n2.show());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start();

	}

}

class Nume {
	int count = 10;

	public void set(int x) {
		count	=x;
	}
	
	public int show(){
		return count;
	}
}