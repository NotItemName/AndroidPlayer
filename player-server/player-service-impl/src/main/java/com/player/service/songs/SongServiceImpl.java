package com.player.service.songs;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNumeric;

import com.google.common.collect.Lists;
import com.player.entity.Album;
import com.player.entity.Artist;
import com.player.entity.Genre;
import com.player.entity.Song;
import com.player.repository.SongRepository;
import com.player.service.albums.AlbumService;
import com.player.service.artists.ArtistService;
import com.player.service.genres.GenreService;
import org.apache.tika.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Николай
 */
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongHelper helper;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private GenreService genreService;

    @Override
    @Transactional
    public Song addSong(InputStream stream, String fileName) throws IOException {
        Metadata audioMetadata = helper.getMetadataFromSong(stream);

        if (audioMetadata == null) {
            return null;
        }

        Song song = convertSongFromMetadata(audioMetadata);

        Artist artist = convertArtistFromMetadata(audioMetadata);
        song.setArtist(artistService.addArtist(artist));


        Set<Genre> genres = convertGenreFromMetadata(audioMetadata);

        Album album = convertAlbumFromMetadata(audioMetadata);
        album.setArtist(artist);
        album.setGenres(genreService.addGenres(genres));
        song.setAlbum(albumService.addAlbum(album));

        song.setFileName(fileName);

        String name = song.getName();
        String albumName = album == null ? null : album.getName();
        String artistName = artist == null ? null : artist.getName();

        if (name == null) {
            return null;
        }
        Song readSong = songRepository.findByNameAndAlbum_NameAndArtist_Name(name, albumName, artistName);
        if (readSong == null) {
            return songRepository.save(song);
        }

        return readSong;
    }

    @Override
    @Transactional
    public boolean checkSongExist(String fileName) {
        return songRepository.findByFileName(fileName) != null;
    }

    @Override
    @Transactional
    public String getFileName(Integer id) {
        return songRepository.findOne(id).getFileName();
    }

    @Override
    @Transactional
    public List<Song> getAllSongs() {
        return Lists.newArrayList(songRepository.findAll());
    }

    @Override
    @Transactional
    public Song getSongById(Integer id) {
        return songRepository.findOne(id);
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

    private Set<Genre> convertGenreFromMetadata(Metadata metadata) {
        Set<Genre> genres = new HashSet<>();
        String genresStr = metadata.get("xmpDM:genre");
        for (String genreStr : genresStr.split(",")) {
            Genre genre = new Genre();
            genre.setName(genreStr);
            genres.add(genre);
        }
        return genres;
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
