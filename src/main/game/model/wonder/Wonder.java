package game.model.wonder;
import game.model.Player;
import game.model.PlayerId;

import java.util.HashMap;
import java.util.Vector;

public class Wonder implements TurnObserver {
    private Vector<WonderBrick> player1;
    private Vector<WonderBrick> player2;
    Vector<Vector<WonderBrick>> wonderbricks;
    private HashMap<PlayerId, Vector<WonderBrick>> playerIdVectorHashMap;
    public Wonder(){
        wonderbricks = new Vector<Vector<WonderBrick>>();
        wonderbricks.add(new Vector<WonderBrick>());
        playerIdVectorHashMap = new HashMap<>();
    }
    @Override
    public void onTurnEnded() {
        playerIdVectorHashMap = new HashMap<PlayerId,Vector<WonderBrick>>();
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
        boolean flag = false;
        if(!playerIdVectorHashMap.containsKey(pid)){
            playerIdVectorHashMap.put(pid, new Vector<WonderBrick>());
        }

        playerIdVectorHashMap.get(pid).add(new PlayerBrick(pid));
        if(wonderbricks.size()==0){
            wonderbricks.add(new Vector<WonderBrick>());
        }

        for (Vector<WonderBrick> wonderBricks : wonderbricks) {
            {
                //max size of bricks in each row = 6
                if (wonderBricks.size() < 6) {
                    wonderBricks.add(new PlayerBrick(pid));
                    flag = true;
                    break;
                }
            }
        }
        if(!flag){
            Vector<WonderBrick> wonderBrick= new Vector<>();
            wonderBrick.add(new PlayerBrick(pid));
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
}
