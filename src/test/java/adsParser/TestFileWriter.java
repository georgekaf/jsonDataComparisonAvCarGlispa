package adsParser;

import fileManipulation.FileWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestFileWriter {

    @Test
    public void TestFileAppend() throws IOException {
        String testWrite = "[\"2017-07-23T13:08:00\",\"2017-07-23T15:00:00\"]";

        FileWriter filewrite = new FileWriter();
        filewrite.fileAppend(testWrite);

        Assert.assertTrue(new File(filewrite.getFilePathName()).exists());

        FileReader input = new FileReader(filewrite.getFilePathName());
        BufferedReader bufRead = new BufferedReader(input);
        String fileContents = bufRead.readLine();
        bufRead.close();

        Assert.assertEquals(new String(fileContents), testWrite);

        //delete the fake results file
        new File(filewrite.getFilePathName()).delete();

    }
}