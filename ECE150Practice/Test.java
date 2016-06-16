import tools.$;

import java.util.*;

public class Test {
  public static void main(String[] args) throws NewException {
    Interf a = new Parent();
    Interf b = new Child();
    
    a.test();
    b.test();
  }
}

class Parent implements Interf {
  public Parent(){
    $.prn("here");
  }
  
  public void test(){
    $.prn("Parent");
  }
}

class Child extends Parent {
  public void test(){
    $.prn("Child");
  }
}

interface Interf {
  public void test();
}
