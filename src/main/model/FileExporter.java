package model;


import direction.TileEdgeDirection;
import model.tile.Location;
import model.tile.Tile;
import model.tile.TileEdge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileExporter {
    public void writeToFile(Map map, String filename){
        int count = 0;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)))) {
            if(map.hasTiles())
            {
                for(Location location : map.getAllLocations()){
                    Tile tile = map.getTile(location);
                    bw.write("( "+ location.getLocationString() +" )");
                    bw.write(" "+String.valueOf(tile.getTerrain())+" ");
                    if(riverString(tile).length()>0)
                    {
                        bw.write(" ( "+ riverString(tile)+" ) ");
                    }
                    count++;
                    if(count<map.getAllLocations().size())
                        bw.write("\n");
                }
            }
            else
            System.out.println("Empty Map");
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private StringBuffer riverString(Tile tile) {
        StringBuffer riverString = new StringBuffer();
        if(tile.getRiverConfiguration().canConnectNorth()) {
            riverString.append("1 ");
        }
        if(tile.getRiverConfiguration().canConnectNortheast()){
             riverString.append("2 ");
        }
        if(tile.getRiverConfiguration().canConnectSoutheast()){
            riverString.append("3 ");
        }
        if(tile.getRiverConfiguration().canConnectSouth()){
            riverString.append("4 ");
        }
        if(tile.getRiverConfiguration().canConnectSouthwest()){
            riverString.append("5 ");
        }
        if(tile.getRiverConfiguration().canConnectNorthwest()){
            riverString.append("6 ");
        }

        return riverString;
    }

    private String getLocationString(Location location) {
        return location.getX() + " " + location.getY() + " " + location.getZ();
    }

}

