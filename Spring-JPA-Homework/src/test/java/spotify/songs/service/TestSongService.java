package spotify.songs.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import spotify.artists.models.ArtistModel;
import spotify.artists.service.ArtistService;
import spotify.songs.models.SongModel;
import spotify.songs.service.SongService;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestSongService {

    @Autowired
    private SongService songService;

    @Autowired
    private ArtistService artistService;

    @Test
    public void testCRUDSong() {
        final ArtistModel artistModel = new ArtistModel("", "Tupac", "Shakur",
                LocalDate.of(1970, 6, 15), "rap");

        final ArtistModel createdArtist = artistService.createArtist(artistModel);

        final SongModel model = new SongModel("", "Gangsta Party",
                LocalDate.of(1993, 5, 5), 240,
                createdArtist.getId());

        final SongModel created = songService.createSong(model);

        assertEquals(model.getName(), created.getName());
        assertEquals(model.getArtistId(), created.getArtistId());
        assertEquals(model.getLengthInSeconds(), created.getLengthInSeconds());
        assertEquals(model.getReleaseDate(), created.getReleaseDate());

        created.setName("Dear mama");

        final SongModel updated = songService.updateSong(created);

        assertEquals(created.getName(), updated.getName());

        songService.deleteSong(updated.getId());

        assertNull(songService.getSong(updated.getId()));
    }
}
