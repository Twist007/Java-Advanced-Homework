package bg.imdb.actors.service.impl;

import bg.imdb.actors.entities.Actor;
import bg.imdb.actors.entities.ActorRepository;
import bg.imdb.actors.service.ActorService;
import bg.imdb.actors.service.converters.ActorConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class ActorServiceImpl implements ActorService {

    private final ActorConverter actorConverter;
    private final ActorRepository actorRepository;

    public ActorServiceImpl(ActorConverter actorConverter, ActorRepository actorRepository) {
        this.actorConverter = actorConverter;
        this.actorRepository = actorRepository;
    }

    @Override
    public Actor createActor(String name) {
        log.info("Create actor BEGIN: {}", name);

        if (name == null) {
            return null;
        }

        final Actor actorEntity = actorConverter.convertToEntity(name);
        final Actor created = actorRepository.save(actorEntity);

        log.info("Create actor END: {}", created);

        return created;
    }

    @Override
    public Actor getByName(String name) {
        log.info("Get actor by name BEGIN: {}", name);

        if (name == null) {
            return null;
        }

        final Optional<Actor>  actorOpt = actorRepository.findByName(name);
        final Actor  actor =  actorOpt.orElse(null);

        log.info("Get actor by name BEGIN: {}",  actor);

        return  actor;
    }
}
