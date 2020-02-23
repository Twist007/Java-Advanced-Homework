package Spotify.service;

import Spotify.repository.artist.Artist;

import java.util.List;

public interface ArtistService {

    void addArtist(Artist artist);

    void deleteSong(long id);

    Artist getSong(long id);

    List<Artist> getAllArtist();

    void update(Artist artist);
}
