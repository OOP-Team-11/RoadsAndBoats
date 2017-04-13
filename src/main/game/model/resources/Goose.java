package game.model.resources;

import game.model.ability.Ability;
import game.model.livestock.Livestock;

import java.util.Objects;
import java.util.Vector;

public class Goose implements Livestock{

    private Vector<Ability> abilities;
    private GooseId id;
    
    public Goose(GooseId gid) {
        this.id = gid;
    }

    public GooseId getId() {
        return id;
    }
}
