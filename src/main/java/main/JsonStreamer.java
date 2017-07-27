package main;

import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Covert file streams to JsonReader objects
 */
public class JsonStreamer {
    
    /**
     * Parse json from a file input stream
     * 
     * @param in
     * @return 
     * @throws IOException
     */
    public JsonReader readJsonStream(InputStream in) throws IOException {
        try {
            InputStreamReader streamReader = new InputStreamReader(in, "UTF-8");
            JsonReader reader = new JsonReader(streamReader);

            return reader;
        } catch (Exception e) {
            //e.printStackTrace();
            throw e;
        }
    }
}