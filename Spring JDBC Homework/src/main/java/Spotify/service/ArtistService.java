package Spotify.service;

import Spotify.repository.artist.Artist;

import java.util.List;

public interface ArtistService {

    void addArtist(Artist artist);

    void deleteArtist(long id);

    Artist geArtist(long id);

    List<Artist> getAllArtist();

    void updateArtist(Artist artist);
}
