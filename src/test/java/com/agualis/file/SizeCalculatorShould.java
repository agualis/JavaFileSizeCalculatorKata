package com.agualis.file;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SizeCalculatorShould {

    @Mock
    File file;

    @Test public void
    one_file_in_root() {
        when(file.isDirectory()).thenReturn(true);
        FilePath filepath = new FilePath(file);

        assertThat(filepath.size("::empty directory filepath::"), is(0));
    }
}