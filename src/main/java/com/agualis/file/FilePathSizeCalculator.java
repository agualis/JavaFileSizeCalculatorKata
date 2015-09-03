package com.agualis.file;

import java.io.File;

public interface FilePathSizeCalculator {

    static FilePathSizeCalculator build(File javaFile) {
        if (javaFile.isFile()) {
            return new FileSizeCalculator(javaFile);
        } else if (javaFile.isDirectory()){
            return new DirectorySizeCalculator(javaFile);
        } else {
            return new UnknownFile();
        }
    }

    public long size();
    public long sizeBackwardsCompatible();
}
