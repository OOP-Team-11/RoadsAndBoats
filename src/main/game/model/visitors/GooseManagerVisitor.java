package game.model.visitors;

import game.model.direction.TileCompartmentLocation;
import game.model.resources.Goose;

public interface GooseManagerVisitor {
    void addGooseVisit(Goose goose, TileCompartmentLocation tileCompartmentLocation);
}
