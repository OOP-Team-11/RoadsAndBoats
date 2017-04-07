
package mapMaker.model.tile;


import java.util.Arrays;

public class Location implements  Cloneable{

    private int x;
    private int y;
    private int z;

    public Location(int x, int y, int z) throws InvalidLocationException {
        if (x + y + z != 0) throw new InvalidLocationException("Coordinates must sum to 0.");
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = -(x+y);
    }

    public Location clone(){
        try{
            Location locationClone = (Location) super.clone();
            return locationClone;
        } catch(CloneNotSupportedException e){
            throw new InternalError(e.toString());
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof Location) &&
                ((Location) object).getX() == this.x &&
                ((Location) object).getY() == this.y &&
                ((Location) object).getZ() == this.z;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new int[]{this.x, this.y, this.z});
    }
    public String getLocationString() {
        return String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(z);
    }
}
