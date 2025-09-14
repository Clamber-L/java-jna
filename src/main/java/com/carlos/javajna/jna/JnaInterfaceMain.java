package com.carlos.javajna.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface JnaClassMain extends Library {

	JnaClassMain INSTANCE = Native.load("my_sdk", JnaClassMain.class);

	int add(int a, int b);
	
	String get_message();
}
