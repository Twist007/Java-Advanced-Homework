package spotify.artists.service;

import spotify.artists.models.ArtistModel;

public interface ArtistService {

    ArtistModel createArtist(ArtistModel model);

    void deleteArtist(String id);

    ArtistModel getArtist(String id);

    ArtistModel updateArtist(ArtistModel model);
}
