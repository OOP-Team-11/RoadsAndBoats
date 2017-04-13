package game.model.structures.primaryProducer;

import game.model.resources.Gold;
import game.model.resources.Iron;
import game.model.resources.Resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Mine extends ResourceDropper {

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
    public Map<Resource, Integer> produce() {
        Map<Resource, Integer> resource = new HashMap<>();

        Random random = new Random();
        int chosen = random.nextInt(2);

        switch(chosen) {
            case 1:
                if (currentGoldCount != 0) {
                    resource.put(new Gold(), 1);
                    --currentGoldCount;
                }
                else if (currentIronCount != 0) {
                    resource.put(new Iron(), 1);
                    --currentIronCount;
                }
                break;
            case 0:
                if (currentIronCount != 0) {
                    resource.put(new Iron(), 1);
                    --currentIronCount;
                }
                else if (currentGoldCount != 0) {
                    resource.put(new Gold(), 1);
                    --currentGoldCount;
                }
                break;
        }

        return resource;
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

    private void setGoldCount(int goldCount) {
        this.initialGoldCount = goldCount;
        this.currentGoldCount = goldCount;
    }

    private void setIronCount(int ironCount) {
        this.initialIronCount = ironCount;
        this.currentIronCount = ironCount;
    }

}
