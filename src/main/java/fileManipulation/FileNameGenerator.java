package fileManipulation;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Generate date (or other) formatted filenames.
 *
 * @author giorgos
 */
public class FileNameGenerator {

    /**
     * Generate the file name with date format and return its absolute path
     *
     * @return fileNamePath
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

    /**
     * Check if a file already exists and increment its name.
     * Return it name
     *
     * @param filename          String. The filename we want to check
     * @param extension         String. The extension of the filename
     * @return filenameToSave   String. The new filename if we renamed it.
     */
    public static String fileExistsRename(String filename, String extension){
        // Because of the unit test cases
        // all their results were saved in the same file
        // now we check for the file and if exists
        // we increment the name by 1
        int num = 0;
        String filenameToSave = filename + extension;
        File filePath = new File(filenameToSave);
        while(filePath.exists()) {
            filenameToSave = filename + "-" + (num++) + extension;
            filePath = new File(filenameToSave);
        }

        return filenameToSave;
    }
}
