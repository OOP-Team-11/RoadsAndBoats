package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.StockMarket;
import game.model.visitors.StructureManagerVisitor;
import javafx.scene.input.KeyCode;

public class BuildStockExchangeAbility extends Ability {
    private StructureManagerVisitor structureManagerVisitor;
    private TileCompartmentLocation tileCompartmentLocation;
    private ResourceManager resourceManager;

    public BuildStockExchangeAbility(MainViewController mainViewController, StructureManagerVisitor v) {
        super(mainViewController);
        this.structureManagerVisitor = v;
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        resourceManager.removeResource(ResourceType.BOARDS, 2);
        resourceManager.removeResource(ResourceType.STONE, 2);
        StockMarket stockExchange = new StockMarket();
        this.structureManagerVisitor.addStructureVisit(stockExchange, this.tileCompartmentLocation);
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(ResourceManager transportRm, TileCompartmentLocation tileCompartmentLocation) {
        this.tileCompartmentLocation = tileCompartmentLocation;
        this.resourceManager = transportRm;
        mainViewController.addControl(KeyCode.M, this);
    }
    @Override
    public String getDisplayString() {
        return "Build Stock Exchange";
    }
}
