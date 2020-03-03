package spotify.artists.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import spotify.artists.models.ArtistModel;
import spotify.artists.service.ArtistService;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestArtistService {

    @Autowired
    private ArtistService artistService;

    @Test
    public void testCRUDArtist() {
        final ArtistModel model =
                new ArtistModel("", "Tupac", "Shakur",
                        LocalDate.of(1970, 6, 15), "rap");

        final ArtistModel created = artistService.createArtist(model);

        assertEquals(model.getFirstName(), created.getFirstName());
        assertEquals(model.getLastName(), created.getLastName());
        assertEquals(model.getGenre(), created.getGenre());
        assertEquals(model.getBirthDate(), created.getBirthDate());

        created.setBirthDate(LocalDate.now());

        final ArtistModel updated = artistService.updateArtist(created);

        assertEquals(LocalDate.now(), updated.getBirthDate());

        artistService.deleteArtist(updated.getId());

        assertNull(artistService.getArtist(updated.getId()));
    }
}
