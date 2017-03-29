package filehandling;

import model.FileExporter;
import model.FileImporter;
import model.Map;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class FileHandling {
    @Test
    public void readFile() throws IOException {
        FileImporter fileImporter = new FileImporter();
        Map map = fileImporter.readFile("map/map2.txt");
        if(map!=null)
        assertTrue(map.hasTiles());

    }
    @Test
    public void writeFile() throws IOException {
        Map map = new Map();
        map.initialize();
        FileExporter fileExporter = new FileExporter();
        //Give File Name
        fileExporter.writeToFile(map,"map/map2.txt");
    }
}
