package game.model.structures;

import game.model.ability.Ability;

import java.util.Vector;

public abstract class Structure {

    private Vector<Ability> abilities;

    protected Structure() {
        abilities = new Vector<Ability>();
    }

}
