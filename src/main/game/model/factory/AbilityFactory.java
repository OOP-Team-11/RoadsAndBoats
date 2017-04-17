package game.model.factory;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.ability.goose.FollowAbility;
import game.model.ability.goose.GooseReproduceAbility;
import game.model.ability.transport.*;
import game.model.ability.transport.MoveAbility;
import game.model.ability.transport.TransportReproduceAbility;
import game.model.managers.ResourceManager;
import game.model.managers.StructureManager;
import game.model.resources.ResourceType;
import game.model.structures.Structure;
import game.model.transport.Transport;
import game.model.visitors.GooseManagerVisitor;
import game.model.visitors.StructureManagerVisitor;
import game.model.visitors.TransportManagerVisitor;

import javax.annotation.Resource;

public class AbilityFactory {
    private MainViewController mainViewController;

    public AbilityFactory(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public MoveAbility getMoveAbility() { return new MoveAbility(mainViewController); }
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
    public BuildMintAbility getBuildMintAbility(StructureManagerVisitor v) { return new BuildMintAbility(mainViewController, v); }
    public BuildGoldMineAbility getBuildGoldMineAbility(StructureManagerVisitor v) { return new BuildGoldMineAbility(mainViewController, v); }
    public BuildIronMineAbility getBuildIronMineAbility(StructureManagerVisitor v) { return new BuildIronMineAbility(mainViewController, v); }
    public BuildRowboatFactoryAbility getBuildRowboatFactoryAbility(StructureManagerVisitor v) { return new BuildRowboatFactoryAbility(mainViewController, v); }
    public BuildSteamshipFactoryAbility getBuildSteamshipFactoryAbility(StructureManagerVisitor v) { return new BuildSteamshipFactoryAbility(mainViewController, v); }
    public BuildStockExchangeAbility getBuildStockExchangeAbility(StructureManagerVisitor v) { return new BuildStockExchangeAbility(mainViewController, v); }
    public BuildTruckFactoryAbility getBuildTruckFactoryAbility(StructureManagerVisitor v) { return new BuildTruckFactoryAbility(mainViewController, v); }
    public PickUpResourceAbility getPickUpResourceAbility() { return new PickUpResourceAbility(mainViewController); }
    public DropResourceAbility getDropResourceAbility() { return new DropResourceAbility(mainViewController); }
    public PickUpTransportAbility getPickUpTransportAbility() { return new PickUpTransportAbility(mainViewController); }
    public DropTransportAbility getDropTransportAbility() { return new DropTransportAbility(mainViewController); }
    public ResearchAbility getResearchAbility() { return new ResearchAbility(mainViewController); }
    public ProduceCoinsAbility getProduceCoinsAbility() { return new ProduceCoinsAbility(mainViewController); }
    public BuyWonderBrickAbility getBuyWonderBrickAbility() { return new BuyWonderBrickAbility(mainViewController); }
}
