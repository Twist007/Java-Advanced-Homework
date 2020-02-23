package Spotify.rest;

import Spotify.repository.song.Song;
import Spotify.service.SongService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Song")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public void addSong(@RequestBody final Song song) {
        songService.addSong(song);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable final long id) {

        songService.deleteSong(id);
    }

    @GetMapping("/{id}")
    public Song getSong(@PathVariable final long id) {

        return songService.getSong(id);
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return songService.getAllSong();
    }

    @PutMapping
    public void update(@RequestBody final Song song) {
        songService.updateSong(song);
    }


}
