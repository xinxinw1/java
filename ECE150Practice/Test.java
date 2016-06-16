import tools.$;

import java.util.*;

public class Test {
  public static void main(String[] args) throws NewException {
    Interf a = new Parent(3);
    Interf b = new Child(4);
    
    a.test();
    b.test();
  }
}

class Parent extends Interf {
  public Parent(int a){
    $.prn("here" + a);
  }
  
  public void test(){
    $.prn("Parent");
  }
}

class Child extends Parent {
  public Child(int a){
    super(a);
  }
  
  public void test(){
    $.prn("Child");
  }
}

abstract class Interf {
  public abstract void test();
}
