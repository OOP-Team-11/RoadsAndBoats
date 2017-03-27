package model.tile;

import java.util.Objects;

public class Location {

    private int x;
    private int y;
    private int z;

    public Location(int x, int y, int z) throws InvalidLocationException {
        if (x + y + z != 0) throw new InvalidLocationException("Coordinates must sum to 0.");
        this.x = x;
        this.y = y;
        this.z = z;
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
        return Objects.hash(x,y,z);
    }
}
