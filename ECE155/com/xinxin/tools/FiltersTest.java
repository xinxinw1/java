package com.xinxin.tools;

import static org.junit.Assert.assertEquals;
import static com.xinxin.tools.Filters.*;
import org.junit.Test;

public class FiltersTest {
  @Test
  public void lowpass() {
    LowPass l = new LowPass(0.2);
    double d = 0.0000000000000001;
    double[] inputs = {1, 2, 3, 4, 5};
    double[] outputs = {1, 1.2, 1.56, 2.048, 2.6384};
    for (int i = 0; i < inputs.length; i++){
      assertEquals(outputs[i], l.next(inputs[i]), d);
    }
  }
  
  @Test
  public void lowpass0() {
    LowPass l = new LowPass(0);
    double d = 0.0000000000000001;
    double[] inputs = {1, 2, 3, 4, 5};
    double[] outputs = {1, 1, 1, 1, 1};
    for (int i = 0; i < inputs.length; i++){
      assertEquals(outputs[i], l.next(inputs[i]), d);
    }
  }
  
  @Test
  public void lowpass1() {
    LowPass l = new LowPass(1);
    double d = 0.0000000000000001;
    double[] inputs = {1, 2, 3, 4, 5};
    double[] outputs = {1, 2, 3, 4, 5};
    for (int i = 0; i < inputs.length; i++){
      assertEquals(outputs[i], l.next(inputs[i]), d);
    }
  }
  
  @Test
  public void highpass() {
    HighPass l = new HighPass(0.2);
    double d = 0.0000000000000001;
    double[] inputs = {1, 2, 3, 4, 5};
    double[] outputs = {1, 0.4, 0.28, 0.256, 0.2512};
    for (int i = 0; i < inputs.length; i++){
      assertEquals(outputs[i], l.next(inputs[i]), d);
    }
  }
  
  @Test
  public void highpass0() {
    HighPass l = new HighPass(0);
    double d = 0.0000000000000001;
    double[] inputs = {1, 2, 3, 4, 5};
    double[] outputs = {1, 0, 0, 0, 0};
    for (int i = 0; i < inputs.length; i++){
      assertEquals(outputs[i], l.next(inputs[i]), d);
    }
  }
  
  @Test
  public void highpass1() {
    HighPass l = new HighPass(1);
    double d = 0.0000000000000001;
    double[] inputs = {1, 2, 3, 4, 5};
    double[] outputs = {1, 2, 3, 4, 5};
    for (int i = 0; i < inputs.length; i++){
      assertEquals(outputs[i], l.next(inputs[i]), d);
    }
  }
}
