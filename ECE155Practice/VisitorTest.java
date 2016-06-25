import tools.$;

import java.util.*;

public class VisitorTest {
  public static void main(String[] args) {
    List l = new List(new Item(3), new Item(1), new Item(5));
    Visitor v = new ListVisitor();
    l.accept(v);
  }
}

class List {
  private ArrayList<Item> a;
  
  public List(Item... is){
    a = new ArrayList<>();
    for (Item i : is){
      a.add(i);
    }
  }
  
  public void accept(Visitor v){
    v.visit(this);
    for (Item i : a){
      i.accept(v);
    }
  }
}

class Item {
  private int n;
  
  public Item(int num){
    n = num;
  }
  
  public void accept(Visitor v){
    v.visit(this);
  }
  
  public int getN(){
    return n;
  }
}

interface Visitor {
  public void visit(List a);
  public void visit(Item a);
}

class ListVisitor implements Visitor {
  public void visit(List a){
    $.prn(a);
  }
  
  public void visit(Item a){
    $.prn(a.getN());
  }
}
