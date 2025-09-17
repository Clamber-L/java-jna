package com.carlos.javajna.alarm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class NativeLoadConfig {

    @Value("${app.natives-path}")
    private String nativesPath;

    @Value("#{'${app.libraries}'.split(',')}")
    private List<String> libraries;

    public String getNativesPath() {
        return nativesPath;
    }

    public void setNativesPath(String nativesPath) {
        this.nativesPath = nativesPath;
    }

    public List<String> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<String> libraries) {
        this.libraries = libraries;
    }
}
