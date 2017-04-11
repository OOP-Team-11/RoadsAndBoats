
package mapMaker.model.tile;


import java.util.Arrays;

public class mmLocation implements  Cloneable{

    private int x;
    private int y;
    private int z;

    public mmLocation(int x, int y, int z) throws mmInvalidLocationException {
        if (x + y + z != 0) throw new mmInvalidLocationException("Coordinates must sum to 0.");
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public mmLocation(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = -(x+y);
    }

    public mmLocation clone(){
        try{
            mmLocation mmLocationClone = (mmLocation) super.clone();
            return mmLocationClone;
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
        return (object instanceof mmLocation) &&
                ((mmLocation) object).getX() == this.x &&
                ((mmLocation) object).getY() == this.y &&
                ((mmLocation) object).getZ() == this.z;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new int[]{this.x, this.y, this.z});
    }
    public String getLocationString() {
        return String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(z);
    }
}
