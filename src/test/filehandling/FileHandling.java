package filehandling;

import model.FileExporter;
import model.FileImporter;
import model.Map;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileHandling {
    @Test
    public void readFile() throws IOException {
        Map map = new Map();
        FileImporter fileImporter = new FileImporter();
        System.out.println(fileImporter.readFile(map, "map.txt"));

    }
    @Test
    public void writeFile() throws IOException {
        Map map = new Map();
        FileExporter fileExporter = new FileExporter();
        fileExporter.writeToFile(map,"/Users/anip/Develop/RoadsAndBoats/src/map2.txt");
    }
}
