package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ClientTest {
	public static void main(String args[]) {
		SellFisher s = new SellFisherImpl();
		InvocationHandler p = new MyInvocationHandler(s);
		Object obj = Proxy.newProxyInstance(s.getClass().getClassLoader(), s.getClass().getInterfaces(), p);
		System.out.println("result:" + ((SellFisher) obj).sellFish());
		;
	}
}