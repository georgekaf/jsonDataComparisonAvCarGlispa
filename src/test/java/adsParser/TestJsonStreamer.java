/**
 *
 */
package adsParser;

import com.google.gson.stream.JsonReader;
import fileManipulation.FileWriter;
import main.FileStreamer;
import main.JsonStreamer;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

/**
 * @author giorgos
 */
public class TestJsonStreamer {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void test_Json_Streamer_Type() throws IOException {
        File tempFile = tempFolder.newFile("test3.json");
        Assert.assertTrue(new File(tempFile.getAbsolutePath()).exists());

        FileStreamer parser = new FileStreamer();
        InputStream fileStream = parser.parseFile(tempFile.getAbsolutePath());

        JsonStreamer JsonParser = new JsonStreamer();
        JsonReader jsonStringReader = JsonParser.readJsonStream(fileStream);

        Assert.assertTrue(jsonStringReader instanceof JsonReader);
        Assert.assertNotEquals(jsonStringReader, null);

        tempFile.delete();
    }

    @Test(expected = Exception.class)
    public void test_Json_Streamer_Empty_Contents() throws EOFException, IOException, UnsupportedEncodingException, FileNotFoundException {
        File tempFile = tempFolder.newFile("test3.json");
        try {
            Assert.assertTrue(new File(tempFile.getAbsolutePath()).exists());

            String testWrite = "";
            FileWriter.writeFile(new File(tempFile.getAbsolutePath()), testWrite);

            FileStreamer parser = new FileStreamer();
            InputStream fileStream = parser.parseFile(tempFile.getAbsolutePath());

            JsonStreamer JsonParser = new JsonStreamer();
            JsonReader jsonStringReader = JsonParser.readJsonStream(fileStream);

            jsonStringReader.beginArray();

            Assert.assertTrue(jsonStringReader.hasNext());
        } finally {
            tempFile.delete();
        }
    }

    @Test
    public void test_Json_Streamer_Contents() throws IOException {
        File tempFile = tempFolder.newFile("test4.json");
        Assert.assertTrue(new File(tempFile.getAbsolutePath()).exists());

        String testWrite = "[" +
                "            {" +
                "                \"timestamp\": \"2016-08-05T00:00:00\"," +
                "                \"impressions\": 898," +
                "                \"spend\": 17.3" +
                "            }," +
                "            {" +
                "                \"timestamp\": \"2016-08-05T01:00:00\"," +
                "                \"impressions\": 1424," +
                "                \"spend\": 32.45" +
                "            }," +
                "            {" +
                "                \"timestamp\": \"2016-08-05T02:00:00\"," +
                "                \"impressions\": 736," +
                "                \"spend\": 8.74" +
                "            }]";

        FileWriter.writeFile(new File(tempFile.getAbsolutePath()), testWrite);


        FileStreamer parser = new FileStreamer();
        InputStream fileStream = parser.parseFile(tempFile.getAbsolutePath());

        JsonStreamer JsonParser = new JsonStreamer();
        JsonReader jsonStringReader = JsonParser.readJsonStream(fileStream);

        jsonStringReader.beginArray();

        Assert.assertTrue(jsonStringReader.hasNext());

        tempFile.delete();
    }
}