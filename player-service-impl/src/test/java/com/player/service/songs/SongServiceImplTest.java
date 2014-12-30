package com.player.service.songs;

import com.player.dto.AlbumDto;
import com.player.dto.ArtistDto;
import com.player.dto.GenreDto;
import com.player.dto.SongDto;
import com.player.model.songs.Song;
import com.player.repository.AlbumRepository;
import com.player.repository.ArtistRepository;
import com.player.repository.GenreRepository;
import com.player.repository.SongRepository;
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
    private SongRepository songRepository;

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private GenreRepository genreRepository;

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
        metadata.set("xmpDM:artist", "Prodigy");
        when(helper.getMetadataFromSong(stream)).thenReturn(metadata);

        Song expectedSong = new Song();
        expectedSong.setId(1);
        expectedSong.setName("Invaders Must Die");

        ArtistDto artistDto = new ArtistDto();
        artistDto.setName("Prodigy");

        AlbumDto albumDto = new AlbumDto();

        GenreDto genreDto = new GenreDto();

        when(songRepository.save(any(SongDto.class))).thenReturn(convert(expectedSong));
        when(artistRepository.save(artistDto)).thenReturn(artistDto);
        when(albumRepository.save(albumDto)).thenReturn(albumDto);
        when(genreRepository.save(genreDto)).thenReturn(genreDto);

        Song actualSong = service.addSong(stream, "testFile.flac");


        assertEquals(expectedSong, actualSong);

        verify(helper, times(1)).getMetadataFromSong(stream);

        SongDto songDto = new SongDto();
        songDto.setName("Invaders Must Die");
        songDto.setTrackNumber(1);

        verify(songRepository, times(1)).save(songDto);
        verify(artistRepository, times(1)).save(artistDto);
        verify(albumRepository, times(1)).save(albumDto);
        verify(genreRepository, times(1)).save(genreDto);

    }
}
