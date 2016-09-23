import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VectorTest {
  // p: PointF(6.536688, 6.4223456)
  // l1: <PointF(6.536688, 3.884338), PointF(6.536688, 6.4223456)>
  // perp1: PointF(3.99868, 6.4223456)
  // l2: <PointF(6.536688, 6.4223456), PointF(8.582247, 6.4223456)>
  // perp2: PointF(6.536688, 8.467905)
  // Points: start: (6.536688, 6.4223456), end1: (6.536688, 3.884338), end2: (6.536688, 8.467905)
  // Vectors: (0, -2.5380076), (0, 2.0455594)
  public static final double TOLERANCE = 0.00000001;
  public static final float TOLERANCEF = 0.00000001f;
  @Test
  public void testDouble() {
    assertEquals(0, Vector.angleBetween(1, 1, 1, 1), TOLERANCE);
    assertEquals(0, Vector.angleBetween(1, 1, 2, 2), TOLERANCE);
    
    assertEquals(Math.PI, Vector.angleBetween(1, 1, -1, -1), TOLERANCE);
    assertEquals(Math.PI, Vector.angleBetween(0, 1, 0, -1), TOLERANCE);
    assertEquals(Math.PI, Vector.angleBetween(0, -1, 0, 1), TOLERANCE);
    assertEquals(Math.PI, Vector.angleBetween(1, 0, -1, 0), TOLERANCE);
    assertEquals(Math.PI, Vector.angleBetween(-1, 0, 1, 0), TOLERANCE);
    
    assertEquals(Math.PI/2, Vector.angleBetween(1, 0, 0, 1), TOLERANCE);
    assertEquals(Math.PI/2, Vector.angleBetween(-1, 0, 0, 1), TOLERANCE);
    assertEquals(Math.PI/2, Vector.angleBetween(1, 0, 0, -1), TOLERANCE);
    assertEquals(Math.PI/2, Vector.angleBetween(-1, 0, 0, -1), TOLERANCE);
    assertEquals(Math.PI/2, Vector.angleBetween(0, 1, 1, 0), TOLERANCE);
    assertEquals(Math.PI/2, Vector.angleBetween(0, -1, 1, 0), TOLERANCE);
    assertEquals(Math.PI/2, Vector.angleBetween(0, 1, -1, 0), TOLERANCE);
    assertEquals(Math.PI/2, Vector.angleBetween(0, -1, -1, 0), TOLERANCE);
    assertEquals(Math.PI/2, Vector.angleBetween(0, -1, 1, 0), TOLERANCE);
    
    assertEquals(Math.PI, Vector.angleBetween(0, -2.5380076, 0, 2.0455594), TOLERANCE);
  }
  
  @Test
  public void testFloat() {
    /*assertEquals(0, Vector.angleBetweenf(1, 1, 1, 1), TOLERANCEF);
    assertEquals(0, Vector.angleBetweenf(1, 1, 2, 2), TOLERANCEF);
    
    assertEquals((float) Math.PI, Vector.angleBetweenf(1, 1, -1, -1), TOLERANCEF);
    assertEquals((float) Math.PI, Vector.angleBetweenf(0, 1, 0, -1), TOLERANCEF);
    assertEquals((float) Math.PI, Vector.angleBetweenf(0, -1, 0, 1), TOLERANCEF);
    assertEquals((float) Math.PI, Vector.angleBetweenf(1, 0, -1, 0), TOLERANCEF);
    assertEquals((float) Math.PI, Vector.angleBetweenf(-1, 0, 1, 0), TOLERANCEF);
    
    assertEquals((float) Math.PI/2, Vector.angleBetweenf(1, 0, 0, 1), TOLERANCEF);
    assertEquals((float) Math.PI/2, Vector.angleBetweenf(-1, 0, 0, 1), TOLERANCEF);
    assertEquals((float) Math.PI/2, Vector.angleBetweenf(1, 0, 0, -1), TOLERANCEF);
    assertEquals((float) Math.PI/2, Vector.angleBetweenf(-1, 0, 0, -1), TOLERANCEF);
    assertEquals((float) Math.PI/2, Vector.angleBetweenf(0, 1, 1, 0), TOLERANCEF);
    assertEquals((float) Math.PI/2, Vector.angleBetweenf(0, -1, 1, 0), TOLERANCEF);
    assertEquals((float) Math.PI/2, Vector.angleBetweenf(0, 1, -1, 0), TOLERANCEF);
    assertEquals((float) Math.PI/2, Vector.angleBetweenf(0, -1, -1, 0), TOLERANCEF);
    assertEquals((float) Math.PI/2, Vector.angleBetweenf(0, -1, 1, 0), TOLERANCEF);*/
    
    assertEquals((float) Math.PI, Vector.angleBetweenf(0, -2.5380076f, 0, 2.0455594f), TOLERANCEF);
  }
}
