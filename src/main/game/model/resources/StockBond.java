package game.model.resources;

import java.util.Objects;

public class StockBond extends Resource {
    @Override
    public int getWealthPoint() {
        return 120;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof StockBond)) {
            return false;
        }
        return true;
    }
}
