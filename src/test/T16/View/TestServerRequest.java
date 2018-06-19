
package edu.csu2017fa314.T16.View;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestServerRequest {

  private ServerRequest sr;
  
  @Before
  public void setup() throws Exception {
    sr = new ServerRequest("download", "save a file", true, 2);
  }
  
  @Test
  public void testGetters() {
    assertEquals("download", sr.getRequest());
    assertEquals("save a file", sr.getDescription());
    assertEquals(true, sr.getUnits());
    assertEquals(2, sr.getOp());
  }
}
