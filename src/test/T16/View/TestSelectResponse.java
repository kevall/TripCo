
package edu.csu2017fa314.T16.View;
import static org.junit.Assert.*;

import edu.csu2017fa314.T16.Model.Leg;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class TestSelectResponse {

  private SelectResponse sr;
  
  @Before
  public void setup() throws Exception {
    ArrayList<String> s = new ArrayList<>();
    s.add("index:3486");s.add("id:KDEN");s.add("type:large_airport");s.add("airport name:Denver International Airport");s.add("latitude:39.9");s.add("longitude:-104.6");s.add("elevation:5431");s.add("municipality:Denver");s.add("home_link:http://www.flydenver.com/");s.add("null");
    ArrayList<String> e = new ArrayList<>();
    e.add("index:3411");e.add("id:KBJC");e.add("type:medium_airport");e.add("airport name:Rocky Mountain Metropolitan Airport");e.add("latitude:39.8");e.add("longitude:-105.1");e.add("elevation:5673");e.add("municipality:Denver");e.add("home_link:null");e.add("null");
    ArrayList<String> s2 = new ArrayList<>();
    s2.add("id:B");s2.add("airport name:B");s2.add("latitude:10.0");s2.add("longitude:25.0");
    ArrayList<String> e2 = new ArrayList<>();
    e2.add("id:C");e2.add("airport name:C");e2.add("latitude:20.0");e2.add("longitude:30.0");
    ArrayList<String> s3 = new ArrayList<>();
    s3.add("id:D");s3.add("airport name:D");s3.add("latitude:30.0");s3.add("longitude:25.0");
    ArrayList<String> e3 = new ArrayList<>();
    e3.add("id:E");e3.add("airport name:E");e3.add("latitude:30.0");e3.add("longitude:15.0");
    Leg l1 = new Leg(s,e,30);
    Leg l2 = new Leg(s2,e2,20);
    Leg l3 = new Leg(s3,e3,10);
    ArrayList<Leg> legs = new ArrayList<>();
    legs.add(l1); legs.add(l2); legs.add(l3);
    
    sr = new SelectResponse(legs);
  }
}
