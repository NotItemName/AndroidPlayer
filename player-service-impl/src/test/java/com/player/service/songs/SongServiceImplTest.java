package com.player.service.songs;

import com.player.dao.AlbumDao;
import com.player.dao.ArtistDao;
import com.player.dao.SongDao;
import com.player.dto.AlbumDto;
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

    @Mock
    private AlbumDao albumDao;

    @Before
    public void init() {
        initMocks(this);
    }

    @Test
    public void testAddSong() throws Exception {

        InputStream stream = ClassLoader.getSystemResourceAsStream("testFile.flac");

        Metadata metadata = new Metadata();
        metadata.set("title", "Invaders Must Die");
        metadata.set("xmpDM:trackNumber", "1");
        metadata.set("xmpDM:artist","Prodigy");
        when(helper.getMetadataFromSong(stream)).thenReturn(metadata);

        Song expectedSong = new Song();
        expectedSong.setId(1);
        expectedSong.setName("Invaders Must Die");

        when(songDao.addSong(any(SongDto.class))).thenReturn(convert(expectedSong));

        when(artistDao.addArtist(any(ArtistDto.class))).thenReturn(new ArtistDto());

        when(albumDao.addAlbum(any(AlbumDto.class))).thenReturn(new AlbumDto());

        Song actualSong = service.addSong(stream, "testFile.flac");


        assertEquals(expectedSong, actualSong);

        verify(helper, times(1)).getMetadataFromSong(stream);

        SongDto songDto = new SongDto();
        songDto.setName("Invaders Must Die");
        songDto.setTrackNumber(1);

        verify(songDao, times(1)).addSong(songDto);
        ArtistDto artistDto = new ArtistDto();
        artistDto.setName("Prodigy");

        verify(artistDao, times(1)).addArtist(artistDto);

        verify(albumDao,times(1)).addAlbum(new AlbumDto());
    }
}
