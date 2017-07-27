package fileManipulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Write (append) strings to file.
 *
 * @author giorgos
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
        // Generate and use a filename
        this.filePathName = FileNameGenerator.generate();
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
