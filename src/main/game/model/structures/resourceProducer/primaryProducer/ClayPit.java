package game.model.structures.resourceProducer.primaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.visitors.TileCompartmentVisitor;

public class ClayPit extends ResourceDropper {

    private static final int CLAY_AMT = 1;

    public ClayPit() {

    }

    @Override
    public boolean produce(TileCompartmentVisitor tcv) {
        acceptStoreResources(tcv, ResourceType.CLAY, CLAY_AMT);
        return true;
    }

    @Override
    public StructureType getType() {
        return StructureType.CLAYPIT;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }


}
