package com.agualis.file;

import java.io.File;

public interface FilePathSizeCalculator {

    static FilePathSizeCalculator build(File javaFile) {
        if (javaFile.isFile()) {
            return new FileSizeCalculator(javaFile);
        } else {
            return new DirectorySizeCalculator(javaFile);
        }
    }

    static long size(File javaFile) {
        return FilePathSizeCalculator.build(javaFile).size();
    }

    static long sizeBackwardsCompatible(File javaFile) {
        return FilePathSizeCalculator.build(javaFile).sizeBackwardsCompatible();
    }

    public long size();
    public long sizeBackwardsCompatible();

}
