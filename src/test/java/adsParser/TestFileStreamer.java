package adsParser;

import main.*;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.codehaus.plexus.util.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonReader;

public class TestFileStreamer {
	 @Rule
	  public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Test(expected=Exception.class)
	public void testParseFileThatNotExists() throws FileNotFoundException {
		FileStreamer filestreamer = new FileStreamer(); 
		InputStream jsonFileStream = filestreamer.parseFile(new String(""));
	}
	
	@Test(expected=Exception.class)
	public void testParseFileThatIsNull() throws FileNotFoundException {
		FileStreamer filestreamer = new FileStreamer(); 
		InputStream jsonFileStream = filestreamer.parseFile(null);
	}
	
	@Test
	public void testParseFileThatExists() throws NullPointerException, IOException {
		File tempFile = tempFolder.newFile("test1.txt");
	     
	    Assert.assertTrue(new File(tempFile.getAbsolutePath()).exists());
	   
	     // Write something to it.
//	     FileUtils.writeStringToFile(tempFile, "hello world");
		FileStreamer filestreamer = new FileStreamer(); 
		InputStream jsonFileStream = filestreamer.parseFile(tempFile.getAbsolutePath());
		tempFile.delete();
	}
	
	@Test
	public void testAddFileToListWithFileStreams() throws IOException {
		File tempFile = tempFolder.newFile("test2.txt");
	    Assert.assertTrue(new File(tempFile.getAbsolutePath()).exists());

        List<JsonReader> filestreams = new ArrayList<JsonReader>();
		FileStreamer filestreamer = new FileStreamer(); 
		filestreams = filestreamer.addFileStream(filestreams, tempFile.getAbsolutePath());
		
		Assert.assertEquals(filestreams.size(), 1);
		Assert.assertTrue(filestreams.get(0) instanceof JsonReader);
		
		tempFile.delete();
	}
}
