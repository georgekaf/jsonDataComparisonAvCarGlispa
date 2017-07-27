package adsParser;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;

import main.Ads;
import main.FileStreamer;
import main.FileWriter;
import main.JsonParser;
import main.JsonStreamer;


public class TestJsonParser {
	 @Rule
	 public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Test
	public void test_Json_Streamer_Contents() throws IOException {
		File tempFile = tempFolder.newFile("test5.json");
	    Assert.assertTrue(new File(tempFile.getAbsolutePath()).exists());
	    
	    String testWrite = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 17.3" + 
		"		    }" + "]";
	    
	    FileWriter.writeFile(new File(tempFile.getAbsolutePath()), testWrite);

		
		FileStreamer parser = new FileStreamer();
        InputStream fileStream = parser.parseFile(tempFile.getAbsolutePath());
        
        JsonStreamer JsonParser = new JsonStreamer();
        JsonReader jsonStringReader = JsonParser.readJsonStream(fileStream);
        
        jsonStringReader.beginArray();
      
        Gson gson = new GsonBuilder().create();
        FileWriter discrepancyWriter = new FileWriter();
       
        while (jsonStringReader.hasNext()) {
            // Read data into object model
            Ads ad = gson.fromJson(jsonStringReader, Ads.class);
            
            Assert.assertEquals(new String(ad.getTimestamp()) , new String("2016-08-05T00:00:00"));
            Assert.assertEquals(new Double(ad.getImpressions()) , new Double(898));
            Assert.assertEquals(new Double(ad.getSpend()) , new Double(17.3));
        }
        
		tempFile.delete();
	}
	
	@Test
	public void test_Json_Streamer_Contents_Ad_Values_False() throws IOException {
		File tempFile = tempFolder.newFile("test5.json");
	    Assert.assertTrue(new File(tempFile.getAbsolutePath()).exists());
	    
	    String testWrite = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 17.3" + 
		"		    }" + "]";
	    
	    FileWriter.writeFile(new File(tempFile.getAbsolutePath()), testWrite);

		
		FileStreamer parser = new FileStreamer();
        InputStream fileStream = parser.parseFile(tempFile.getAbsolutePath());
        
        JsonStreamer JsonParser = new JsonStreamer();
        JsonReader jsonStringReader = JsonParser.readJsonStream(fileStream);
        
        jsonStringReader.beginArray();
      
        Gson gson = new GsonBuilder().create();
        FileWriter discrepancyWriter = new FileWriter();
       
        while (jsonStringReader.hasNext()) {
            // Read data into object model
            Ads ad = gson.fromJson(jsonStringReader, Ads.class);
            
            Assert.assertNotEquals(new String(ad.getTimestamp()) , new String("2026-09-05T00:00:00"));
            Assert.assertNotEquals(new Double(ad.getImpressions()) , new Double(81198));
            Assert.assertNotEquals(new Double(ad.getSpend()) , new Double(137.3));
        }
        
		tempFile.delete();
	}
	
	@Test(expected=MalformedJsonException.class)
	public void test_Json_Streamer_Malformed_Contents() throws IOException {
		File tempFile = tempFolder.newFile("test5.json");
	    Assert.assertTrue(new File(tempFile.getAbsolutePath()).exists());
	    
	    String testWrite = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 17.3" + 
		"		    }," + "]";
	    
	    FileWriter.writeFile(new File(tempFile.getAbsolutePath()), testWrite);

		
		FileStreamer parser = new FileStreamer();
        InputStream fileStream = parser.parseFile(tempFile.getAbsolutePath());
        
        JsonStreamer JsonParser = new JsonStreamer();
        JsonReader jsonStringReader = JsonParser.readJsonStream(fileStream);
        
        jsonStringReader.beginArray();
        
        GsonBuilder gsonBuild = new GsonBuilder();
        Gson gson = gsonBuild.create();
        
        FileWriter discrepancyWriter = new FileWriter();
       
        while (jsonStringReader.hasNext()) {
        	
            // Read data into object model
            Ads ad = gson.fromJson(jsonStringReader, Ads.class);
            
            Assert.assertEquals(new String(ad.getTimestamp()) , new String("2016-08-05T00:00:00"));
            Assert.assertEquals(new Double(ad.getImpressions()) , new Double(898));
            Assert.assertEquals(new Double(ad.getSpend()) , new Double(17.3));
        }
        
		tempFile.delete();
	}

