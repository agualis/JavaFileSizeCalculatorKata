package com.agualis.file;

import java.io.File;

public class FileSizeCalculator implements FilePathSizeCalculator {
    private File javaFile;

    public FileSizeCalculator(File javaFile) {
        this.javaFile = javaFile;
    }

    @Override
    public long size() {
        return javaFile.length();
    }

    @Override
    public long sizeBackwardsCompatible() {
        return javaFile.length();
    }
}
