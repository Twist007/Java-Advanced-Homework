package Spotify.service;

import Spotify.repository.song.Song;

import java.util.List;

public interface SongService {

    void addSong(Song song);

    void deleteSong(long id);

    Song getSong(long id);

    List<Song> getAllSong();

    void updateSong(Song song);
}
