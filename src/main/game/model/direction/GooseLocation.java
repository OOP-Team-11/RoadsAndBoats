package game.model.direction;

import game.model.resources.Goose;

import java.util.Objects;

public class GooseLocation {
    private Goose goose;
    private TileCompartmentDirection tileCompartmentDirection;

    public GooseLocation(Goose goose, TileCompartmentDirection tcd) {
        this.goose = goose;
        this.tileCompartmentDirection = tcd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(goose, tileCompartmentDirection);
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof Goose) &&
                ((Goose) object).getId() == this.getGoose().getId();
    }

    public Goose getGoose() {
        return goose;
    }

    public TileCompartmentDirection getTileCompartmentDirection() {
        return tileCompartmentDirection;
    }
}
