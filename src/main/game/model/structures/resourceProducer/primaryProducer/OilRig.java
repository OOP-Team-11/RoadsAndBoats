package game.model.structures.resourceProducer.primaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.structures.resourceProducer.ResourceHolder;

public class OilRig extends ResourceHolder {

    private static final int FUEL_AMT = 1;

    public OilRig() {

    }

    @Override
    public boolean produce() {
        storeResource(ResourceType.FUEL, FUEL_AMT);
        return true;
    }

    @Override
    public StructureType getType() {
        return StructureType.OIL_RIG;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }
}
