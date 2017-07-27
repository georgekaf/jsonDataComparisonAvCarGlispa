/**
 * manages the opening of files as streams
 */
package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.google.gson.stream.JsonReader;

/**
 * @author giorgos
 *
 */
public class FileStreamer {
    
    /**
     * Initialize a file and parse it a s stream  
     * 
     * @param filePath
     * @return InputStream
     * @throws FileNotFoundException
     */
    public InputStream parseFile(String filePath) throws NullPointerException, FileNotFoundException {
        try {
            File jsonfile = new File(filePath);
               InputStream fileStream = new FileInputStream(jsonfile);
               return fileStream;
        } catch(Exception e) {
            System.out.println("Error: Could not find file.");
            System.out.println(e.getMessage()); 
            throw e;
        }

        
     }
    
    /**
     * 
     * @param filestreams
     * @param filepath
     * @return
     * @throws IOException
     */
    public List<JsonReader> addFileStream(List<JsonReader> filestreams, String filepath) throws IOException {
        FileStreamer parser = new FileStreamer();
        InputStream fileStream;
        try {
            fileStream = parser.parseFile(filepath);
            if (fileStream != null) {
                try {
                    JsonStreamer JsonParser = new JsonStreamer();
                    filestreams.add(JsonParser.readJsonStream(fileStream));    
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }    
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return filestreams;
    }
}
