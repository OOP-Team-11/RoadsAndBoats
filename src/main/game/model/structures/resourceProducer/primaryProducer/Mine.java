package game.model.structures.resourceProducer.primaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.structures.resourceProducer.ResourceDropper;

import java.util.Random;

public class Mine extends ResourceDropper {

    private static final int GOLD_AMT = 1;
    private static final int IRON_AMT = 1;

    private int initialGoldCount;
    private int initialIronCount;
    private int currentGoldCount;
    private int currentIronCount;

    public Mine(int initialGoldCount, int initialIronCount) {
        this.initialGoldCount = initialGoldCount;
        this.initialIronCount = initialIronCount;
        this.currentGoldCount = initialGoldCount;
        this.currentIronCount = initialIronCount;
    }

    @Override
    public boolean produce(ResourceManager resourceManager) {
        Random random = new Random();
        int chosen = random.nextInt(2);

        switch (chosen) {
            // Gold is picked
            case 1:
                if (checkCurrentGoldCount()) {
                    resourceManager.addResource(ResourceType.GOLD, GOLD_AMT);
                    decrementGoldCount();
                }
                else if (checkCurrentIronCount()) {
                    resourceManager.addResource(ResourceType.IRON, IRON_AMT);
                    decrementIronCount();
                }
                return true;
            // Iron is picked
            case 2:
                if (checkCurrentIronCount()) {
                    resourceManager.addResource(ResourceType.IRON, IRON_AMT);
                    decrementIronCount();
                }
                else if (checkCurrentGoldCount()) {
                    resourceManager.addResource(ResourceType.GOLD, GOLD_AMT);
                    decrementGoldCount();
                }
                return true;
        }

        return false;
    }

    public void addShaft() {
        replenishMine();
    }

    private void replenishMine() {
        setGoldCount(this.initialGoldCount);
        setIronCount(this.initialIronCount);
    }

    public int getCurrentGoldCount() {
        return currentGoldCount;
    }

    public int getCurrentIronCount() {
        return currentIronCount;
    }

    private boolean checkCurrentGoldCount() {
        return (currentGoldCount != 0);
    }

    private boolean checkCurrentIronCount() {
        return (currentIronCount != 0);
    }

    private void decrementGoldCount() {
        --currentGoldCount;
    }

    private void decrementIronCount() {
        --currentIronCount;
    }

    private void setGoldCount(int goldCount) {
        this.initialGoldCount = goldCount;
        this.currentGoldCount = goldCount;
    }

    private void setIronCount(int ironCount) {
        this.initialIronCount = ironCount;
        this.currentIronCount = ironCount;
    }

    @Override
    public StructureType getType() {
        return StructureType.MINE;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }
}
