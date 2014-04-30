package com.player.service.songs;

import org.apache.tika.metadata.Metadata;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.player.utils.FileUtility.streamToFile;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * @author Николай
 */
public class SongHelperTest {

    private SongHelper helper = new SongHelper();

    @Test
    public void testParseMetadata() throws IOException {

        InputStream stream = ClassLoader.getSystemResourceAsStream("testFile.flac");

        File file = streamToFile(stream);

        Metadata metadata = helper.getMetadataFromSong(file);

        assertEquals("Invaders Must Die", metadata.get("xmpDM:album"));
        assertEquals("The Prodigy", metadata.get("xmpDM:artist"));
        assertEquals("Invaders Must Die", metadata.get("title"));
    }

    @Test
    public void testSaveFile() throws IOException {
        InputStream stream = ClassLoader.getSystemResourceAsStream("testFile.flac");

        File file = streamToFile(stream);

        File result = helper.saveFileOnDisc(file, System.getProperty("java.io.tmpdir"));

        assertTrue(result.exists());
        assertTrue(result.delete());
    }

}
