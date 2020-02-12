package com.gupao.asmTest;

import com.gupao.asm.Type;

public class TypeDescriptors {

	public static void testF(){
		System.out.println(1);
	}

	public static void main(String[] args) throws NoSuchMethodException {
		System.out.println(Type.getDescriptor(TypeDescriptors.class));
		System.out.println(Type.getDescriptor(String.class));
		System.out.println(Type.getMethodDescriptor(TypeDescriptors.class.getDeclaredMethod("testF")));
	}
}
