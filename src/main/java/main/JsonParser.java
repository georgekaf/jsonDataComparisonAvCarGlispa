/**
 * 
 */
package main;

    
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

/**
 * @author giorgos
 *
 */
public class JsonParser {
    private String arguments;
    public Float percentLimit = (float) 5;

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            if (args.length > 0) {
                List<JsonReader> filestreams = new ArrayList<JsonReader>();
                for (int i=0; i<2; i++) {
                    if (args.length >= i+1) {
                        
                        if (new String (args[i]) == "") {
                            continue;
                        }
                            
                        File file = new File(args[i]);
                        if (file.exists() && file.isFile()){
                            System.out.println("file "+args[i]+" exists");
                                
                              FileStreamer parser = new FileStreamer();
                              filestreams = parser.addFileStream(filestreams, args[i]);
                        }
                    }    
                }
                
                if (filestreams.size() == 2) {
                    JsonParser jsonParser = new JsonParser();
                    //Save the arguments and print them later
                    jsonParser.setArgs(args);
                    
                    //Iterate through the filestreams and calculate percentage differences
                    Boolean success = jsonParser.iterateJsonFiles(filestreams.get(0), filestreams.get(1));    
                    
                    if (success) {
                        System.out.println("Please review results");
                    } else {
                        System.out.println("Something went wrong. Please see stacktrace");
                    }
                    
                } else {
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
                System.out.println("No arguments provided");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * 
     * @param companyA
     * @param companyB
     * @return 
     * @throws IOException
     */
    public boolean iterateJsonFiles(JsonReader companyA, JsonReader companyB) throws IOException {
        try {
            companyA.beginArray();
            companyB.beginArray();
            
            iterateJsonFileStreams(companyA, companyB);
            
            companyA.endArray();
            companyB.endArray();
            
            companyA.close();
            companyB.close();    
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param companyA
     * @param companyB
     * @throws IOException
     */
    public void iterateJsonFileStreams(JsonReader companyA,  JsonReader companyB) throws IOException {
        Boolean continueReading = true;
        Gson gson = new GsonBuilder().create();
        FileWriter discrepancyWriter = new FileWriter();
        
        discrepancyWriter.fileAppend("[");
        
        long count = 0;
        while (companyA.hasNext() && companyB.hasNext() && continueReading) {
            // Read data into object model
            Ads adsA = gson.fromJson(companyA, Ads.class);
            Ads adsB = gson.fromJson(companyB, Ads.class);
            
            if (!new String(adsA.getTimestamp()).equals(adsB.getTimestamp())) {
                System.out.println(
                    "Timestamp Order Mismatched at CompanyA record: "+ adsA.getTimestamp() +
                    " and CompanyB " + adsB.getTimestamp()
                );
                break;
            }
            
            if (adsA.getTimestamp() != null && adsB.getTimestamp() !=null) {
//                System.out.println("Stream A: " + adsA + "\n" + "Stream B: " + adsB);
                System.out.println("Checking date: " + adsA.getTimestamp());
                //Calculate percentage difference of Impressions
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
                
              
                if (impressionDiscr > this.percentLimit || spendDiscr > this.percentLimit) {
                    String commaStr = "";
                    if (count > 0 ){
                        commaStr = ",";
                    }
                   
                    discrepancyWriter.fileAppend(commaStr +'"'+adsA.getTimestamp()+'"');
                    count++;
                }
                
                String hr = "";
                for (int ic=0; ic<85; ic++) {
                    hr  +=  "=";
                }
                System.out.println(hr+"\n");
                
            }
        }
    
        discrepancyWriter.fileAppend("]");
        
        System.out.println("Comparison of files "+ this.arguments +" completed ");
        System.out.println("Results are in " + discrepancyWriter.getFilePathName());
    }

    /**
     * Set value for private field args.
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
