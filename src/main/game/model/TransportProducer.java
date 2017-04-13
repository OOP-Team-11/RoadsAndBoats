package game.model;

import game.model.resources.Resource;
import game.model.transport.Transport;

public interface TransportProducer {

    Transport produce(PlayerId playerId, Resource resource);
}
