package com.gupao.chapter2.singleton;

public class LazySimpleSingleton {
	private LazySimpleSingleton(){}

	//静态块，公共内存区域
	private static LazySimpleSingleton lazy=null;

	public static LazySimpleSingleton getInstance(){
		if(lazy==null){
			lazy=new LazySimpleSingleton();
		}
		return lazy;
	}
}
