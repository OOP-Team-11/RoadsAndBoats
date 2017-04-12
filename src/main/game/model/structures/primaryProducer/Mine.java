package game.model.structures.primaryProducer;

public class Mine extends ResourceDropper {

    private int initialGoldCount;
    private int initialIronCount;

    Mine(int goldCount, int ironCount) {
        this.initialGoldCount = goldCount;
        this.initialIronCount = ironCount;
    }

//    public void addShaft() {
//        replenishMine();
//    }
//
//    private void replenishMine() {
//        setInitialGoldCount(this.initialIronCount);
//    }
//
//    //
//    public int getGoldCount() {
//        return goldCount;
//    }
//
//    public int getIronCount() {
//        return ironCount;
//    }
//
//    private void setInitialGoldCount(int goldCount) {
//        this.initialGoldCount = goldCount;
//    }
//
//    private void setInitialIronCount(int ironCount) {
//        this.initialIronCount = ironCount;
//    }
}
