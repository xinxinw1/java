import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.*;

public class UnitTestExampleTest {
  @Before
  public void setUp(){
    System.out.println("here");
  }
  
  @After
  public void takeDown(){
    System.out.println("whoa");
  }

  @Test
  public void aTest(){
    System.out.println("ayy1");
    assertEquals(UnitTestExample.methodUnderTest(0, 0), Double.NEGATIVE_INFINITY, 0.001);
  }
  
  @Test
  public void bTest(){
    System.out.println("ayy2");
    assertEquals(UnitTestExample.methodUnderTest(0, 0), Double.NEGATIVE_INFINITY, 0.001);
  }
  
  @Test(expected=IndexOutOfBoundsException.class)
  public void cTest(){
    System.out.println("ayy3");
    new java.util.ArrayList<Object>().get(0);
  }
}
