package com.player.service.songs;

import org.apache.tika.metadata.Metadata;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * @author Николай
 */
public class SongDtoHelperTest {

    private SongHelper helper = new SongHelper();

    @Test
    public void testParseMetadata() throws IOException {

        InputStream stream = ClassLoader.getSystemResourceAsStream("testFile.flac");

        Metadata metadata = helper.getMetadataFromSong(stream);

        assertEquals("Invaders Must Die", metadata.get("xmpDM:album"));
        assertEquals("The Prodigy", metadata.get("xmpDM:artist"));
        assertEquals("Invaders Must Die", metadata.get("title"));
    }
}
