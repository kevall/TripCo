package edu.csu2017fa314.T16.Model;
import static org.junit.Assert.*;

import edu.csu2017fa314.T16.Model.Leg;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.ArrayList;

public class TestLeg {

    private Leg leg;
    
    @Before
    public void setup() throws Exception {
        ArrayList<String> s = new ArrayList<>();
        s.add("index:3486");s.add("id:KDEN");s.add("type:large_airport");s.add("airport name:Denver International Airport");s.add("latitude:39.9");s.add("longitude:-104.6");s.add("elevation:5431");s.add("municipality:Denver");s.add("home_link:http://www.flydenver.com/");s.add("null");
        ArrayList<String> e = new ArrayList<>();
        e.add("index:3411");e.add("id:KBJC");e.add("type:medium_airport");e.add("airport name:Rocky Mountain Metropolitan Airport");e.add("latitude:39.8");e.add("longitude:-105.1");e.add("elevation:5673");e.add("municipality:Denver");e.add("home_link:null");e.add("null");
        leg = new Leg(s, e, 33);
    }
    
    @Test
    public void testLatitude() {
        assertEquals(39.9, leg.getStartLatitude(), 0.01);
        assertEquals(39.8, leg.getEndLatitude(), 0.01);
    }
    
    @Test
    public void testLongitude() {
        assertEquals(-104.6, leg.getStartLongitude(),0.01);
        assertEquals(-105.1, leg.getEndLongitude(),0.01);
    }
    
    @Test
    public void testDMStoD() {
        assertEquals(39.7, leg.DMStoD("39°42'"), 0.1);
        assertEquals(39.71, leg.DMStoD("39°42'36\""), 0.01);
    }
   
}
