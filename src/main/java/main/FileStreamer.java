package main;

import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.List;

/**
 *
 * manages the opening of files as streams
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
     * Create a filestream and add it to the provided ArrayList
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