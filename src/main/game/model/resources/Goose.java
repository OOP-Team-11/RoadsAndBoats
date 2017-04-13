package game.model.resources;

import game.model.ability.Ability;
import game.model.livestock.Livestock;

import java.util.Objects;
import java.util.Vector;

public class Goose extends Resource implements Livestock{

    private Vector<Ability> abilities;
    private GooseId id;
    public Goose(GooseId gid) {
        this.id = gid;
    }

    @Override
    public int getWealthPoint() {
        return 0;
    }

    public GooseId getId() {
        return id;
    }
}
