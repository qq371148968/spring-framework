package com.gupao.chapter2.observer.guava;

import com.google.common.eventbus.EventBus;


public class GuavaEventTest {
	public static void main(String[] args) {

		EventBus bus=new EventBus();
		GuavaEvent event=new GuavaEvent();

		bus.register(event);

		bus.post("zhaoying");
		System.out.println("1");
	}
}