	@Test(expected=JsonSyntaxException.class)
	public void test_Json_Streamer_Malformed_Contents2() throws IOException {
		File tempFile = tempFolder.newFile("test5.json");
	    Assert.assertTrue(new File(tempFile.getAbsolutePath()).exists());
	    
	    String testWrite = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 17.3" + 
		"		    " + "]";
	    
	    FileWriter.writeFile(new File(tempFile.getAbsolutePath()), testWrite);

		
		FileStreamer parser = new FileStreamer();
        InputStream fileStream = parser.parseFile(tempFile.getAbsolutePath());
        
        JsonStreamer JsonParser = new JsonStreamer();
        JsonReader jsonStringReader = JsonParser.readJsonStream(fileStream);
        
        jsonStringReader.beginArray();
        
        GsonBuilder gsonBuild = new GsonBuilder();
        Gson gson = gsonBuild.create();
        
        FileWriter discrepancyWriter = new FileWriter();
       
        while (jsonStringReader.hasNext()) {
        	
            // Read data into object model
            Ads ad = gson.fromJson(jsonStringReader, Ads.class);
            
            Assert.assertEquals(new String(ad.getTimestamp()) , new String("2016-08-05T00:00:00"));
            Assert.assertEquals(new Double(ad.getImpressions()) , new Double(898));
            Assert.assertEquals(new Double(ad.getSpend()) , new Double(17.3));
        }
        
		tempFile.delete();
	}
	
	@Test
	public void test_Main_Proper_Mismatching_Dates() throws IOException {
		//Create companyA file
		File companyAFile = tempFolder.newFile("test6.json");
	    Assert.assertTrue(new File(companyAFile.getAbsolutePath()).exists());
	    
	    String companyAJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 17.3" + 
		"		    }" + "]";
	    
	    FileWriter.writeFile(new File(companyAFile.getAbsolutePath()), companyAJson);
		
		//Create companyB file
	    File companyBFile = tempFolder.newFile("test7.json");
	    String companyBJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2017-08-05T00:00:00\"," + 
		"		        \"impressions\": 2898," + 
		"		        \"spend\": 17.3" + 
		"		    }" + "]";
		
	    Assert.assertTrue(new File(companyBFile.getAbsolutePath()).exists());
	    FileWriter.writeFile(new File(companyBFile.getAbsolutePath()), companyBJson);
	    
	    String[] args = {
	    		new String(companyAFile.getAbsolutePath()),
	    		new String(companyBFile.getAbsolutePath())
	    		};
	    
	    JsonParser.main(args);
	    companyAFile.delete();
	    companyBFile.delete();
	}
	
	@Test
	public void test_Main_Proper_Contents() throws IOException {
		//Create companyA file
		File companyAFile = tempFolder.newFile("test6.json");
	    Assert.assertTrue(new File(companyAFile.getAbsolutePath()).exists());
	    
	    String companyAJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 17.3" + 
		"		    }" + "]";
	    
	    FileWriter.writeFile(new File(companyAFile.getAbsolutePath()), companyAJson);
		
		//Create companyB file
	    File companyBFile = tempFolder.newFile("test7.json");
	    String companyBJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 2898," + 
		"		        \"spend\": 17.3" + 
		"		    }" + "]";
		
	    Assert.assertTrue(new File(companyBFile.getAbsolutePath()).exists());
	    FileWriter.writeFile(new File(companyBFile.getAbsolutePath()), companyBJson);
	    
	    String[] args = {
	    		new String(companyAFile.getAbsolutePath()),
	    		new String(companyBFile.getAbsolutePath())
	    		};
	    JsonParser.main(args);
	    companyAFile.delete();
	    companyBFile.delete();
	}
	
	@Test
	public void test_Main_Contents_Missing_Contents_From_Second_File() throws IOException {
		//Create companyA file
		File companyAFile = tempFolder.newFile("test8.json");
	    Assert.assertTrue(new File(companyAFile.getAbsolutePath()).exists());
	    
	    String companyAJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 17.3" + 
		"		    }" + "]";
	    
	    FileWriter.writeFile(new File(companyAFile.getAbsolutePath()), companyAJson);
		
		//Create companyB file
	    File companyBFile = tempFolder.newFile("test9.json");
	    String companyBJson = "";
		
	    Assert.assertTrue(new File(companyBFile.getAbsolutePath()).exists());
	    FileWriter.writeFile(new File(companyBFile.getAbsolutePath()), companyBJson);
	    
	    String[] args = {
	    		new String(companyAFile.getAbsolutePath()),
	    		new String(companyBFile.getAbsolutePath())
	    		};
	    
	    JsonParser.main(args);
	    companyAFile.delete();
	    companyBFile.delete();
	}
	
