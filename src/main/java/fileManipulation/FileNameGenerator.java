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
     *
     * @param filename
     * @param extension
     * @return
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
