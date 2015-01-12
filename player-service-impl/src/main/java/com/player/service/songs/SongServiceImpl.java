package com.player.service.songs;

import com.player.dao.AlbumDao;
import com.player.dao.ArtistDao;
import com.player.dao.GenreDao;
import com.player.dao.SongDao;
import com.player.entity.Album;
import com.player.entity.Artist;
import com.player.entity.Genre;
import com.player.entity.Song;
import com.player.model.songs.SongDto;
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
    public SongDto addSong(InputStream stream, String fileName) throws IOException {
        Metadata audioMetadata = helper.getMetadataFromSong(stream);

        if (audioMetadata == null) {
            return null;
        }

        Song song = convertSongFromMetadata(audioMetadata);

        Artist artist = convertArtistFromMetadata(audioMetadata);
        artist = artistDao.save(artist);
        song.setArtist(artist);


        Album album = convertAlbumFromMetadata(audioMetadata);
        album.setArtist(artist);
        album = albumDao.save(album);
        song.setAlbum(album);


        Genre genre = convertGenreFromMetadata(audioMetadata);
        genre = genredao.save(genre);
        song.setGenre(genre);

        song.setFileName(fileName);

        return convert(songDao.save(song));
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
        songs.setSongDtos(convertList(songDao.findAll()));
        return songs;
    }


    private Song convertSongFromMetadata(Metadata metadata) {
        Song song = new Song();
        String title = metadata.get("title");
        if (isNotBlank(title)) {
            song.setName(title);
        }
        String trackNumber = metadata.get("xmpDM:trackNumber");
        if (isNumeric(trackNumber)) {
            song.setTrackNumber(Integer.parseInt(trackNumber));
        }
        return song;
    }

    private Genre convertGenreFromMetadata(Metadata metadata) {
        Genre genreDto = new Genre();
        String genre = metadata.get("xmpDM:genre");
        if (isNotBlank(genre)) {
            genreDto.setName(genre);
        }
        return genreDto;
    }

    private Album convertAlbumFromMetadata(Metadata metadata) {
        Album albumDto = new Album();
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

    private Artist convertArtistFromMetadata(Metadata metadata) {
        Artist artistDto = new Artist();
        String artist = metadata.get("xmpDM:artist");
        if (isNotBlank(artist)) {
            artistDto.setName(artist);
        }
        return artistDto;
    }
}
