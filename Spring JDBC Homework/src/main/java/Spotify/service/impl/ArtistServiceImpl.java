package Spotify.service.impl;

import Spotify.repository.artist.Artist;
import Spotify.repository.artist.ArtistRepository;
import Spotify.service.ArtistService;

import java.util.List;

public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void addArtist(Artist artist) {
        artistRepository.add(artist);
    }

    @Override
    public void deleteArtist(long id) {
        artistRepository.delete(id);
    }

    @Override
    public Artist geArtist(long id) {
        return artistRepository.get(id);
    }

    @Override
    public List<Artist> getAllArtist() {
        return artistRepository.getAll();
    }

    @Override
    public void updateArtist(Artist artist) {
        artistRepository.update(artist);
    }
}
