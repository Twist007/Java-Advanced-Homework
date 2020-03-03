package spotify.songs.service;

import spotify.songs.models.SongModel;

public interface SongService {

    SongModel createSong(SongModel model);

    void deleteSong(String id);

    SongModel getSong(String id);

    SongModel updateSong(SongModel model);
}
