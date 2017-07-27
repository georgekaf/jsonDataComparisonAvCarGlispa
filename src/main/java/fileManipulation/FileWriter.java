package fileManipulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Write (append) strings to file.
 *
 * @author giorgos
 */
public class FileWriter {
    private File file;      // The current file Object
    private String filePathName;

    /**
     * Set the private fields
     */
    public FileWriter() {
        setFilePathName();
        this.file = new File(this.filePathName);
    }

    /**
     * Generate a unique (datetime) filename
     * and set it to field filePathName
     *
     * @return
     */
    public void setFilePathName() {
        // Generate and use a filename
        this.filePathName = FileNameGenerator.generateDateFormatFile();
    }

    /**
     * Get private field
     *
     * @return
     */
    public String getFilePathName() {
        return filePathName;
    }

    /**
     * Append string to file without loading the entire file in memory.
     * 
     * @param str
     * @throws IOException
     */
    public void fileAppend(String str) throws IOException {
        writeFile(this.file, str);
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
}
