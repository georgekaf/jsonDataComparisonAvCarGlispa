package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import numericFunctions.PercentOf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class that accepts the filepaths, calculates differences of json objects
 * or decides if there any issues with the files provided.
 *
 * @author giorgos
 */
public class JsonParser {
    private String arguments; // The main's args will be assigned to it, for later use
    public Float percentLimit = (float) 5; // The percent limit that will trigger a result output

    /**
     * The main input method. It will accept multiple strings (filepaths) but more than two wll be ignored.
     *
     * @param args          Accept multiple filepaths arguments, but more than two will be ignored.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            //check if the arguments are completely empty
            if (args.length > 0) {

                // The arguments should be evaluated and eventually loaded in the filestreams ArrayList
                List<JsonReader> filestreams = new ArrayList<JsonReader>();

                for (int i=0; i<2; i++) {
                    if (args.length >= i+1) {

                        // Ignore empty strings
                        if (new String (args[i]) == "") {
                            continue;
                        }

                        // Check if the files actually exist
                        File file = new File(args[i]);
                        if (file.exists() && file.isFile()){
                            System.out.println("file "+args[i]+" exists");

                            // Try to create a filestream to the actual file
                            // an load the object into the arraylist
                            FileStreamer parser = new FileStreamer();
                            filestreams = parser.addFileStream(filestreams, args[i]);
                        }
                    }    
                }

                // If filestreams have the wrong size it won't proceed
                if (filestreams.size() == 2) {

                    // Create the json parsing object
                    JsonParser jsonParser = new JsonParser();

                    // Save the arguments and print them later
                    jsonParser.setArgs(args);
                    
                    // Iterate through the filestreams and calculate percentage differences
                    // If the whole process goes as expected use the boolean result to print the
                    // appropriate result message
                    Boolean success = jsonParser.iterateJsonFiles(filestreams.get(0), filestreams.get(1));
                    
                    if (success) {
                        System.out.println("Please review results");
                    } else {
                        System.out.println("Something went wrong. Please see stacktrace");
                    }
                    
                } else {
                    // Check the result message and print an error
                    // so that we know what went wrong
                    switch (filestreams.size()) {
                        case 1:
                            System.out.println("Only one file was found");
                            break;
                        case 0:
                            System.out.println("Missing both files");
                            break;
                    }
                }
            } else {
                // No arguments at all
                System.out.println("No arguments provided");
            }
        } catch (Exception e) {
            // Exceptions should not happen, if they do
            // print a stack trace and terminate execution with an exception
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Iterate the contents provided from files for two companies and check for differences in the predetermined json
     * attributes (they will be mapped to object fields).
     *
     * @param companyA          JsonReader object of company A
     * @param companyB          JsonReader object of company B
     * @return boolean          default true
     * @throws IOException
     */
    public boolean iterateJsonFiles(JsonReader companyA, JsonReader companyB) throws IOException {
        try {
            // initialize the json readers
            companyA.beginArray();
            companyB.beginArray();

            // Iterate the in memory file streams
            // calculate for differences
            iterateJsonFileStreams(companyA, companyB);

            // End
            companyA.endArray();
            companyB.endArray();

            // Unload from memory
            companyA.close();
            companyB.close();

        } catch (Exception e){
            // If something goes wrong print a stack trace
            // Do not terminate, just return false;
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    /**
     * Map the json records to objects and calculate the difference of the predetermined fields.
     *
     * @param companyA
     * @param companyB
     * @throws IOException
     */
    public void iterateJsonFileStreams(JsonReader companyA,  JsonReader companyB) throws IOException {
        Boolean continueReading = true; // A flag that can be used to break the main loop
        long count = 0; // counter // A loop counter

        Gson gson = new GsonBuilder().create(); // Gson will be used to map json strings to an object class
        FileWriter discrepancyWriter = new FileWriter(); // Initialize the filewriter class (write results to a file)

        // start by creating a flat json array
        discrepancyWriter.fileAppend("[");

        // The company filestreams should be in sync so check if both have records
        while (companyA.hasNext() && companyB.hasNext() && continueReading) {

            // Read data into object models
            Ads adsA = gson.fromJson(companyA, Ads.class);
            Ads adsB = gson.fromJson(companyB, Ads.class);

            // Check if timestamps are equal to each other or else notify the user and break the loop.
            // The user should know about this and take proper action
            if (!new String(adsA.getTimestamp()).equals(adsB.getTimestamp())) {
                System.out.println(
                    "Timestamp Order Mismatched at CompanyA record: "+ adsA.getTimestamp() +
                    " and CompanyB " + adsB.getTimestamp()
                );
                break;
            }

            // If we can't get the timestamps at all do not proceed
            if (adsA.getTimestamp() != null && adsB.getTimestamp() !=null) {
                // Start printing results

                System.out.println("Checking date: " + adsA.getTimestamp());

                // Calculate percentage difference of Impressions
                System.out.println("Check Impressions:");
                Double impressionDiscr = PercentOf.TwoNumbers(
                        adsA.getImpressions(), 
                        adsB.getImpressions()
                    );
               
                //Calculate percentage difference of Spend 
                System.out.println("Check Spend:");
                Double spendDiscr = PercentOf.TwoNumbers(
                        adsA.getSpend(), 
                        adsB.getSpend()
                    );
                
                // Compare any of the predetermined fields against the percent limit
                if (impressionDiscr > this.percentLimit || spendDiscr > this.percentLimit) {
                    String commaStr = "";
                    if (count > 0 ){
                        commaStr = ",";
                    }
                   
                    discrepancyWriter.fileAppend(commaStr +'"'+adsA.getTimestamp()+'"');
                    count++;
                }

                // Print a line separator
                String hr = "";
                for (int ic=0; ic<85; ic++) {
                    hr  +=  "=";
                }
                System.out.println(hr+"\n");
            }
        }

        // Close the flat json array
        discrepancyWriter.fileAppend("]");

        // Print out any informational messages
        System.out.println("Comparison of files "+ this.arguments +" completed ");
        System.out.println("Results are in " + discrepancyWriter.getFilePathName());
    }

    /**
     * Set value for private field arguments from args.
     * 
     * @param args
     */
    private void setArgs(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++){
            sb.append(args[i]).append(",");
        }
         
        this.arguments = sb.toString().trim();
    }
}
