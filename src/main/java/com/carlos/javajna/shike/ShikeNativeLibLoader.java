//package com.carlos.javajna.shike;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//
//public class ShikeNativeLibLoader {
//
//    public static void load(String libName) {
//        try {
//            String dependency = "pthreadVC2";
//            String depPath = extractLibraryToTemp(dependency);
//            System.load(depPath);
//
//            String dependency2 = "QRNetSDK";
//            String depPath2 = extractLibraryToTemp(dependency2);
//            System.load(depPath2);
//
//            String mainPath = extractLibraryToTemp(libName);
//            System.load(mainPath);
//
//            System.out.println("[ShikeNativeLibLoader] Successful loaded " + libName);
//        }catch (IOException e) {
//            throw new RuntimeException("Fail to load native lib:" + libName, e);
//        }
//    }
//
//    private static String extractLibraryToTemp(String libName) throws IOException {
//        String path = getLibraryPath(libName);
//        try (InputStream in = NativeLibLoader.class.getResourceAsStream(path)){
//            if (in == null) {
//                throw new UnsatisfiedLinkError("Cannot find native library in resources: " + path);
//            }
//            File temp = File.createTempFile(libName, getLibraryExtension());
//            temp.deleteOnExit();
//            Files.copy(in, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
//            return temp.getAbsolutePath();
//        }
//    }
//
//    private static String getLibraryPath(String libName) {
//        return "/natives/" + getLibraryFileName(libName);
//    }
//
//    public static String getLibraryFileName(String libName) {
//        String os = System.getProperty("os.name").toLowerCase();
//        if (os.contains("win")) return libName + ".dll";
//        if (os.contains("mac")) return "lib" + libName + ".dylib";
//        return "lib" + libName + ".so";
//    }
//
//    private static String getLibraryExtension() {
//        String os = System.getProperty("os.name").toLowerCase();
//        if (os.contains("win")) return ".dll";
//        if (os.contains("mac")) return ".dylib";
//        return ".so";
//    }
//}
