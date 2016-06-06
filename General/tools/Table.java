package tools;

import java.util.Map;
import java.util.HashMap;

public class Table {
  private Map<Object, Object> m;
  
  public Table(Object... a){
    m = new HashMap<Object, Object>();
    int i;
    for (i = 1; i < a.length; i+=2){
      m.put(a[i-1], a[i]);
    }
    if (i <= a.length)m.put(a[i], null);
  }
  
  public Object get(Object k){return m.get(k);}
  public Object set(Object k, Object v){m.put(k, v); return v;}
  public Object put(Object k, Object v){return set(k, v);}
  public int len(){return m.size();}
  public boolean has(Object k){return m.containsKey(k);}
  public Table clr(){m.clear(); return this;}
  
  public Object[] keys(){return m.keySet().toArray();}
  
  public Object[] vals(){
    Object[] ks = keys();
    Object[] vs = new Object[ks.length];
    for (int i = 0; i < ks.length; i++){
      vs[i] = get(ks[i]);
    }
    return vs;
  }
}