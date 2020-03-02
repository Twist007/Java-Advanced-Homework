package spotify.artists.service.converter;

import org.springframework.stereotype.Component;
import spotify.artists.entities.Artist;
import spotify.artists.models.ArtistModel;

@Component
public class ArtistConverter {

    public Artist convertToEntity(final ArtistModel model) {
        if (model == null) {
            return null;
        }

        final Artist artist = new Artist();

        artist.setId(model.getId());
        artist.setFirstName(model.getFirstName());
        artist.setLastName(model.getLastName());
        artist.setBirthDate(model.getBirthDate());
        artist.setGenre(model.getGenre());

        return artist;
    }

    public ArtistModel convertToModel(final Artist artist) {
        if (artist == null) {
            return null;
        }

        final ArtistModel model = new ArtistModel();

        model.setId(artist.getId());
        model.setFirstName(artist.getFirstName());
        model.setLastName(artist.getLastName());
        model.setBirthDate(artist.getBirthDate());
        model.setGenre(artist.getGenre());

        return model;
    }
}
