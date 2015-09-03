package com.agualis.file;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileSizeCalculatorTest {

    @Mock File javaRootDir;
    @Mock File javaSubDir;
    @Mock File javaFile;
    @Mock File javaFile2;
    @Mock File javaFile3;
    @Mock File javaUnknownFile;

    public static final long FAKE_FILE_SIZE = 10;
    public static final long FAKE_FILE_SIZE2 = 5;
    public static final long FAKE_FILE_SIZE3 = 3;

    @Before
    public void init() {
        when(javaFile.isFile()).thenReturn(true);
        when(javaFile2.isFile()).thenReturn(true);
        when(javaFile3.isFile()).thenReturn(true);
        when(javaFile.length()).thenReturn(FAKE_FILE_SIZE);
        when(javaFile2.length()).thenReturn(FAKE_FILE_SIZE2);
        when(javaFile3.length()).thenReturn(FAKE_FILE_SIZE3);

        when(javaRootDir.isDirectory()).thenReturn(true);
        when(javaRootDir.isFile()).thenReturn(false);
        when(javaSubDir.isFile()).thenReturn(false);
        when(javaSubDir.isDirectory()).thenReturn(true);

    }


    @Test public void
    empty_directory() {
        when(javaRootDir.isFile()).thenReturn(false);
        when(javaRootDir.listFiles()).thenReturn(new File[0]);

        assertThat(FilePathSizeCalculator.size(javaRootDir), is(0L));
    }

    @Test public void
    one_file() {
        FilePathSizeCalculator directorySizeCalculator =  FilePathSizeCalculator.build(javaFile);

        assertThat(directorySizeCalculator.size(), is(FAKE_FILE_SIZE));
    }

    @Test public void
    one_file_flat_directory() {
        when(javaRootDir.isFile()).thenReturn(false);
        when(javaRootDir.listFiles()).thenReturn(new File[]{javaFile});
        when(javaRootDir.length()).thenReturn(FAKE_FILE_SIZE);

        assertThat(FilePathSizeCalculator.size(javaRootDir), is(FAKE_FILE_SIZE));
    }

    @Test public void
    several_files_flat_directory() {
        when(javaRootDir.isFile()).thenReturn(false);
        when(javaRootDir.listFiles()).thenReturn(new File[]{javaFile, javaFile2, javaFile3});

        assertThat(FilePathSizeCalculator.size(javaRootDir), is(FAKE_FILE_SIZE + FAKE_FILE_SIZE2 + FAKE_FILE_SIZE3));
    }

    @Test public void
    one_file_in_root_and_several_files_subdirectory() {
        when(javaRootDir.isFile()).thenReturn(false);
        when(javaRootDir.listFiles()).thenReturn(new File[]{javaSubDir, javaFile});
        when(javaSubDir.isFile()).thenReturn(false);
        when(javaSubDir.listFiles()).thenReturn(new File[]{javaFile, javaFile2, javaFile3});

        assertThat(FilePathSizeCalculator.size(javaRootDir), is(FAKE_FILE_SIZE + FAKE_FILE_SIZE + FAKE_FILE_SIZE2 + FAKE_FILE_SIZE3));
    }

    @Test public void
    several_subdirectories_with_several_files_each() {
        when(javaRootDir.isFile()).thenReturn(false);
        when(javaRootDir.listFiles()).thenReturn(new File[]{javaSubDir, javaSubDir, javaSubDir});
        when(javaSubDir.isFile()).thenReturn(false);
        when(javaSubDir.listFiles()).thenReturn(new File[]{javaFile, javaFile2, javaFile3});

        long expectedSize = 3 * (FAKE_FILE_SIZE + FAKE_FILE_SIZE2 + FAKE_FILE_SIZE3);

        assertThat(FilePathSizeCalculator.size(javaRootDir), is(expectedSize));
    }
}