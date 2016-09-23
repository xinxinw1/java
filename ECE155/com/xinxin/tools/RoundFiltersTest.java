import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RoundFiltersTest {
  @Test
  public void evaluatesExpression() {
    RoundFilters calculator = new RoundFilters();
    int sum = calculator.evaluate("1+2+3");
    assertEquals(6, sum);
  }
}
