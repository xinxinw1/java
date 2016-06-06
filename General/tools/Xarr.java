package tools;

import java.util.ArrayList;
import java.lang.reflect.Array;

public class Xarr extends ArrayList<Object> {
  public Xarr(){}
  
  public Xarr(ArrayList a){
    for (int i = 0; i < a.size(); i++){
      set(i, a.get(i));
    }
  }
  
  public Xarr(Object a){
    for (int i = 0; i < Array.getLength(a); i++){
      set(i, Array.get(a, i));
    }
  }
  
  public Object get(int n){
    if (n < 0 || n >= size())return null;
    return super.get(n);
  }
  
  public Object set(int n, Object x){
    if (n >= size())ext(n);
    return super.set(n, x);
  }
  
  private void ext(int n){
    for (int i = n-(size()-1); i >= 1; i--){
      super.add(null);
    }
  }
}