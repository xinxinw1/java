import tools.$;

import java.util.*;

public class Test {
  static int i = 0;
  
  public static void main(String[] args) {
    Interf a = new Interf(){
      public void test(){
        i++;
      }
    };
    
    $.prn(i);
    a.test();
    $.prn(i);
  }
}

interface Interf {
  public void test();
}
