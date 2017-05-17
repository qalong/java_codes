package java7.try_with_resources;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws Exception {
		// try (FileInputStream fis = new
		// FileInputStream("/try_with_resources.txt"); BufferedInputStream
		// bufferedInput = new BufferedInputStream(fis)) {
		//
		// int data = bufferedInput.read();
		// while (data != -1) {
		// System.out.print((char) data);
		// data = bufferedInput.read();
		// }
		//
		// }

		try (TestAutoClosable t = new TestAutoClosable();) {
			t.doit();
		}
	}

	public static class TestAutoClosable implements AutoCloseable {

		public void doit() {
			System.out.println("DO IT!");
		}

		@Override
		public void close() throws Exception {
			System.out.println("AutoCloseable");
		}

	}
}
