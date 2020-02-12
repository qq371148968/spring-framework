package com.gupao.chapter2.proxy.dynamicproxy.cglibproxy;


import com.gupao.cglib.proxy.Enhancer;
import com.gupao.cglib.proxy.MethodInterceptor;
import com.gupao.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibMeipo implements MethodInterceptor {

	public Object getInstance(Class<?> clazz) throws Exception{
		Enhancer enhancer=new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);

		return enhancer.create();
	}
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		before();
		Object obj=methodProxy.invokeSuper(o,objects);
		after();
		return obj;
	}

	private void before(){
		System.out.println("我是before");
		System.out.println("开始工作准备");
	}

	private void after(){
		System.out.println("我是结束");
		System.out.println("结束工作");
	}
}
