/**
 * Write (append) string to file.
 */
package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author giorgos
 *
 */
public class FileWriter {
    private File filePath;
    private String filePathName;

    /**
     * Set the private fields
     */
    public FileWriter() {
        setFilePathName();
    }
    
    /**
     * Append string to file without loading the entire file in memory.
     * 
     * @param str
     * @throws IOException
     */
    public void fileAppend(String str) throws IOException {
        writeFile(this.filePath, str);
    }
    
    /**
     * 
     * @param filepath
     * @param str
     * @throws IOException
     */
    public static void writeFile (File filepath, String str) throws IOException {
        Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(filepath, true), "UTF-8"
                )
            );
        try {
            if (writer != null) {
                writer.append(str);
//                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } 
    }
    
    /**
     * Generate a unique (datetime) filename 
     * and set it to field filePathName
     * 
     * @return
     */
    public void setFilePathName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date currentDate = new Date();
        
        String filename = System.getProperty("user.dir") 
                + File.separator 
                + "discrepancies_" 
                + dateFormat.format(currentDate) ;
        
        // Because of the unit test cases
        // all their results were saved in the same file
        // now we check for the file and if exists 
        // we increment the name by 1
        int num = 0;
        String filenameToSave = filename + ".json";
        this.filePath = new File(filenameToSave);
        while(this.filePath.exists()) {
            filenameToSave = filename + "-" + (num++) +".json";
            this.filePath = new File(filenameToSave); 
        }
        
        this.filePathName = filenameToSave;
    }

    /**
     * Get private field
     * 
     * @return
     */
    public String getFilePathName() {
        return filePathName;
    }
}
