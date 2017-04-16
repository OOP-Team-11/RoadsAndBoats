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
        return new GooseReproduceAbility(mainViewController, v); }
    public BuildWoodcutterAbility getBuildWoodcutterAbility(StructureManagerVisitor v) { return new BuildWoodcutterAbility(mainViewController, v); }
    public BuildClayPitAbility getBuildClayPitAbility(StructureManagerVisitor v) { return new BuildClayPitAbility(mainViewController, v); }
    public BuildStoneQuarryAbility getBuildStoneQuarryAbility(StructureManagerVisitor v) { return new BuildStoneQuarryAbility(mainViewController, v); }
    public BuildSawmillAbility getBuildSawmillAbility(StructureManagerVisitor v) { return new BuildSawmillAbility(mainViewController, v); }
    public BuildPapermillAbility getBuildPapermillAbility(StructureManagerVisitor v) { return new BuildPapermillAbility(mainViewController, v); }
    public BuildStoneFactoryAbility getBuildStoneFactoryAbility(StructureManagerVisitor v) { return new BuildStoneFactoryAbility(mainViewController, v); }
    public BuildCoalBurnerAbility getBuildCoalBurnerAbility(StructureManagerVisitor v) { return new BuildCoalBurnerAbility(mainViewController, v); }
    public BuildMineAbility getBuildMineAbility(StructureManagerVisitor v) { return new BuildMineAbility(mainViewController, v); }
}
