/**
 * The basic Ads object class
 */

package main;

public class Ads {
    private String timestamp;
    private Double impressions;
    private Double spend;

    /**
     * Initialize the object Ads 
     * 
     * @param timestamp
     * @param impressions
     * @param spend
     */
    public Ads(String timestamp, Double impressions, Double spend) {
        setTimestamp(timestamp);    
        setImpressions(impressions);    
        setSpend(spend);
    }

//    @Override
//    public String toString() {
//        return "Ad{" 
//        + "timestamp=" + getTimestamp() 
//        + ", impressions=" + getImpressions() 
//        + ", spend=" + getSpend() 
//        + '}';
//    }
    
    /**
     * Return timestamp
     * 
     * @return String
     */
    public String getTimestamp() {
        return timestamp;
    }
    
    /**
     * Return impressions
     * 
     * @return Double
     */
    public Double getImpressions() {
        return impressions;
    }
    
    /**
     * Return spend
     * 
     * @return Double
     */
    public Double getSpend() {
        return spend;
    }
    
    /**
     * Set the timestamp
     * 
     * @param timestamp
     */
    private void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     * Set the impressions
     * 
     * @param impressions
     */
    private void setImpressions(Double impressions) {
        this.impressions = impressions;
    }
    
    /**
     * Set the spend
     * 
     * @param spend
     */
    private void setSpend(Double spend) {
        this.spend = spend;
    }
}
