//package com.carlos.javajna.jna;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//
//public class NativeLibLoader {
//
//	public static void load(String libName) {
//		try {
//			String path = getLibraryPath(libName);
//			System.out.println("[NativeLibLoader] Loading " + path);
//
//			InputStream in = NativeLibLoader.class.getResourceAsStream(path);
//			if (in == null) {
//				throw new UnsatisfiedLinkError("Cannot find native library in resources:" + path);
//			}
//			File temp = File.createTempFile(libName, getLibraryExtension());
//			temp.deleteOnExit();
//			Files.copy(in, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
//			System.load(temp.getAbsolutePath());
//		}catch (IOException e) {
//			throw new RuntimeException("Fail to load native lib:" + libName, e);
//		}
//	}
//
//	private static String getLibraryPath(String libName) {
//		return "/natives/" + getLibraryFileName(libName);
//	}
//
//	private static String getLibraryFileName(String libName) {
//		String os = System.getProperty("os.name").toLowerCase();
//		if (os.contains("win")) return libName + ".dll";
//		if (os.contains("mac")) return "lib" + libName + ".dylib";
//		return "lib" + libName + ".so";
//	}
//
//	private static String getLibraryExtension() {
//		String os = System.getProperty("os.name").toLowerCase();
//		if (os.contains("win")) return ".dll";
//		if (os.contains("mac")) return ".dylib";
//		return ".so";
//	}
//}
