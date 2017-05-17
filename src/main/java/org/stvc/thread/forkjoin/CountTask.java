package org.stvc.thread.forkjoin;

import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 10;// 阈值

	private int start;
	private int end;

	public CountTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		// 如果任务足够小就计算任务
		boolean canCompute = (end - start) <= THRESHOLD;
		if (canCompute) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
			System.out.println("=计算" + start + "到" + end + "的结果为" + sum);
		} else {
			// 如果任务大于阀值，就分裂成两个子任务计算
			int middle = (start + end) / 2;
			System.out.println("/\\拆分成子任务" + start + "到" + middle + ";" + (middle + 1) + "到" + end);

			CountTask leftTask = new CountTask(start, middle);
			CountTask rightTask = new CountTask(middle + 1, end);

			// 执行子任务
			leftTask.fork();
			rightTask.fork();

			// 等待子任务执行完，并得到其结果
			int leftResult = leftTask.join();
			int rightResult = rightTask.join();

			// 合并子任务
			sum = leftResult + rightResult;
			System.out.println("\\/合并" + start + "到" + middle + ";" + (middle + 1) + "到" + end + "的计算结果为" + sum);
		}
		return sum;
	}

}
