package com.agualis.file;

public class UnknownFile implements FilePathSizeCalculator {
    @Override
    public long size() {
        return 0;
    }

    @Override
    public long sizeBackwardsCompatible() {
        return 0;
    }
}