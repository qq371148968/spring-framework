package com.zy;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


public class ZyTest {

	public static void main(String[] args) {
		System.out.println("1");
	}


	@Test
	void testZy() {
		System.out.println(1);
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigWithJdkProxy.class);
//		System.out.println(ctx.getBean("zyStudent1"));
		System.out.println(ctx.getBean("zhaoying"));

	}


	@ComponentScan("com.zy.example.scannable")
	@EnableAspectJAutoProxy
	static class ConfigWithJdkProxy {
	}
}
