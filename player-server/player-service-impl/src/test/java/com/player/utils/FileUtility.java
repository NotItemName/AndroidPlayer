package com.player.utils;

import org.apache.poi.util.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Николай
 */
public class FileUtility {

    public static File streamToFile(InputStream stream) throws IOException {


        final File tempFile = File.createTempFile("testFile", ".flac");
        tempFile.deleteOnExit();

        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(stream, out);
        }
        return tempFile;
    }
}
