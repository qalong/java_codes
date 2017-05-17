package lock.stampedlock;

import java.util.concurrent.locks.StampedLock;

class Point {
	private double x, y;
	private final StampedLock sl = new StampedLock();

	void move(double deltaX, double deltaY) { // an exclusively locked method
		long stamp = sl.writeLock();
		try {
			x += deltaX;
			y += deltaY;
		} finally {
			sl.unlockWrite(stamp);
		}
	}

	// ���濴���ֹ۶�������
	double distanceFromOrigin() { // A read-only method
		long stamp = sl.tryOptimisticRead(); // ���һ���ֹ۶���
		double currentX = x, currentY = y; // �������ֶζ��뱾�ؾֲ�����
		if (!sl.validate(stamp)) { // ��鷢���ֹ۶�����ͬʱ�Ƿ�������д��������
			stamp = sl.readLock(); // ���û�У������ٴλ��һ����������
			try {
				currentX = x; // �������ֶζ��뱾�ؾֲ�����
				currentY = y; // �������ֶζ��뱾�ؾֲ�����
			} finally {
				sl.unlockRead(stamp);
			}
		}
		return Math.sqrt(currentX * currentX + currentY * currentY);
	}

	// �����Ǳ��۶�������
	void moveIfAtOrigin(double newX, double newY) { // upgrade
		// Could instead start with optimistic, not read mode
		long stamp = sl.readLock();
		try {
			while (x == 0.0 && y == 0.0) { // ѭ������鵱ǰ״̬�Ƿ����
				long ws = sl.tryConvertToWriteLock(stamp); // ������תΪд��
				if (ws != 0L) { // ����ȷ��תΪд���Ƿ�ɹ�
					stamp = ws; // ����ɹ� �滻Ʊ��
					x = newX; // ����״̬�ı�
					y = newY; // ����״̬�ı�
					break;
				} else { // ������ܳɹ�ת��Ϊд��
					sl.unlockRead(stamp); // ������ʽ�ͷŶ���
					stamp = sl.writeLock(); // ��ʽֱ�ӽ���д�� Ȼ����ͨ��ѭ������
				}
			}
		} finally {
			sl.unlock(stamp); // �ͷŶ�����д��
		}
	}
}