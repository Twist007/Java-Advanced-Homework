package Spotify.rest;

import Spotify.repository.artist.Artist;
import Spotify.service.ArtistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }


    @PostMapping
    public void addArtist(@RequestBody final Artist artist) {
        artistService.addArtist(artist);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable final long id) {

        artistService.deleteArtist(id);
    }

    @GetMapping("/{id}")
    public Artist getArtist(@PathVariable final long id) {

        return artistService.geArtist(id);
    }

    @GetMapping
    public List<Artist> getAllArtist() {
        return artistService.getAllArtist();
    }

    @PutMapping
    public void update(@RequestBody final Artist artist) {
        artistService.updateArtist(artist);
    }
}


