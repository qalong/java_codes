package thread.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();

		// ����һ���������񣬸������1+2+3+4
		CountTask task = new CountTask(1, 320);

		// ִ��һ������
		Future<Integer> result = forkJoinPool.submit(task);

		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
