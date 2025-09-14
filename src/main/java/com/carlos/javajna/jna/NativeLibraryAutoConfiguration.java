package com.carlos.javajna.jna;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class NativeLibraryAutoConfiguration {

	@PostConstruct
	public void initNativeLibrary() {
		NativeLibLoader.load("my_sdk");
	}
}
