
package edu.csu2017fa314.T16.View;
import static org.junit.Assert.*;

import edu.csu2017fa314.T16.Model.Location;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class TestSearchResponse {

  private SearchResponse sr;
  
  @Before
  public void setup() throws Exception {
    Location l1 = new Location("alpha", "Here");
    Location l2 = new Location("beta", "There");
    Location l3 = new Location("gamma", "Everywhere");
    ArrayList<Location> locs = new ArrayList<>();
    locs.add(l1); locs.add(l2); locs.add(l3);
    
    sr = new SearchResponse(locs);
  }
}
