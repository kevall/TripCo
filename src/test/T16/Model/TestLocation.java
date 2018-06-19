package edu.csu2017fa314.T16.Model;
import static org.junit.Assert.*;

import edu.csu2017fa314.T16.Model.Location;
import org.junit.Before;
import org.junit.Test;

public class TestLocation {

  private Location loc;
  
  @Before
  public void setup() throws Exception {
    loc = new Location("alpha", "Here");
  }

  @Test
  public void testName() {
    assertEquals("Here",loc.getName());
  }

  @Test
  public void testCode() {
    assertEquals("alpha", loc.getCode());
  }
}
