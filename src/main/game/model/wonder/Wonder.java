package game.model.wonder;
import game.model.Player;
import game.model.PlayerId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Wonder implements TurnObserver {
    private Vector<WonderBrick> player1;
    private Vector<WonderBrick> player2;
    private Vector<Vector<WonderBrick>> wonderbricks;
    private HashMap<PlayerId, Vector<WonderBrick>> playerIdVectorHashMap;
    private ArrayList<Integer> wealthPointRowList;
    public Wonder(){
        wonderbricks = new Vector<Vector<WonderBrick>>();
        wonderbricks.add(new Vector<WonderBrick>());
        playerIdVectorHashMap = new HashMap<>();
    }
    @Override
    public void onTurnEnded() {
        for (Vector<WonderBrick> wonderBricks : wonderbricks) {
            {
                if (wonderBricks.size() < 6) {
                    wonderBricks.add(new NeutralBrick());
                    break;
                }
            }
        }

    }
    public Vector<Vector<WonderBrick>> getWonderBricks() {
        return wonderbricks;
    }
    public int getWonderSize() {
        int size = 0;
        for (Vector<WonderBrick> wonderBricks : wonderbricks) {
            {
               size = size + wonderBricks.size();
            }
        }
        return size;
    }
    public void addBrick(PlayerId pid) {
        boolean isBrickAdded = false;
        PlayerBrick playerbrick = new PlayerBrick(pid);
        if(!playerIdVectorHashMap.containsKey(pid)){
            playerIdVectorHashMap.put(pid, new Vector<WonderBrick>());
        }

        playerIdVectorHashMap.get(pid).add(playerbrick);
        if(wonderbricks.size()==0){
            wonderbricks.add(new Vector<WonderBrick>());
        }

        for (Vector<WonderBrick> wonderBricks : wonderbricks) {
            {
                //max size of bricks in each row = 6
                if (wonderBricks.size() < 6) {
                    wonderBricks.add(playerbrick);
                    isBrickAdded = true;
                    break;
                }
            }
        }
        if(!isBrickAdded){
            Vector<WonderBrick> wonderBrick= new Vector<>();
            wonderBrick.add(playerbrick);
            wonderbricks.add(wonderBrick);
        }
    }
    public int getBrickCost(PlayerId playerId) {
        return getCost(playerId);
    }
    private int getCost(PlayerId playerId){
        int cost=0;
        //Checking whether the first 4 row is full or not
        if(wonderbricks.size()<4) {
                cost = playerIdVectorHashMap.get(playerId).size()+1;
        }
        else{
                cost = playerIdVectorHashMap.get(playerId).size()+2;
        }
        return cost;
    }

    public int getWealthPoints(PlayerId playerId) {
        int currentPlayerBrick = 0;
        int otherPlayerBrick = 0;
        int points = 0;
        int neutral = 0;
        if(wealthPointRowList==null){
            wealthPointRowList = new ArrayList<>();
        }
        for (Vector<WonderBrick> wonderBricks : wonderbricks) {
            currentPlayerBrick=neutral=otherPlayerBrick=0;
            for (WonderBrick wonderbrick : wonderBricks) {
                if (playerIdVectorHashMap.get(playerId).contains(wonderbrick)) {
                    currentPlayerBrick++;
                }
                else if(!wonderbrick.isNeutral()){
                    otherPlayerBrick++;
                }
                else if(wonderbrick.isNeutral()) {
                    neutral++;
                }
            }
            points = points + getWealthPointFromTable(currentPlayerBrick,otherPlayerBrick,neutral);
            wealthPointRowList.add(points);
        }
        return points;
    }
    private int getWealthPointFromTable(int current, int other, int neutral){
        int points = 0;
        if(other>0 && current>0){
            points = (current)*10/(current+other);
        }
        else if(current>0) {
            points = 10;
        }
        return points;
    }
    public Vector<WonderBrick> getPlayerBricks(PlayerId playerId){
        return playerIdVectorHashMap.get(playerId);
    }


}

