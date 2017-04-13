package game.model.resources;


import java.util.Objects;

public class Fuel extends Resource {
    @Override
    public int getWealthPoint() {
        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Fuel)) {
            return false;
        }
        return true;
    }
}
