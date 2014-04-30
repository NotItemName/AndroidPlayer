package com.player.service.songs;

import com.player.dao.artists.ArtistDao;
import com.player.dao.songs.SongDao;
import com.player.dto.ArtistDto;
import com.player.dto.SongDto;
import com.player.model.songs.Song;
import org.apache.tika.metadata.Metadata;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.InputStream;

import static com.player.service.songs.SongConverter.convert;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class SongServiceImplTest {

    @InjectMocks
    private SongServiceImpl service;

    @Mock
    private SongHelper helper;

    @Mock
    private SongDao songDao;

    @Mock
    private ArtistDao artistDao;

    @Before
    public void init() {
        initMocks(this);
    }

    @Test
    public void testAddSong() throws Exception {

        InputStream stream = ClassLoader.getSystemResourceAsStream("testFile.flac");

        Metadata metadata = new Metadata();
        metadata.set("title", "Invaders Must Die");
        metadata.set("xmpDM:tracknumber", "1");
        when(helper.getMetadataFromSong(stream)).thenReturn(metadata);

        Song expectedSong = new Song();
        expectedSong.setId(1);
        expectedSong.setName("Invaders Must Die");

        when(songDao.addSong(any(SongDto.class))).thenReturn(convert(expectedSong));

        when(artistDao.addArtist(any(ArtistDto.class))).thenReturn(new ArtistDto());

        Song actualSong = service.addSong(stream);


        assertEquals(expectedSong, actualSong);

        verify(helper, times(1)).getMetadataFromSong(stream);
        verify(songDao, times(1)).addSong(any(SongDto.class));
        verify(artistDao, times(1)).addArtist(any(ArtistDto.class));
    }
}