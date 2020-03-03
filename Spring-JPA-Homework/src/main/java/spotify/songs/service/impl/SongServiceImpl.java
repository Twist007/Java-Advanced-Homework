package spotify.songs.service.impl;

import org.springframework.stereotype.Service;
import spotify.songs.entities.Song;
import spotify.songs.entities.SongRepository;
import spotify.songs.models.SongModel;
import spotify.songs.service.SongService;
import spotify.songs.service.converters.SongConverter;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final SongConverter songConverter;

    public SongServiceImpl(SongRepository songRepository, SongConverter songConverter) {
        this.songRepository = songRepository;
        this.songConverter = songConverter;
    }

    @Transactional
    @Override
    public SongModel createSong(SongModel model) {
        final Song song = songConverter.convertToEntity(model);

        final Song created = songRepository.save(song);

        return songConverter.convertToModel(created);
    }

    @Override
    public void deleteSong(String id) {
        final Optional<Song> songOpt = songRepository.findById(id);
        if (!songOpt.isPresent()) {
            return;
        }
        songRepository.delete(songOpt.get());
    }

    @Transactional
    @Override
    public SongModel getSong(String id) {
        final Optional<Song> songOpt = songRepository.findById(id);
        return songOpt.map(songConverter::convertToModel).orElse(null);
    }

    @Transactional
    @Override
    public SongModel updateSong(SongModel model) {
        final Song song = songConverter.convertToEntity(model);

        final Song updated = songRepository.save(song);

        return songConverter.convertToModel(updated);
    }
}