	@Test
	public void test_Main_Contents_Missing_Contents_From_Both_Files() throws IOException {
		//Create companyA file
		File companyAFile = tempFolder.newFile("test10.json");
	    Assert.assertTrue(new File(companyAFile.getAbsolutePath()).exists());
	    
	    String companyAJson = "";
	    
	    FileWriter.writeFile(new File(companyAFile.getAbsolutePath()), companyAJson);
		
		//Create companyB file
	    File companyBFile = tempFolder.newFile("test11.json");
	    String companyBJson = "";
		
	    Assert.assertTrue(new File(companyBFile.getAbsolutePath()).exists());
	    FileWriter.writeFile(new File(companyBFile.getAbsolutePath()), companyBJson);
	    
	    String[] args = {
	    		new String(companyAFile.getAbsolutePath()),
	    		new String(companyBFile.getAbsolutePath())
	    		};
	    
	    JsonParser.main(args);
	    companyAFile.delete();
	    companyBFile.delete();
	}
	
	@Test
	public void test_Main_Contents_Missing_Both_Files() throws IOException {

	    String[] args = {
	    		new String("missing/file/test100.json"),
	    		new String("missing/file/test200.json"),
	    		};
	    
	    JsonParser.main(args);
	}
	
	@Test
	public void test_Main_Contents_Missing_Args() throws IOException {
	    String[] args = {"",""};
	    JsonParser.main(args);
	}
	
	@Test
	public void test_Main_Contents_Missing_Args2() throws IOException {
	    String[] args = {};
	    JsonParser.main(args);
	}

	@Test
	public void test_Main_Contents_Missing_Args3() throws IOException {
	    String[] args = {new String(""),new String("")};
	    JsonParser.main(args);
	}
	
	@Test
	public void test_Main_Contents_Missing_Second_File() throws IOException {
		//Create companyA file
		File companyAFile = tempFolder.newFile("test12.json");
	    Assert.assertTrue(new File(companyAFile.getAbsolutePath()).exists());
	    
	    String companyAJson = "";
	    
	    FileWriter.writeFile(new File(companyAFile.getAbsolutePath()), companyAJson);
		

	    String[] args = {
	    		new String(companyAFile.getAbsolutePath()),
	    		new String("")
	    		};
	    
	    JsonParser.main(args);
	    companyAFile.delete();
	}
	
	@Test
	public void test_Main_Contents_Missing_First_File() throws IOException {
		//Create companyA file
		File companyAFile = tempFolder.newFile("test13.json");
	    Assert.assertTrue(new File(companyAFile.getAbsolutePath()).exists());
	    
	    String companyAJson = "";
	    
	    FileWriter.writeFile(new File(companyAFile.getAbsolutePath()), companyAJson);
		

	    String[] args = {
	    		new String(""),
	    		new String(companyAFile.getAbsolutePath()),
	    		};
	    
	    JsonParser.main(args);
	    companyAFile.delete();
	}
	
	@Test
	public void test_Main_Proper_Calc_No_Diff() throws IOException {
		//Create companyA file
		File companyAFile = tempFolder.newFile("test6.json");
	    Assert.assertTrue(new File(companyAFile.getAbsolutePath()).exists());
	    
	    String companyAJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 17.3" + 
		"		    }" + "]";
	    
	    FileWriter.writeFile(new File(companyAFile.getAbsolutePath()), companyAJson);
		
		//Create companyB file
	    File companyBFile = tempFolder.newFile("test7.json");
	    String companyBJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 17.3" + 
		"		    }" + "]";
		
	    Assert.assertTrue(new File(companyBFile.getAbsolutePath()).exists());
	    FileWriter.writeFile(new File(companyBFile.getAbsolutePath()), companyBJson);
	    
	    String[] args = {
	    		new String(companyAFile.getAbsolutePath()),
	    		new String(companyBFile.getAbsolutePath())
	    		};
	    JsonParser.main(args);
	    companyAFile.delete();
	    companyBFile.delete();
	}
	
