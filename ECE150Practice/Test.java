import tools.$;

import java.util.*;

public class Test {
  public static void main(String[] args) throws NewException {
    Interf p = new Parent() {
      public void test(){
        $.prn("here");
      }
      
      public int whoa(){
        return 3;
      }
    };
    
    p.test();
    
    $.prn(((Parent) p).whoa);
    ((Parent) p).whoa = 66;
    $.prn(((Parent) p).whoa);
    $.prn(((Parent) p).hey);
  }
}

abstract class Parent implements Interf {
  public int whoa = 3;
  static public final int hey = 4;

  public Parent(){
    $.prn("here");
  }
  
  public abstract void test();
}

interface Interf {
  public void test();
  public int whoa();
}
