package com.player.service.songs;

import com.player.dao.artists.ArtistDao;
import com.player.dao.songs.SongDao;
import com.player.dto.AlbumDto;
import com.player.dto.ArtistDto;
import com.player.dto.GenreDto;
import com.player.dto.SongDto;
import com.player.model.songs.Song;
import org.apache.tika.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;

import static com.player.service.songs.SongConverter.convert;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * @author Николай
 */
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongHelper helper;

    @Autowired
    private SongDao songDao;

    @Autowired
    private ArtistDao artistDao;

    @Override
    @Transactional
    public Song addSong(InputStream stream) throws IOException {
        Metadata audioMetadata = helper.getMetadataFromSong(stream);

        ArtistDto artistDto = convertArtistFromMetadata(audioMetadata);
        artistDao.addArtist(artistDto);

        AlbumDto albumDto = convertAlbumFromMetadata(audioMetadata);
        albumDto.setArtistDto(artistDto);

        GenreDto genreDto = convertGenreFromMetadata(audioMetadata);

        SongDto songDto = convertSongFromMetadata(audioMetadata);
        songDto.setArtistDto(artistDto);
        songDto.setAlbumDto(albumDto);
        songDto.setGenreDto(genreDto);

        return convert(songDao.addSong(songDto));
    }

    private SongDto convertSongFromMetadata(Metadata metadata) {
        SongDto songDto = new SongDto();
        String title = metadata.get("title");
        if (isNotBlank(title)) {
            songDto.setName(title);
        }
        String trackNumber = metadata.get("xmpDM:trackNumber");
        if (isNumeric(trackNumber)) {
            songDto.setTrackNumber(Integer.parseInt(trackNumber));
        }
        return songDto;
    }

    private GenreDto convertGenreFromMetadata(Metadata metadata) {
        GenreDto genreDto = null;
        String genre = metadata.get("xmpDM:genre");
        if (isNotBlank(genre)) {
            genreDto = new GenreDto();
            genreDto.setName(genre);
        }
        return genreDto;
    }

    private AlbumDto convertAlbumFromMetadata(Metadata metadata) {
        AlbumDto albumDto = new AlbumDto();
        String album = metadata.get("xmpDM:album");
        if (isNotBlank(album)) {
            albumDto.setName(album);
        }
        String year = metadata.get("xmpDM:releaseDate");
        if (isNumeric(year)) {
            albumDto.setYear(Integer.parseInt(year));
        }
        return albumDto;
    }

    private ArtistDto convertArtistFromMetadata(Metadata metadata) {
        ArtistDto artistDto = null;
        String artist = metadata.get("xmpDM:artist");
        if (isNotBlank(artist)) {
            artistDto = new ArtistDto();
            artistDto.setName(artist);
        }
        return artistDto;
    }
}
