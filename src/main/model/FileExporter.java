package model;


import model.tile.Location;
import model.tile.Tile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileExporter {
    public void writeToFile(Map map, String filename){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)))) {
            if(map.hasTiles())
            {
                for(Location location : map.getLocations()){
                    Tile tile = map.getTile(location);
                    bw.write(" \""+"(\" "+ location.getlocationString() +" \")"+"\"");
                    bw.write(" \""+String.valueOf(tile.getTerrain())+"\" ");
                    bw.write("\n");
                }
            }
            else
            System.out.println("Empty Map");


        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}

