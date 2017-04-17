package game.model.structures.resourceProducer.primaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.visitors.TileCompartmentVisitor;

public class Woodcutter extends ResourceDropper {

    private static final int TRUNKS_AMT = 1;

    public Woodcutter() {

    }

    @Override
    public boolean produce(TileCompartmentVisitor tcv) {
        acceptStoreResources(tcv, ResourceType.TRUNKS, TRUNKS_AMT);
        return true;
    }

    @Override
    public StructureType getType() {
        return StructureType.WOODCUTTER;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }

}
