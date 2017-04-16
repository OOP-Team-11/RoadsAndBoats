package game.model.factory;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.ability.goose.FollowAbility;
import game.model.ability.goose.GooseReproduceAbility;
import game.model.ability.transport.*;
import game.model.ability.transport.MoveAbility;
import game.model.ability.transport.TransportReproduceAbility;
import game.model.visitors.GooseManagerVisitor;
import game.model.visitors.StructureManagerVisitor;
import game.model.visitors.TransportManagerVisitor;

public class AbilityFactory {
    private MainViewController mainViewController;

    public AbilityFactory(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public Ability getMoveAbility() { return new MoveAbility(mainViewController); }
    public FollowAbility getFollowAbility() { return new FollowAbility(mainViewController); }
    public TransportReproduceAbility getTransportReproduceAbility(TransportManagerVisitor v) {
        return new TransportReproduceAbility(mainViewController, v); }
    public GooseReproduceAbility getGooseReproduceAbility(GooseManagerVisitor v) {
        return new GooseReproduceAbility(mainViewController, v);
    public BuildWoodcutterAbility getBuildWoodcutterAbility() { return new BuildWoodcutterAbility(mainViewController); }
    public BuildClayPitAbility getBuildClayPitAbility() { return new BuildClayPitAbility(mainViewController); }
    public BuildStoneQuarryAbility getBuildStoneQuarryAbility() { return new BuildStoneQuarryAbility(mainViewController); }
    public BuildSawmillAbility getBuildSawmillAbility() { return new BuildSawmillAbility(mainViewController); }
    public BuildPapermillAbility getBuildPapermillAbility() { return new BuildPapermillAbility(mainViewController); }
    public BuildStoneFactoryAbility getBuildStoneFactoryAbility() { return new BuildStoneFactoryAbility(mainViewController); }
    public BuildCoalBurnerAbility getBuildCoalBurnerAbility() { return new BuildCoalBurnerAbility(mainViewController); }
    public BuildMineAbility getBuildMineAbility() { return new BuildMineAbility(mainViewController); }
    }
}
