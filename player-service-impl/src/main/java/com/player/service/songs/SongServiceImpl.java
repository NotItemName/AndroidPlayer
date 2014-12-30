package com.player.service.songs;

import com.player.dao.AlbumDao;
import com.player.dao.ArtistDao;
import com.player.dao.GenreDao;
import com.player.dao.SongDao;
import com.player.dto.AlbumDto;
import com.player.dto.ArtistDto;
import com.player.dto.GenreDto;
import com.player.dto.SongDto;
import com.player.model.songs.Song;
import com.player.model.songs.Songs;
import org.apache.tika.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;

import static com.player.service.songs.SongConverter.convert;
import static com.player.service.songs.SongConverter.convertList;
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

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private GenreDao genredao;

    @Override
    @Transactional
    public Song addSong(InputStream stream, String fileName) throws IOException {
        Metadata audioMetadata = helper.getMetadataFromSong(stream);

        if (audioMetadata == null) {
            return null;
        }

        SongDto songDto = convertSongFromMetadata(audioMetadata);

        ArtistDto artistDto = convertArtistFromMetadata(audioMetadata);
        artistDto = artistDao.save(artistDto);
        songDto.setArtistDto(artistDto);


        AlbumDto albumDto = convertAlbumFromMetadata(audioMetadata);
        albumDto.setArtistDto(artistDto);
        albumDto = albumDao.save(albumDto);
        songDto.setAlbumDto(albumDto);


        GenreDto genreDto = convertGenreFromMetadata(audioMetadata);
        genreDto = genredao.save(genreDto);
        songDto.setGenreDto(genreDto);

        songDto.setFileName(fileName);

        return convert(songDao.save(songDto));
    }

    @Override
    @Transactional
    public boolean checkSongExist(String fileName) {
        return songDao.findByFileName(fileName) != null;
    }

    @Override
    @Transactional
    public String getFileName(Integer id) {
        return songDao.findOne(id).getFileName();
    }

    @Override
    @Transactional
    public Songs getAllSongs() {
        Songs songs = new Songs();
        songs.setSongs(convertList(songDao.findAll()));
        return songs;
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
        GenreDto genreDto = new GenreDto();
        String genre = metadata.get("xmpDM:genre");
        if (isNotBlank(genre)) {
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
        ArtistDto artistDto = new ArtistDto();
        String artist = metadata.get("xmpDM:artist");
        if (isNotBlank(artist)) {
            artistDto.setName(artist);
        }
        return artistDto;
    }
}
