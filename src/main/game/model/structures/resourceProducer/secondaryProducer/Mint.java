package game.model.structures.resourceProducer.secondaryProducer;

import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.structures.resourceProducer.SecondaryProducer;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

public class Mint extends SecondaryProducer {

    private static final int FUEL_REQ = 1;      // 1 Fuel input requirement
    private static final int GOLD_REQ = 2;      // 2 Gold input requirement

    private static final int COINS_AMT = 1;     // 1 Coins output amount

    public Mint() {

    }

    // 1 Coins <= 1 Fuel + 2 Gold
    @Override
    public boolean produce(ResourceManager resourceManager) {
        if (canProduceCoins(resourceManager)) {
            resourceManager.addResource(ResourceType.COINS, COINS_AMT);
            return true;
        }
        return false;
    }

    @Override
    public boolean produce(TileCompartment tileCompartment) {
        if (canProduceCoins(tileCompartment)) {
            tileCompartment.storeResource(ResourceType.COINS, COINS_AMT);
            return true;
        }
        return false;
    }

    @Override
    public boolean produce(Transport transport) {
        if (canProduceCoins(transport)) {
            transport.storeResource(ResourceType.COINS, COINS_AMT);
            return true;
        }
        return false;
    }

    private boolean canProduceCoins(TileCompartment tileCompartment) {
        return tileCompartment.takeResource(ResourceType.FUEL, FUEL_REQ)
                && tileCompartment.takeResource(ResourceType.GOLD, GOLD_REQ);
    }

    private boolean canProduceCoins(Transport transport) {
        return transport.takeResource(ResourceType.FUEL, FUEL_REQ)
                && transport.takeResource(ResourceType.GOLD, GOLD_REQ);
    }

    private boolean canProduceCoins(ResourceManager resourceManager) {
        return resourceManager.removeResource(ResourceType.FUEL, FUEL_REQ)
                && resourceManager.removeResource(ResourceType.GOLD, GOLD_REQ);
    }

    @Override
    public StructureType getType() {
        return StructureType.MINT;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }

}