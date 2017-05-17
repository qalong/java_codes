package mutilcatch;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.util.Random;

public class MutilCatch {

	public static void main(String[] args) {
		try {
			if (new Random().nextBoolean())// 不一定会报什么错
				throw new EOFException();
			else
				throw new FileNotFoundException();

		} catch (FileNotFoundException | EOFException e) {
			e.printStackTrace();
			/* 记录到log中 */
		}
	}
}
