package fileManipulation;

import java.io.*;

/**
 * Write (append) strings to file.
 *
 * @author giorgos
 */
public class FileWriter {
    private File file;              // The current output file Object
    private String filePathName;    // The name of the current output file loaded in memory

    /**
     * Initialize class and set the private fields
     */
    public FileWriter() {
        setFilePathName();
        this.file = new File(this.filePathName);
    }

    /**
     * Generate a unique (datetime) filename
     * and set it to field filePathName
     *
     * @return filePathName
     */
    public void setFilePathName() {
        // Generate and use a filename
        this.filePathName = FileNameGenerator.generateDateFormatFile();
    }

    /**
     * filePathName getter
     *
     * @return filePathName
     */
    public String getFilePathName() {
        return filePathName;
    }

    /**
     * Append string to file without loading the entire file in memory.
     * Get file write.
     * 
     * @param str
     * @throws IOException
     */
    public void fileAppend(String str) throws IOException {
        writeFile(this.file, str);
    }

    /**
     *  Write provided string to the desired file in append mode
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