package com.agualis.feature;

import com.agualis.file.FilePathSizeCalculator;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;

public class FileSizeCalculatorManualTest {

    public static void main(String[] args) {

        StopWatch stopwatch = new StopWatch();

        File javaFile = new File("/Users/agualis/github/");

        stopwatch.start();
        System.out.println(FilePathSizeCalculator.size(javaFile) + " bytes");
        stopwatch.stop();
        System.out.println(stopwatch.getTime() + " ms");

        stopwatch.reset();
        stopwatch.start();
        System.out.println(FilePathSizeCalculator.sizeBackwardsCompatible(javaFile) + " bytes");
        stopwatch.stop();
        System.out.println(stopwatch.getTime() + " ms");

    }

}
