package com.carlos.javajna.alarm;

import java.io.File;

public class NativeLibLoader {
    public static void loadFromDir(String dir, String... libNames) {
        for (String libName : libNames) {
            String fileName = libName.endsWith(".dll") ? libName : libName + ".dll";
            File file = new File(dir, fileName);
            if (!file.exists()) {
                throw new UnsatisfiedLinkError("Native library not found: " + file.getAbsolutePath());
            }
            System.load(file.getAbsolutePath());
            System.out.println("[NativeLibLoader] Loaded " + file.getAbsolutePath());
        }
    }
}

