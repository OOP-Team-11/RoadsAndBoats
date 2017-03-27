package model;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileExporter {
    public void writeToFile(Map map, String filename){
        ClassLoader classLoader = getClass().getClassLoader();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)))) {

            String content = "This is the content to write into file\n";
            bw.write(content);

            // no need to close it.
//            bw.close();

            System.out.println("Done");


        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}

