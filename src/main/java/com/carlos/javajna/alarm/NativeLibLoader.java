package com.carlos.javajna.alarm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class NativeLibLoader {

    /**
     * 解压 DLLs 到固定目录，并加载
     */
    private static File nativeDir;

    public static void loadAll(String... libNames) {
        try {
            prepareNativeDir();

            for (String libName : libNames) {
                String fileName = getLibraryFileName(libName);
                File targetFile = new File(nativeDir, fileName);

                // 如果文件不存在，解压
                if (!targetFile.exists()) {
                    try (InputStream in = NativeLibLoader.class.getResourceAsStream("/natives/" + fileName)) {
                        if (in == null) {
                            throw new UnsatisfiedLinkError("Cannot find native library in resources: " + fileName);
                        }
                        Files.copy(in, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
                System.load(targetFile.getAbsolutePath());
                System.out.println("[NativeLibLoader] Loaded " + targetFile.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException("Fail to load native libraries", e);
        }
    }

    private static void prepareNativeDir() throws IOException {
        if (nativeDir == null) {
            // 可以放到 target/natives 或用户目录下 temp
            nativeDir = new File(System.getProperty("user.dir"), "target/natives");
            if (!nativeDir.exists()) nativeDir.mkdirs();

            // 可选：把目录加入 java.library.path，确保依赖 DLL 可见
            String path = System.getProperty("java.library.path");
            System.setProperty("java.library.path", nativeDir.getAbsolutePath() + ";" + path);
        }
    }

    public static String getLibraryFileName(String libName) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) return libName + ".dll";
        if (os.contains("mac")) return "lib" + libName + ".dylib";
        return "lib" + libName + ".so";
    }
}

