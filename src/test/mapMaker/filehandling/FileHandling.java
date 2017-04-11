package mapMaker.filehandling;

import mapMaker.model.mmFileImporter;
import mapMaker.model.mmMap;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class FileHandling {

    @Test
    public void readFile() throws IOException {
        File directory = new File("./");
        File file = new File(directory.getAbsolutePath().replace(".","") + "/map/map2.txt");

        mmFileImporter mmFileImporter = new mmFileImporter();
        mmMap mmMap = mmFileImporter.readFile(file);
        assertTrue(mmMap.hasTiles());
    }
}
