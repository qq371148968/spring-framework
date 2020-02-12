package com.gupao.asmTest;

import com.gupao.asm.ClassReader;
import com.gupao.asm.ClassVisitor;
import com.gupao.asm.ClassWriter;
import com.gupao.asm.MethodVisitor;
import com.gupao.asm.Opcodes;
import com.gupao.asm.Type;
import com.gupao.asm.commons.AdviceAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class TestASM {
	public static void main(String[] args) throws IOException {
		ClassReader classReader = new ClassReader("com.gupao.asmTest.OrderStarter");
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ClassVisitor classVisitor = new TestClassVisitor(classWriter);
		classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);

		byte[] classFile = classWriter.toByteArray();
		File file = new File("/Users/yingzhao/Documents/GitHub/spring-framework/zy-test/build/classes/java/main/com/gupao/asmTest/OrderStarter.class");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(classFile);
		fos.close();
	}

	private static class TestClassVisitor extends ClassVisitor {

		public TestClassVisitor(ClassVisitor classVisitor) {
			super(Opcodes.ASM6, classVisitor);
		}

		@Override
		public MethodVisitor visitMethod(int access, String name,
										 String desc, String signature,
										 String[] exceptions) {
			System.out.println("visitMethod: " + name);
			MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
			if ("<init>".equals(name)) {
//                mv = new MainMethodVisitor(mv);
				mv = new MainMethodAdapter(mv,access,name,desc);
			}
			return mv;
		}
	}

	private static class MainMethodVisitor extends MethodVisitor {

		public MainMethodVisitor(MethodVisitor methodVisitor) {
			super(Opcodes.ASM6, methodVisitor);
		}

		private void sop(String msg) {
			mv.visitFieldInsn(Opcodes.GETSTATIC,
					Type.getInternalName(System.class), //"java/lang/System"
					"out",
					Type.getDescriptor(PrintStream.class) //"Ljava/io/PrintStream;"
			);
			mv.visitLdcInsn(msg);
			mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
					Type.getInternalName(PrintStream.class),
					"println",
					"(Ljava/lang/String;)V",
					false);
		}

		@Override
		public void visitCode() {
			mv.visitCode();
			System.out.println("method start to insert code");
			sop("asm insert before");
		}

		@Override
		public void visitInsn(int opcode) {
			if (opcode == Opcodes.RETURN) {
				System.out.println("method end to insert code");
				sop("asm insert after");
			}
			mv.visitInsn(opcode);
		}
	}


	private static class MainMethodAdapter extends AdviceAdapter {

		/**
		 * Constructs a new {@link AdviceAdapter}.
		 *
		 * @param mv     the method visitor to which this adapter delegates calls.
		 * @param access the method's access flags (see {@link Opcodes}).
		 * @param name   the method's name.
		 * @param desc   the method's descriptor (see {@link Type Type}).
		 */
		protected MainMethodAdapter(MethodVisitor mv, int access, String name, String desc) {
			super(Opcodes.ASM6, mv, access, name, desc);
		}

		@Override
		protected void onMethodEnter() {
			super.onMethodEnter();
			sop("AdviceAdater: asm insert code");
		}

		@Override
		protected void onMethodExit(int opcode) {
			super.onMethodExit(opcode);
			sop("AdviceAdater: asm insert code");
		}

		private void sop(String msg) {
			mv.visitFieldInsn(Opcodes.GETSTATIC,
					Type.getInternalName(System.class), //"java/lang/System"
					"out",
					Type.getDescriptor(PrintStream.class) //"Ljava/io/PrintStream;"
			);
			mv.visitLdcInsn(msg);
			mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
					Type.getInternalName(PrintStream.class),
					"println",
					"(Ljava/lang/String;)V",
					false);
		}
	}

}