	@Test
	public void test_Main_Proper_Calc_Diff_Impression() throws IOException {
		//Create companyA file
		File companyAFile = tempFolder.newFile("test6.json");
	    Assert.assertTrue(new File(companyAFile.getAbsolutePath()).exists());
	    
	    String companyAJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 17.3" + 
		"		    }" + "]";
	    
	    FileWriter.writeFile(new File(companyAFile.getAbsolutePath()), companyAJson);
		
		//Create companyB file
	    File companyBFile = tempFolder.newFile("test7.json");
	    String companyBJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 10," + 
		"		        \"spend\": 17.3" + 
		"		    }" + "]";
		
	    Assert.assertTrue(new File(companyBFile.getAbsolutePath()).exists());
	    FileWriter.writeFile(new File(companyBFile.getAbsolutePath()), companyBJson);
	    
	    String[] args = {
	    		new String(companyAFile.getAbsolutePath()),
	    		new String(companyBFile.getAbsolutePath())
	    		};
	    JsonParser.main(args);
	    companyAFile.delete();
	    companyBFile.delete();
	}
	
	@Test
	public void test_Main_Proper_Calc_Diff_Spend() throws IOException {
		//Create companyA file
		File companyAFile = tempFolder.newFile("test6.json");
	    Assert.assertTrue(new File(companyAFile.getAbsolutePath()).exists());
	    
	    String companyAJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 17.3" + 
		"		    }" + "]";
	    
	    FileWriter.writeFile(new File(companyAFile.getAbsolutePath()), companyAJson);
		
		//Create companyB file
	    File companyBFile = tempFolder.newFile("test7.json");
	    String companyBJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 172.3" + 
		"		    }" + "]";
		
	    Assert.assertTrue(new File(companyBFile.getAbsolutePath()).exists());
	    FileWriter.writeFile(new File(companyBFile.getAbsolutePath()), companyBJson);
	    
	    String[] args = {
	    		new String(companyAFile.getAbsolutePath()),
	    		new String(companyBFile.getAbsolutePath())
	    		};
	    JsonParser.main(args);
	    companyAFile.delete();
	    companyBFile.delete();
	}
	
	@Test
	public void test_Main_Proper_Calc_Diff_Spend2() throws IOException {
		//Create companyA file
		File companyAFile = tempFolder.newFile("test6.json");
	    Assert.assertTrue(new File(companyAFile.getAbsolutePath()).exists());
	    
	    String companyAJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 172.3" + 
		"		    }" + "]";
	    
	    FileWriter.writeFile(new File(companyAFile.getAbsolutePath()), companyAJson);
		
		//Create companyB file
	    File companyBFile = tempFolder.newFile("test7.json");
	    String companyBJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 898," + 
		"		        \"spend\": 17.3" + 
		"		    }" + "]";
		
	    Assert.assertTrue(new File(companyBFile.getAbsolutePath()).exists());
	    FileWriter.writeFile(new File(companyBFile.getAbsolutePath()), companyBJson);
	    
	    String[] args = {
	    		new String(companyAFile.getAbsolutePath()),
	    		new String(companyBFile.getAbsolutePath())
	    		};
	    JsonParser.main(args);
	    companyAFile.delete();
	    companyBFile.delete();
	}
	
	@Test
	public void test_Main_Proper_Calc_Diff_As_Strings() throws IOException {
		//Create companyA file
		File companyAFile = tempFolder.newFile("test6.json");
	    Assert.assertTrue(new File(companyAFile.getAbsolutePath()).exists());
	    
	    String companyAJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": 50," + 
		"		        \"spend\": 10" + 
		"		    }" + "]";
	    
	    FileWriter.writeFile(new File(companyAFile.getAbsolutePath()), companyAJson);
		
		//Create companyB file
	    File companyBFile = tempFolder.newFile("test7.json");
	    String companyBJson = "[" + 
		"		    {" + 
		"		        \"timestamp\": \"2016-08-05T00:00:00\"," + 
		"		        \"impressions\": \"100\"," + 
		"		        \"spend\": \"100\" " + 
		"		    }" + "]";
		
	    Assert.assertTrue(new File(companyBFile.getAbsolutePath()).exists());
	    FileWriter.writeFile(new File(companyBFile.getAbsolutePath()), companyBJson);
	    
	    String[] args = {
	    		new String(companyAFile.getAbsolutePath()),
	    		new String(companyBFile.getAbsolutePath())
	    		};
	    JsonParser.main(args);
	    companyAFile.delete();
	    companyBFile.delete();
	}
}
