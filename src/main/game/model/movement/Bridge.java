package game.model.movement;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;

public class Bridge
{
    public final Location loc;
    public final TileCompartmentDirection tcd1;
    public final TileCompartmentDirection tcd2;

    public Bridge(Location loc, TileCompartmentDirection tcd1, TileCompartmentDirection tcd2)
    {
        this.loc = loc;
        this.tcd1 = tcd1;
        this.tcd2 = tcd2;
    }

    public int hashCode()
    {
        return loc.hashCode() * 13 + tcd1.hashCode() * 17 - tcd2.hashCode() * 53;
    }

    public boolean equals(Object o)
    {
        if (o instanceof Bridge)
        {
            Bridge other = (Bridge) o;

            return other.loc.equals(this.loc)
                    && ((other.tcd1.equals(tcd1) && other.tcd2.equals(tcd2))
                    || ((other.tcd2.equals(tcd1) && other.tcd1.equals(tcd2))));
        }

        return false;
    }
}
