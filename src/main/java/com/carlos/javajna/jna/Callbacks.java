package com.carlos.javajna.jna;

import com.sun.jna.Callback;

public class Callbacks {

	public interface MyCallback extends Callback {
		void invoke(int code, String msg);
	}
}
