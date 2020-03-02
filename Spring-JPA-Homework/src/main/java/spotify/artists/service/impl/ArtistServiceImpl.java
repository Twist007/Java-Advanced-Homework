package spotify.artists.service.impl;

import org.springframework.stereotype.Service;
import spotify.artists.entities.Artist;
import spotify.artists.entities.ArtistRepository;
import spotify.artists.models.ArtistModel;
import spotify.artists.service.ArtistService;
import spotify.artists.service.converter.ArtistConverter;

import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    public final ArtistConverter artistConverter;
    public final ArtistRepository artistRepository;

    public ArtistServiceImpl(final ArtistConverter artistConverter, final ArtistRepository artistRepository) {
        this.artistConverter = artistConverter;
        this.artistRepository = artistRepository;
    }

    @Override
    public ArtistModel createArtist(ArtistModel model) {
        final Artist artist = artistConverter.convertToEntity(model);

        final Artist created = artistRepository.save(artist);

        return artistConverter.convertToModel(created);
    }

    @Override
    public void deleteArtist(String id) {
        final Optional<Artist> artistOpt = artistRepository.findById(id);

        if (!artistOpt.isPresent()) {
            return;
        }

        artistRepository.delete(artistOpt.get());
    }

    @Override
    public ArtistModel getArtist(String id) {
        final Optional<Artist> artistOpt = artistRepository.findById(id);

        return artistOpt.map(artistConverter::convertToModel).orElse(null);
    }

    @Override
    public ArtistModel updateArtist(ArtistModel model) {
        final Artist artist = artistConverter.convertToEntity(model);

        final Artist updated = artistRepository.save(artist);

        return artistConverter.convertToModel(updated);
    }
}
