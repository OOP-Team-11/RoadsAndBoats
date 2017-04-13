package game.model.transport;

import game.model.resources.Goose;

import java.util.Vector;

public abstract class Transport {

//    private PlayerId playerId;
//    private int transportId;
//    private int moveCapacity;
//    private int carryCapacity;
    private Vector<Goose> followers;

    Transport() {
        this.followers = new Vector<Goose>();
    }

    public void addFollower(Goose goose) { followers.add(goose); }
    public void removeFollowers() {
        for(Goose g : followers)
            followers.remove(g);
    }
//    public void move(Location newLocation) {
//
//    }

}
