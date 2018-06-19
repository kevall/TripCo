package edu.csu2017fa314.T16.Model;
import static org.junit.Assert.*;

import edu.csu2017fa314.T16.Model.Itinerary;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class TestItinerary {

    private Itinerary itinerary1;
    private Itinerary itinerary2;

    @Before
    public void setUp() throws Exception {
        ArrayList<String[]> i1 = new ArrayList<>(Arrays.asList(new String[]{"id:A","name:A","latitude:10.0","longitude:15.0"},
                new String[]{"id:B","name:B","latitude:10.0","longitude:25.0"},
                new String[]{"id:C","name:C","latitude:20.0","longitude:30.0"},
                new String[]{"id:D","name:D","latitude:30.0","longitude:25.0"},
                new String[]{"id:E","name:E","latitude:30.0","longitude:15.0"},
                new String[]{"id:F","name:F","latitude:20.0","longitude:10.0"}));
        ArrayList<String[]> i2 = new ArrayList<>(Arrays.asList(new String[]{"index:3411","id:KBJC","type:medium_airport","airport name:Rocky Mountain Metropolitan Airport","latitude:39.90879822","longitude:-105.1169968","elevation:5673","municipality:Denver","home_link:null","null"},
        		new String[]{"index:3486","id:KDEN","type:large_airport","airport name:Denver International Airport","latitude:39.861698150635","longitude:-104.672996521","elevation:5431","municipality:Denver","home_link:http://www.flydenver.com/","null"},
        		new String[]{"index:7121","id:0CD4","type:heliport","airport name:Kauffman Heliport","latitude:40.1463012695","longitude:-104.887001038","elevation:5120","municipality:Denver","home_link:null","null"},
        		new String[]{"index:7704","id:11CO","type:heliport","airport name:Channel 7 Heliport","latitude:39.72529983520508","longitude:-104.98400115966797","elevation:5300","municipality:Denver","home_link:null","null"},
        		new String[]{"index:9359","id:2CO4","type:heliport","airport name:Presbyterian/St Luke's Medical Center Heliport","latitude:39.7494010925293","longitude:-104.96900177001953","elevation:5291","municipality:Denver","home_link:null","null"},
        		new String[]{"index:12122","id:57CO","type:heliport","airport name:The Children's Hospital Heliport","latitude:39.733299255371094","longitude:-104.96700286865234","elevation:5436","municipality:Denver","home_link:null","null"},
        		new String[]{"index:13138","id:69CO","type:heliport","airport name:Porter Memorial Hospital Heliport","latitude:39.670799255371094","longitude:-104.97599792480469","elevation:5349","municipality:Denver","home_link:null","null"},
        		new String[]{"index:15680","id:9CO0","type:heliport","airport name:Police Headquarters Heliport","latitude:39.73749923706055","longitude:-104.99199676513672","elevation:5350","municipality:Denver","home_link:null","null"},
        		new String[]{"index:16855","id:CO01","type:heliport","airport name:General Mail Facility Heliport","latitude:39.79050064086914","longitude:-104.9020004272461","elevation:5223","municipality:Denver","home_link:null","null"},
        		new String[]{"index:16858","id:CO04","type:heliport","airport name:St Anthony Hospital Central Heliport","latitude:39.74250030517578","longitude:-105.0469970703125","elevation:5275","municipality:Denver","home_link:null","null"},
        		new String[]{"index:3411","id:KBJC","type:medium_airport","airport name:Rocky Mountain Metropolitan Airport","latitude:39.90879822","longitude:-105.1169968","elevation:5673","municipality:Denver","home_link:null","null"},
        		new String[]{"index:3486","id:KDEN","type:large_airport","airport name:Denver International Airport","latitude:39.861698150635","longitude:-104.672996521","elevation:5431","municipality:Denver","home_link:http://www.flydenver.com/","null"},
        		new String[]{"index:7121","id:0CD4","type:heliport","airport name:Kauffman Heliport","latitude:40.1463012695","longitude:-104.887001038","elevation:5120","municipality:Denver","home_link:null","null"},
        		new String[]{"index:7704","id:11CO","type:heliport","airport name:Channel 7 Heliport","latitude:39.72529983520508","longitude:-104.98400115966797","elevation:5300","municipality:Denver","home_link:null","null"},
        		new String[]{"index:9359","id:2CO4","type:heliport","airport name:Presbyterian/St Luke's Medical Center Heliport","latitude:39.7494010925293","longitude:-104.96900177001953","elevation:5291","municipality:Denver","home_link:null","null"},
        		new String[]{"index:12122","id:57CO","type:heliport","airport name:The Children's Hospital Heliport","latitude:39.733299255371094","longitude:-104.96700286865234","elevation:5436","municipality:Denver","home_link:null","null"},
        		new String[]{"index:13138","id:69CO","type:heliport","airport name:Porter Memorial Hospital Heliport","latitude:39.670799255371094","longitude:-104.97599792480469","elevation:5349","municipality:Denver","home_link:null","null"},
        		new String[]{"index:15680","id:9CO0","type:heliport","airport name:Police Headquarters Heliport","latitude:39.73749923706055","longitude:-104.99199676513672","elevation:5350","municipality:Denver","home_link:null","null"},
        		new String[]{"index:16855","id:CO01","type:heliport","airport name:General Mail Facility Heliport","latitude:39.79050064086914","longitude:-104.9020004272461","elevation:5223","municipality:Denver","home_link:null","null"},
        		new String[]{"index:16858","id:CO04","type:heliport","airport name:St Anthony Hospital Central Heliport","latitude:39.74250030517578","longitude:-105.0469970703125","elevation:5275","municipality:Denver","home_link:null","null"}));
        itinerary1 = new Itinerary(i1, true, 3);
     	itinerary2 = new Itinerary(i2, true, 2); 	
    }

    @Test
    public void testLatitude() {
        assertEquals("10.0",itinerary1.getLatitude(new String[] {"id:B","name:B","latitude:10.0","longitude:25.0"}));
    }

    @Test
    public void testLongitude() {
        assertEquals("25.0",itinerary1.getLongitude(new String[] {"id:B","name:B","latitude:10.0","longitude:25.0"}));

    }

    @Test 
    public void testTable() {
        assertEquals(itinerary2.table[3][3], 0);
        assertEquals(itinerary2.table[1][2], itinerary2.table[2][1]);
    }
   
    @Test
    public void testIndices() {
        assertEquals(4,itinerary2.latitude);
        assertEquals(5,itinerary2.longitude);
    }

    @Test
    public void testSwap() {
    	assertArrayEquals(new int[]{3, 2, 1}, itinerary2.opt2Swap(new int[]{1, 2, 3}, 0, 2));
    }

    @Test
    public void testDistance() {
	assertEquals(72, itinerary2.distance(new int[]{1, 2, 3, 1}));
    }

//    @Test
//    public void testTrip() {
//        //assertEquals(itinerary.getTrip().get(0).startID, "KDEN");
//        //assertEquals(itinerary.getTrip().get(0).startID, itinerary.getTrip().get(itinerary.getTrip().size()-1).endID);
//    }

    @Test
    public void testDMStoD() {
        assertEquals(39.7, Itinerary.DMStoD("39°42'"),0.01);
        assertEquals(39.71, Itinerary.DMStoD("39°42'36\""),0.01);
    }

    @Test
    public void testGreatCircle() {
        assertEquals(0, itinerary1.greatCircle(0.0,0.0,0.0,0.0));
    }

    @Test
    public void testTotal() {
        assertEquals(86, itinerary2.distance);
    }
	
	@Test
	public void testOpt3Swap() {
		assertArrayEquals(new int[]{0,3,4,1,2,5},itinerary1.opt3Swap(new int[]{0,1,2,3,4,5},1,2,3,4));
		assertArrayEquals(new int[]{1,6,7,4,5,2,3,8},itinerary1.opt3Swap(new int[]{1,2,3,4,5,6,7,8},1,2,5,6));
	}

   @Test
    public void testThreeOpt() {
       assertArrayEquals(new int[]{0,1,2,3,4,5},itinerary1.threeOpt(new int[]{0,1,2,3,4,5})); //case 1
       assertArrayEquals(new int[]{0,1,2,3,4,5},itinerary1.threeOpt(new int[]{0,1,2,4,3,5})); //case 2
   }

   @Test
    public void testNearestNeighbor() {
        assertArrayEquals(new int[]{0,1,2,3,4,5,0},itinerary1.nearestNeighbor(0));
   }

   @Test
    public void test2Opt() {
        assertArrayEquals(new int[]{0,1,2,3,4,5},itinerary1.twoOpt(new int[] {0,1,2,3,4,5}));
   }
}
