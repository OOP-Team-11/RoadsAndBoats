package game.model.resources;

import java.util.Objects;

public class Coins extends Resource {
    @Override
    public int getWealthPoint() {
        return 40;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Coins)) {
            return false;
        }
        return true;
    }
}
