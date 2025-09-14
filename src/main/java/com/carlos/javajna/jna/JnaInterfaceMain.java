package com.carlos.javajna.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public interface JnaInterfaceMain extends Library {

	JnaInterfaceMain INSTANCE = Native.load("my_sdk", JnaInterfaceMain.class);

	int add(int a, int b);

	String get_message();

	// 结构体函数
	void get_person(Structs.Person.ByReference person);

	// 回调函数注册
	void register_callback(Callbacks.MyCallback callback);

	// 数组/指针函数
	int sum_array(IntByReference arr, int len);

	void process_buffer(Pointer buffer, int size);
}
