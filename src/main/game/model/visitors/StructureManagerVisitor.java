package game.model.visitors;

import game.model.direction.TileCompartmentLocation;
import game.model.structures.Structure;

public interface StructureManagerVisitor {
    void addStructureVisit(Structure structure, TileCompartmentLocation tileCompartmentLocation);
}
