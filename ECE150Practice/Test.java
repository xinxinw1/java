import tools.$;

public class Test {
  public static void main(String[] args){
    new Child().test();
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
  
  
