package spotify.songs.service.converters;

import org.springframework.stereotype.Component;
import spotify.artists.entities.Artist;
import spotify.artists.entities.ArtistRepository;
import spotify.songs.entities.Song;
import spotify.songs.models.SongModel;

import java.util.Optional;

@Component
public class SongConverter {

    private final ArtistRepository artistRepository;

    public SongConverter(final ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Song convertToEntity(final SongModel model) {
        if (model == null) {
            return null;
        }

        final Song song = new Song();
        song.setId(model.getId());
        song.setName(model.getName());
        song.setReleaseDate(model.getReleaseDate());
        song.setLength(model.getLengthInSeconds());

        final Optional<Artist> artistOpt = artistRepository.findById(model.getArtistId());
        artistOpt.ifPresent(song::setArtist);

        return song;
    }

    public SongModel convertToModel(final Song song) {
        if (song == null) {
            return null;
        }

        final SongModel model = new SongModel();
        model.setId(song.getId());
        model.setName(song.getName());
        model.setReleaseDate(song.getReleaseDate());
        model.setLengthInSeconds(song.getLength());
        model.setArtistId(song.getArtist().getId());

        return model;
    }
}
