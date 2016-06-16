import tools.$;

import java.util.*;

public class Test {
  int i = 0;
  
  public static void main(String[] args) {
    new Test().main2();
  }
  
  public void main2(){
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
