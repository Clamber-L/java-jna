//package com.carlos.javajna.jna;
//
//import com.sun.jna.Memory;
//import com.sun.jna.Pointer;
//import com.sun.jna.ptr.IntByReference;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JnaClassMain implements CommandLineRunner {
//
//	@Override
//	public void run(String... args) throws Exception {
//		int result = JnaInterfaceMain.INSTANCE.add(10, 20);
//		System.out.println("add(10, 20) = " + result);
//
//		String msg = JnaInterfaceMain.INSTANCE.get_message();
//		System.out.println("C++ message: " + msg);
//
//		// 结构体
//		Structs.Person.ByReference person = new Structs.Person.ByReference();
//		JnaInterfaceMain.INSTANCE.get_person(person);
//		person.read();
//		System.out.println("Person age=" + person.age);
//
//		// 回调示例
//		Callbacks.MyCallback callback = ((code, msg1) -> {
//			System.out.println("Callback code=" + code + " msg=" + msg);
//		});
//		JnaInterfaceMain.INSTANCE.register_callback(callback);
//
//		// 内存/指针操作示例
//		Memory mem = new Memory(1024);
//		mem.setInt(0, 123);
//		mem.setByte(4, (byte)0xFF);
//
//		Pointer p = mem;
//		JnaInterfaceMain.INSTANCE.process_buffer(p, 1024);
//
//		IntByReference ref = new IntByReference(10);
//		JnaInterfaceMain.INSTANCE.sum_array(ref, 1);
//		System.out.println(ref.getValue());
//	}
//}
