package lock.stampedlock;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
	private static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		lock.lock();
	}

}
