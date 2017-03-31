package filehandling;

import model.FileExporter;
import model.FileImporter;
import model.Map;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class FileHandling {

    //TODO: fix this test to verify correct map is created
    @Test
    public void readFile() throws IOException {
        File directory = new File("./");
        File file = new File(directory.getAbsolutePath().replace(".","") + "/map/map2.txt");

        FileImporter fileImporter = new FileImporter();
        Map map = fileImporter.readFile(file);
        assertTrue(map.hasTiles());

    }

    //TODO: fix this test to verify file output
    @Test
    public void writeFile() throws IOException {
        Map map = new Map();
        FileExporter fileExporter = new FileExporter();
        fileExporter.writeToFile(map,"/Users/anip/Develop/RoadsAndBoats/src/map2.txt");
    }
}
