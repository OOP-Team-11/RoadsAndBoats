package game.model.structures.resourceProducer.secondaryProducer;

import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.structures.resourceProducer.SecondaryProducer;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

public class StoneFactory extends SecondaryProducer {

    private static final int LIMIT = 6;         // limit of uses per turn

    private static final int CLAY_REQ = 1;      // 1 Clay input requirement

    private static final int STONE_AMT = 2;     // 2 Stone output amount

    private int productionLimit;

    public StoneFactory() {
        this.productionLimit = LIMIT;
    }

    /**
     * Use for map importer to import used stone factories
     * @param productionLimit
     */
    public StoneFactory(int productionLimit) {
        this.productionLimit = productionLimit;
    }

    // 2 Stone <= 1 Clay
    @Override
    public boolean produce(ResourceManager resourceManager) {
        if (canProduceStone(resourceManager)) {
            produceStone(resourceManager);
            return true;
        }
        return false;
    }

    @Override
    public boolean produce(TileCompartment tileCompartment) {
        if (canProduceStone(tileCompartment)) {
            produceStone(tileCompartment);
            return true;
        }
        return false;
    }

    @Override
    public boolean produce(Transport transport) {
        if (canProduceStone(transport)) {
            produceStone(transport);
            return true;
        }
        return false;
    }

    private void produceStone(TileCompartment tileCompartment) {
        tileCompartment.storeResource(ResourceType.STONE, STONE_AMT);
        decrementProductionLimit();
    }

    private void produceStone(Transport transport) {
        transport.storeResource(ResourceType.STONE, STONE_AMT);
        decrementProductionLimit();
    }

    private void produceStone(ResourceManager resourceManager) {
        resourceManager.removeResource(ResourceType.STONE, STONE_AMT);
        decrementProductionLimit();
    }

    private boolean canProduceStone(TileCompartment tileCompartment) {
        return (productionLimit != 0)
                && tileCompartment.takeResource(ResourceType.CLAY, CLAY_REQ);
    }

    private boolean canProduceStone(Transport transport) {
        return (productionLimit != 0)
                && transport.takeResource(ResourceType.CLAY, CLAY_REQ);
    }

    private boolean canProduceStone(ResourceManager resourceManager) {
        return (productionLimit != 0)
                && resourceManager.removeResource(ResourceType.CLAY, CLAY_REQ);
    }


    private void decrementProductionLimit() {
        --productionLimit;
    }

    public void resetProductionLimit() {
        productionLimit = LIMIT;
    }

    public int getProductionLimit() {
        return  productionLimit;
    }

    @Override
    public StructureType getType() {
        return StructureType.STONE_FACTORY;
    }

    @Override
    public String getExportString() {
        //Has limit, so getExportString should return its limit
        return "LIMIT=" + this.getProductionLimit();
    }
}
