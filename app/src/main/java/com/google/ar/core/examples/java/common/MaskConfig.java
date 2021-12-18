package com.google.ar.core.examples.java.common;

import java.util.List;

public class MaskConfig {

    private static MaskConfig instance;
    public List<String[][]> maskConfig;

    private MaskConfig(List<String[][]> maskConfig) {
        this.maskConfig = maskConfig;
    }

    public static void setInstance(List<String[][]> maskConfig) {
        instance = new MaskConfig(maskConfig);
    }
    public static MaskConfig getInstance() {
        return instance;
    }

    public List<String[][]> getMaskConfig() {
        return maskConfig;
    }
}
