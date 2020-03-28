package bg.imdb.actors.service;

import bg.imdb.actors.entities.Actor;

public interface ActorService {

    Actor createActor(String name);

    Actor getByName(String name);
}
