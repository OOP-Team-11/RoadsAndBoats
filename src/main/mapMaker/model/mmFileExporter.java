package mapMaker.model;


import mapMaker.direction.mmTileEdgeDirection;
import mapMaker.model.tile.mmLocation;
import mapMaker.model.tile.mmTile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class mmFileExporter {
    public void writeToFile(mmMap mmMap, String filename){
        int count = 0;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)))) {
            if(mmMap.hasTiles())
            {
                for(mmLocation mmLocation : mmMap.getAllLocations()){
                    mmTile mmTile = mmMap.getTile(mmLocation);
                    bw.write("( "+ mmLocation.getLocationString() +" )");
                    bw.write(" "+String.valueOf(mmTile.getMmTerrain())+" ");
                    if(riverString(mmTile).length()>0)
                    {
                        bw.write(" ( "+ riverString(mmTile)+" ) ");
                    }
                    count++;
                    if(count< mmMap.getAllLocations().size())
                        bw.write("\n");
                }
            }
            else
            System.out.println("Empty mmMap");
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private StringBuffer riverString(mmTile mmTile) {
        StringBuffer riverString = new StringBuffer();
        if(mmTile.getMmRiverConfiguration().canConnectNorth()) {
            riverString.append("1 ");
        }

        if(mmTile.getTileEdge(mmTileEdgeDirection.getNorthEast()).hasRiver()){
             riverString.append("2 ");
        }
        if(mmTile.getTileEdge(mmTileEdgeDirection.getSouthEast()).hasRiver()){
            riverString.append("3 ");
        }
        if(mmTile.getTileEdge(mmTileEdgeDirection.getSouth()).hasRiver()){
            riverString.append("4 ");
        }
        if(mmTile.getTileEdge(mmTileEdgeDirection.getSouthWest()).hasRiver()){
            riverString.append("5 ");
        }
        if(mmTile.getTileEdge(mmTileEdgeDirection.getNorthWest()).hasRiver()){
            riverString.append("6 ");
        }

        return riverString;
    }

    private String getLocationString(mmLocation mmLocation) {
        return mmLocation.getX() + " " + mmLocation.getY() + " " + mmLocation.getZ();
    }

}

