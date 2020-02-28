package com.zy.example.scannable;

public class Student {
	private static final long serialVersionUID = -2088281526481179972L;
	private int id;
	private String name;
	private int age;

	public Student(int id,String name,int age){
		this.id=id;
		this.name=name;
		this.age=age;
	}

	@Override
	public String toString() {
		return "id="+id+",name="+name+",age="+age;
	}
}
