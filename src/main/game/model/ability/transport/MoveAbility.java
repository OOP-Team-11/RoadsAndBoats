package game.model.ability.transport;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.direction.DirectionToLocation;
import game.model.direction.Location;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.TransportManager;
import game.model.movement.Move;
import game.model.transport.Transport;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class MoveAbility extends Ability {

    private Move moveObj;
    private Transport transport;
    private TransportManager transportManager;
    private TileCompartmentLocation tileCompartmentLocation;

    public MoveAbility(MainViewController mainViewController) {
        super(mainViewController);
    }

    @Override
    public void perform() {
        mainViewController.detachControls();
        transportManager.getTransports().get(tileCompartmentLocation).remove(transport);
        Location newLocation = DirectionToLocation.getLocation(tileCompartmentLocation.getLocation(), moveObj.getTileEdgeDirection());
        TileCompartmentLocation newTCL = new TileCompartmentLocation(newLocation, moveObj.getTileCompartmentDirection());
        if(transportManager.getTransports().get(newTCL) != null) {
            transportManager.getTransports().get(newTCL).add(transport);
            transportManager.notifyMapTransportRenderInfoObservers();
        }
        else {
            List<Transport> transportList = new ArrayList<>();
            transportList.add(transport);
            transportManager.getTransports().put(newTCL,transportList);
        }
        if(transport.getFollowers().size() > 0) {
            transportManager.moveFollowers(transport, tileCompartmentLocation, newTCL);
        }
        transportManager.notifyMapTransportRenderInfoObservers();
    }

    @Override
    public void detachFromController() {

    }

    public void attachToController(Transport transport, Move moveObj, TransportManager transportManager, TileCompartmentLocation tileCompartmentLocation, int moveIndex) {
        this.moveObj = moveObj;
        this.transport = transport;
        this.transportManager = transportManager;
        this.tileCompartmentLocation = tileCompartmentLocation;
        KeyCode kc = KeyCode.getKeyCode(Integer.toString(moveIndex));
        mainViewController.addControl(kc, this);
    }
    @Override
    public String getDisplayString() {
        return "Move "+moveObj.getTileEdgeDirection().getString();
    }
}
