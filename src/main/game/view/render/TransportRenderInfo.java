package game.view.render;

import game.model.PlayerId;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.resources.Goose;
import game.model.resources.ResourceType;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import game.model.transport.TransportType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class TransportRenderInfo
{
    private Transport transport;
    
    public TransportRenderInfo(Transport transport)
    {
        this.transport = transport;
    }

    public TransportType getTransportType() {
        return this.transport.getType();
    }

    public int getMoveCapacity() {
        return this.transport.getMoveCapacity();
    }

    public int getCarryCapacity() {
        return this.transport.getCarryCapacity();
    }

    public List<Goose> getFollowers() {
        return this.transport.getFollowers();
    }

    public PlayerId getOwner() {
        return transport.getPlayerId();
    }

    public TransportId getTransportID() { return transport.getTransportId(); }

    public String getResourceString() {
        ResourceManager resourceManager = transport.getResourceManager();
        StringBuilder sb = new StringBuilder();
        sb.append("Boards:").append(resourceManager.getResourceCount(ResourceType.BOARDS)).append("\t");
        sb.append("Clay:").append(resourceManager.getResourceCount(ResourceType.CLAY)).append("\t");
        sb.append("Gold:").append(resourceManager.getResourceCount(ResourceType.GOLD)).append("\t");
        sb.append("Coin:").append(resourceManager.getResourceCount(ResourceType.COINS)).append("\t");
        sb.append("Fuel:").append(resourceManager.getResourceCount(ResourceType.FUEL)).append("\t");
        sb.append("Geese:").append(resourceManager.getResourceCount(ResourceType.GOOSE)).append("\t");
        sb.append("\n");
        sb.append("Iron:").append(resourceManager.getResourceCount(ResourceType.IRON)).append("\t");
        sb.append("Paper:").append(resourceManager.getResourceCount(ResourceType.PAPER)).append("\t");
        sb.append("Stock:").append(resourceManager.getResourceCount(ResourceType.STOCKBOND)).append("\t");
        sb.append("Stone:").append(resourceManager.getResourceCount(ResourceType.STONE)).append("\t");
        sb.append("Trunks:").append(resourceManager.getResourceCount(ResourceType.TRUNKS)).append("\t");
        return sb.toString();
    }
}
