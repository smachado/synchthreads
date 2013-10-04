package edu.upc.eetac.dsa.smachado.synchthreads;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ExampleNotSynchronized implements Runnable {
	private final static String LORE_IPSUM = "Lorem ipsum dolor sit amet";
	private boolean uppercase;
	private static PrintWriter writer = null;
	static {
		try {
			writer = new PrintWriter("output-notsynch.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ExampleNotSynchronized(boolean uppercase) {
		super();
		this.uppercase = uppercase;
	}

	private static void writeLoreIpsum(String loreIpsum) {
		StringTokenizer st = new StringTokenizer(loreIpsum);
		while(st.hasMoreTokens())
			writer.println(st.nextToken());
	}

	public void run() {
		if (uppercase)
			for (int i = 0; i < 10; i++) {
				writeLoreIpsum(LORE_IPSUM.toUpperCase());
			}
		else
			for (int i = 0; i < 10; i++) {
				writeLoreIpsum(LORE_IPSUM.toLowerCase());
			}

	}

	public static void main(String args[]) {
		Thread thUpperCase = new Thread(new ExampleNotSynchronized(true));
		Thread thLowerCase = new Thread(new ExampleNotSynchronized(false));
		thUpperCase.start();
		thLowerCase.start();
		
		try {
			thUpperCase.join();
			thLowerCase.join();
			
			writer.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
