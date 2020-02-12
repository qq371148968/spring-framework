package com.gupao.chapter2.proxy.dynamicproxy.cglibproxy;

import com.gupao.cglib.core.DebuggingClassWriter;

public class CglibTest {
	public static void main(String[] args) {

		try {

			System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"/Users/yingzhao/Documents/GitHub/spring-framework/zy-test/build/classes/java/main");
			Customer customer=(Customer)new CglibMeipo().getInstance(Customer.class);
			customer.findLove();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
