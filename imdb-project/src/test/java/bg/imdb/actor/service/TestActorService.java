package bg.imdb.actor.service;

import bg.imdb.actors.entities.Actor;
import bg.imdb.actors.service.ActorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestActorService {

  @Autowired
  private ActorService actorService;

  @Test
  public void testCreateActor() {
    assertNull(actorService.getByName("Harrison Ford 2"));

    final Actor actor = actorService.createActor("Harrison Ford 1");

    assertEquals("Harrison Ford 1", actor.getName());

    actorService.createActor("Carrie Fisher 2");

    final String actorCarrie = actorService.getByName("Carrie Fisher 2").getName();

    assertNotNull(actorCarrie);
  }

}
