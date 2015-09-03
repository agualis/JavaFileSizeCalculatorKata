package com.agualis.file;

import java.io.File;
import java.util.Arrays;

public class DirectorySizeCalculator implements FilePathSizeCalculator {
    private File javaFile;

    protected DirectorySizeCalculator(File javaFile) {
        this.javaFile = javaFile;
    }

    public long size() {
        if (isEmptyDirectory()) return 0;

        return Arrays.stream(javaFile.listFiles())
                .parallel()
                .mapToLong(file -> FilePathSizeCalculator.build(file).size())
                .sum();
    }

    public long sizeBackwardsCompatible() {
        //In case we cannot use Java8 Streams
        if (isEmptyDirectory()) return 0;

        long result = 0;
        for (File file : javaFile.listFiles()) {
            result += FilePathSizeCalculator.build(file).sizeBackwardsCompatible();
        }
        return result;
    }

    private boolean isEmptyDirectory() {
        //listFiles can be null if we don't have right permissions
        if (javaFile.listFiles() == null) return true;
        if (javaFile.listFiles().length == 0 ) return true;
        return false;
    }
}
