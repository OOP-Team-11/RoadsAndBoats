package game.model.factory;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.ability.goose.FollowAbility;
import game.model.ability.goose.GooseReproduceAbility;
import game.model.ability.transport.*;

public class AbilityFactory {
    private MainViewController mainViewController;

    public AbilityFactory(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public Ability getMoveAbility() { return new MoveAbility(mainViewController); }
    public FollowAbility getFollowAbility() { return new FollowAbility(mainViewController); }
    public TransportReproduceAbility getTransportReproduceAbility() { return new TransportReproduceAbility(mainViewController); }
    public GooseReproduceAbility getGooseReproduceAbility() { return new GooseReproduceAbility(mainViewController); }
    public BuildWoodcutterAbility getBuildWoodcutterAbility() { return new BuildWoodcutterAbility(mainViewController); }
    public BuildClayPitAbility getBuildClayPitAbility() { return new BuildClayPitAbility(mainViewController); }
    public BuildStoneQuarryAbility getBuildStoneQuarryAbility() { return new BuildStoneQuarryAbility(mainViewController); }
    public BuildSawmillAbility getBuildSawmillAbility() { return new BuildSawmillAbility(mainViewController); }
    public BuildPapermillAbility getBuildPapermillAbility() { return new BuildPapermillAbility((mainViewController)); }

}
