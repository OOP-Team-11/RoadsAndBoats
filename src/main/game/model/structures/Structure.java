package game.model.structures;

import game.model.ability.Ability;

import java.util.Vector;

public abstract class Structure {

    private Vector<Ability> abilities;
    private StructureId id;

    protected Structure() {
        abilities = new Vector<Ability>();
        id = new StructureId();
    }

    public StructureId getId()
    {
        return id;
    }
}
