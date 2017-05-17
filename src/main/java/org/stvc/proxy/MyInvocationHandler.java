package org.stvc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object target;

	public MyInvocationHandler(Object target) {
		this.target = target;
	}

	public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
		System.out.println("the fish price higher");
		return (Integer) method.invoke(target, args) + 10;
	}

}