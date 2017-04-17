package game.model.wonder;
import game.model.PlayerId;
import game.model.tile.Tile;
import game.utilities.observer.MapRenderInfoObserver;
import game.view.render.MapRenderInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class Wonder implements TurnObserver{
    private List<WonderBrick> orderedWonderBricks;
    private Vector<Vector<WonderBrick>> wonderbricks;
    private HashMap<PlayerId, Vector<WonderBrick>> playerIdVectorHashMap;
    private HashMap<PlayerId, Vector<WonderBrick>> playerBrickMapTurn;
    private ArrayList<Integer> wealthPointRowList;
    private IrrigationPoint irrigationPoint;
    private boolean irrigationPointActivated;

    public Wonder() {
        wonderbricks = new Vector<Vector<WonderBrick>>();
        wonderbricks.add(new Vector<WonderBrick>());
        playerIdVectorHashMap = new HashMap<>();
        playerBrickMapTurn = new HashMap<>();
        orderedWonderBricks = new Vector<>();
    }

    @Override
    public void onTurnEnded() {
        addNeutralBrick();
        playerBrickMapTurn.clear();
    }

    public List<WonderBrick> getOrderedWonderBricks() {
        return orderedWonderBricks;
    }

    public void addNeutralBrick() {
        if (wonderbricks.get(wonderbricks.size()-1).size()<maxSize()) {
            wonderbricks.get(wonderbricks.size()-1).add(new NeutralBrick());
            if(checkBrickOnIrrigationPoint(wonderbricks.get(wonderbricks.size()-1).size())){
                setIrrigationPointActivated(true);
            }
        }
        else {
            Vector<WonderBrick> wonderBrick = new Vector<>(maxSize());
            wonderBrick.add(new NeutralBrick());
            wonderbricks.add(wonderBrick);
            if(checkBrickOnIrrigationPoint(wonderBrick.size())){
                setIrrigationPointActivated(true);
            }
        }
        orderedWonderBricks.add(new NeutralBrick());
    }

    private int maxSize() {
        if(wonderbricks.size()<=3){
            return 4;
        }
        else if(wonderbricks.size()<=7){
            return 5;
        }
        else if(wonderbricks.size()<=12)
            return 6;
        else
            return 7;
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
        orderedWonderBricks.add(new PlayerBrick(pid));
        PlayerBrick playerbrick = new PlayerBrick(pid);
        if (!playerIdVectorHashMap.containsKey(pid)) {
            playerIdVectorHashMap.put(pid, new Vector<WonderBrick>());
        }
        if(!playerBrickMapTurn.containsKey(pid)){
            playerBrickMapTurn.put(pid, new Vector<WonderBrick>());
        }

        playerIdVectorHashMap.get(pid).add(playerbrick);
        playerBrickMapTurn.get(pid).add(playerbrick);
        if (wonderbricks.size() == 0) {
            wonderbricks.add(new Vector<WonderBrick>());
        }

        if (wonderbricks.get(wonderbricks.size()-1).size()<maxSize()) {
            wonderbricks.get(wonderbricks.size()-1).add(playerbrick);
                    if(checkBrickOnIrrigationPoint(wonderbricks.get(wonderbricks.size()-1).size())){
                        setIrrigationPointActivated(true);
                    }
                }

        else{
            Vector<WonderBrick> wonderBrick = new Vector<>(maxSize());
            wonderBrick.add(playerbrick);
            wonderbricks.add(wonderBrick);
            if(checkBrickOnIrrigationPoint(wonderBrick.size())){
                setIrrigationPointActivated(true);
            }
        }
    }


    public int getCurrentBrickCost(PlayerId playerId) {
        return getCost(playerId);
    }

    private int getCost(PlayerId playerId) {
        int cost = 0;
        if (!playerIdVectorHashMap.containsKey(playerId)) {
            playerIdVectorHashMap.put(playerId, new Vector<WonderBrick>());
        }
        if(!playerBrickMapTurn.containsKey(playerId)){
            playerBrickMapTurn.put(playerId, new Vector<WonderBrick>());
        }
        //Checking whether the first 4 row is full or not
        if (wonderbricks.size() <= 4) {
            cost = playerBrickMapTurn.get(playerId).size() + 1;
        } else {
            cost = playerBrickMapTurn.get(playerId).size() + 2;
        }
        return cost;
    }

    public int getWealthPoints(PlayerId playerId) {
        int currentPlayerBrick = 0;
        int otherPlayerBrick = 0;
        int points = 0;
        int neutral = 0;
        if (wealthPointRowList == null) {
            wealthPointRowList = new ArrayList<>();
        }
        //No brick added;
        if(!playerIdVectorHashMap.containsKey(playerId)) {
            return 0;
        }
        for (Vector<WonderBrick> wonderBricks : wonderbricks) {
            currentPlayerBrick = neutral = otherPlayerBrick = 0;
            for (WonderBrick wonderbrick : wonderBricks) {
                if (playerIdVectorHashMap.get(playerId).contains(wonderbrick)) {
                    currentPlayerBrick++;
                } else if (!wonderbrick.isNeutral()) {
                    otherPlayerBrick++;
                } else if (wonderbrick.isNeutral()) {
                    neutral++;
                }
            }
            points = points + getWealthPointFromTable(currentPlayerBrick, otherPlayerBrick, neutral);
            wealthPointRowList.add(points);
        }
        return points;
    }

    private int getWealthPointFromTable(int current, int other, int neutral) {
        int points = 0;
        if (other > 0 && current > 0) {
            points = (current) * 10 / (current + other);
        } else if (current > 0) {
            points = 10;
        }
        return points;
    }

    public Vector<WonderBrick> getPlayerBricks(PlayerId playerId) {
        return playerIdVectorHashMap.get(playerId);
    }

    public void setIrrigationPoint(IrrigationPoint irrigationPoint) {
        this.irrigationPoint = irrigationPoint;
    }
    private boolean checkBrickOnIrrigationPoint(int brick){
        if(irrigationPoint!=null)
        return wonderbricks.size()==irrigationPoint.getRow() && brick==irrigationPoint.getBrick();
        else
            return false;
    }

    public boolean isIrrigationPointActivated() {
        return irrigationPointActivated;
    }

    private void setIrrigationPointActivated(boolean irrigationPointActivated) {
        this.irrigationPointActivated = irrigationPointActivated;
    }
}

