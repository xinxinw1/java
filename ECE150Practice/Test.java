import tools.$;

import java.util.*;

public class Test {
  public static void main(String[] args){
    ArrayList<Integer> a = new ArrayList<>();
    a.add(3);
    a.add(5);
    for (int i = 0; i < 20; i++){
      a.add(i);
    }
    a.get(0);
    $.prn(a.size());
  }
}

class Parent {
  public Parent(){
    $.prn("creating parent");
  }
  
  public void test(){
    $.prn("Hey!");
  }
}

class Child extends Parent {
  public Child(){
    super();
    $.prn("creating child");
  }
  
  public void test(){
    $.prn("Whoa!");
    super.test();
  }
}
  
  
