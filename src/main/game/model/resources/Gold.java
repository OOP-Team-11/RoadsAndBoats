package game.model.resources;


import java.util.Objects;

public class Gold extends Resource {
    @Override
    public int getWealthPoint() {
        return 10;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Gold)) {
            return false;
        }
        return true;
    }
}
