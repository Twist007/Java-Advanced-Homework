package bg.imdb.actors.service.converters;

import bg.imdb.actors.entities.Actor;
import org.springframework.stereotype.Component;

@Component
public class ActorConverter {

    public Actor convertToEntity(final String name) {
        if (name == null) {
            return null;
        }

        return new Actor(null, name);
    }
}
