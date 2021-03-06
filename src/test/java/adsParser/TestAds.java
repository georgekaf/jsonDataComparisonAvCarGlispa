/**
 *
 */
package adsParser;

import main.Ads;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author giorgos
 */
public class TestAds {

    @Test
    public void testClassInitializationGetTimestamp() {
        String timestamp = "";
        Double impression = 0.0;
        Double spend = 0.0;

        Ads ad = new Ads(timestamp, impression, spend);

        String timestamp1 = ad.getTimestamp();
        Double impressions1 = ad.getImpressions();
        Double spend1 = ad.getSpend();

        Assert.assertEquals(timestamp1, "");
    }

    @Test
    public void testClassInitializationGetImpressions() {
        String timestamp = "";
        Double impression = 0.0;
        Double spend = 0.0;

        Ads ad = new Ads(timestamp, impression, spend);

        Double impressions1 = ad.getImpressions();

        Assert.assertEquals(impressions1, impression);
    }

    @Test
    public void testClassInitializationGetSpend() {
        String timestamp = "";
        Double impression = 0.0;
        Double spend = 0.0;

        Ads ad = new Ads(timestamp, impression, spend);

        Double spend1 = ad.getSpend();

        Assert.assertEquals(spend1, spend);
    }


}

