package thread.forkjoin;

import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 10;// ��ֵ

	private int start;
	private int end;

	public CountTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		// ��������㹻С�ͼ�������
		boolean canCompute = (end - start) <= THRESHOLD;
		if (canCompute) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
			System.out.println("=����" + start + "��" + end + "�Ľ��Ϊ" + sum);
		} else {
			// ���������ڷ�ֵ���ͷ��ѳ��������������
			int middle = (start + end) / 2;
			System.out.println("/\\��ֳ�������" + start + "��" + middle + ";" + (middle + 1) + "��" + end);

			CountTask leftTask = new CountTask(start, middle);
			CountTask rightTask = new CountTask(middle + 1, end);

			// ִ��������
			leftTask.fork();
			rightTask.fork();

			// �ȴ�������ִ���꣬���õ�����
			int leftResult = leftTask.join();
			int rightResult = rightTask.join();

			// �ϲ�������
			sum = leftResult + rightResult;
			System.out.println("\\/�ϲ�" + start + "��" + middle + ";" + (middle + 1) + "��" + end + "�ļ�����Ϊ" + sum);
		}
		return sum;
	}

}
