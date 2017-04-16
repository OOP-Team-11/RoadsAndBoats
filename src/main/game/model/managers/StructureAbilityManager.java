package game.model.managers;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.factory.AbilityFactory;
import game.model.map.RBMap;
import game.model.structures.Structure;
import game.model.visitors.StructureManagerVisitor;

import java.util.ArrayList;

public class StructureAbilityManager
{
    private AbilityFactory abilityFactory;
    private ArrayList<Ability> abilities;
    private RBMap map;
    private MainViewController mainViewController;
    private StructureManagerVisitor structureManagerVisitor;

    public StructureAbilityManager(MainViewController mainViewController, RBMap map, StructureManagerVisitor structureManagerVisitor) {
        this.mainViewController = mainViewController;
        this.abilityFactory = new AbilityFactory(mainViewController);
        this.abilities = new ArrayList<Ability>();
        this.structureManagerVisitor = structureManagerVisitor;
    }

    public void setMap(RBMap map) {
        this.map = map;
    }

    public RBMap getMap() {
        return map;
    }

    public void addAbilities(TileCompartmentLocation tileCompartmentLocation, Structure structure) {
        //TODO
    }
}
