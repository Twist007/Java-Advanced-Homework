package Spotify.service.impl;

import Spotify.repository.song.Song;
import Spotify.repository.song.SongRepository;
import Spotify.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public void addSong(Song song) {
        songRepository.add(song);
    }

    @Override
    public void deleteSong(long id) {
        songRepository.delete(id);
    }

    @Override
    public Song getSong(long id) {
        return songRepository.get(id);
    }

    @Override
    public List<Song> getAllSong() {
        return songRepository.getAll();
    }

    @Override
    public void updateSong(Song song) {
        songRepository.update(song);
    }
}
