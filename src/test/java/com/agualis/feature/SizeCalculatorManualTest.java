package com.agualis.feature;

import com.agualis.file.FilePathSizeCalculator;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;

public class SizeCalculatorManualTest {

    public static void main(String[] args) {
        FilePathSizeCalculator directorySizeCalculator = FilePathSizeCalculator.build(
                new File("/Users/agualis/github"));

        StopWatch stopwatch = new StopWatch();

        stopwatch.start();
        System.out.println(directorySizeCalculator.size() + " bytes");
        stopwatch.stop();
        System.out.println(stopwatch.getTime() + " ms");

        stopwatch.reset();
        stopwatch.start();
        System.out.println(directorySizeCalculator.sizeBackwardsCompatible() + " bytes");
        stopwatch.stop();
        System.out.println(stopwatch.getTime() + " ms");

    }

}
