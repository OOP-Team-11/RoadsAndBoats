package game.view.render;

public class WallInfo {

    private int type;
    private int compartment;

    public WallInfo(int type, int compartment){
        this.type = type;
        this.compartment = compartment;
    }

    public int getType(){
        return this.type; // 1 is blue, 2 is red, 3 is neutral
    }
    public int getCompartment(){ // 1 is North, 2 is NE etc.
        return this.compartment;
    }
}
