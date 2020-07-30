package com.communify.api.helper;

import static com.communify.api.helper.LogHelper.createDirectoryIfNotExists;
import static com.communify.api.helper.LogHelper.createFileIfNotExists;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.communify.api.CommunifyApplicationTests;

import lombok.Getter;

@Getter
public class LogHelperTest extends CommunifyApplicationTests {

    @Test
    public void shouldCreateDirectoryOrFileIfNotExists() throws IOException {
        File file = mock(File.class);
        when(file.exists()).thenReturn(false);
        createDirectoryIfNotExists(file);
        createFileIfNotExists(file);
        verify(file, times(2)).exists();
    }
    
}
