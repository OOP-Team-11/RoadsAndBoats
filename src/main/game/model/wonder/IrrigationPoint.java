package game.model.wonder;


public class IrrigationPoint {
    private int row=12;
    private int brick=12;
    public IrrigationPoint(int row, int brick){
        this.row = row;
        this.brick = brick;
    }
    public int getRow() {
        return row;
    }
    public int getBrick() {
        return brick;
    }
}
