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
 * Generate date (or other) formatted filenames.
 *
 * @author giorgos
 */
public class FileWriter {

    /*
     *
     *
     */
    public static String generateDateFormatFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date currentDate = new Date();

        String filename = System.getProperty("user.dir")
                + File.separator
                + "discrepancies_"
                + dateFormat.format(currentDate) ;

        String fileNamePath = fileExistsRename(filename, new String(".json"));

        return fileNamePath;
    }

    public static String fileExistsRename(String filename, String extension){
        // Because of the unit test cases
        // all their results were saved in the same file
        // now we check for the file and if exists
        // we increment the name by 1
        int num = 0;
        String filenameToSave = filename + extension ;
        String filePath = new File(filenameToSave);
        while(this.filePath.exists()) {
            filenameToSave = filename + "-" + (num++) + extension;
            filePath = new File(filenameToSave);
        }

        return filePath;
    }
}
