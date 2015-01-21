package com.player.service.songs;

import com.player.entity.Album;
import com.player.entity.Artist;
import com.player.entity.Genre;
import com.player.entity.Song;
import com.player.model.songs.SongDto;
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

public class SongDtoServiceImplTest {

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

        SongDto expectedSongDto = new SongDto();
        expectedSongDto.setId(1);
        expectedSongDto.setName("Invaders Must Die");

        Artist artist = new Artist();
        artist.setName("Prodigy");

        Album album = new Album();

        Genre genre = new Genre();

        when(songRepository.save(any(Song.class))).thenReturn(convert(expectedSongDto));
        when(artistRepository.save(artist)).thenReturn(artist);
        when(albumRepository.save(album)).thenReturn(album);
        when(genreRepository.save(genre)).thenReturn(genre);

        SongDto actualSongDto = service.addSong(stream, "testFile.flac");


        assertEquals(expectedSongDto, actualSongDto);

        verify(helper, times(1)).getMetadataFromSong(stream);

        Song song = new Song();
        song.setName("Invaders Must Die");
        song.setTrackNumber(1);

        verify(songRepository, times(1)).save(song);
        verify(artistRepository, times(1)).save(artist);
        verify(albumRepository, times(1)).save(album);
        verify(genreRepository, times(1)).save(genre);

    }
}
