package gui;

import tools.*;

public interface XElem {
  public void wid(int n);
  public void hei(int n);
  public void minw(int n);
  public void minh(int n);
  public void maxw(int n);
  public void maxh(int n);
  public void expx(boolean b);
  public void expy(boolean b);
  public void bug(boolean b);
  public void set(Table t);
}

/*
Default setup:

private int[] d = {-1, -1};
private int[] mn = {-1, -1};
private int[] mx = {-1, -1};
private boolean[] exp = {false, false};

public Dimension getMinimumSize(){
  return X.wdim(X.wexp(super.getMinimumSize(), getPreferredSize(), exp), mn);
}

public Dimension getPreferredSize(){
  return X.wdim(super.getPreferredSize(), d);
}

public Dimension getMaximumSize(){
  return X.wdim(X.wexp(super.getMaximumSize(), getPreferredSize(), exp), mx);
}

public void wid(int w){this.d[0] = w;}
public void hei(int h){this.d[1] = h;}
public void minw(int w){this.mn[0] = w;}
public void minh(int h){this.mn[1] = h;}
public void maxw(int w){this.mx[0] = w;}
public void maxh(int h){this.mx[1] = h;}
public void exp(boolean[] b){this.exp = b;}

For debugging:

public Dimension getMinimumSize(){
  $.prn("min: $1", super.getMinimumSize());
  return X.wdim(X.wexp(super.getMinimumSize(), getPreferredSize(), exp), mn);
}

public Dimension getPreferredSize(){
  $.prn("prf: $1", super.getPreferredSize());
  return X.wdim(super.getPreferredSize(), d);
}

public Dimension getMaximumSize(){
  $.prn("max: $1", super.getMaximumSize());
  return X.wdim(X.wexp(super.getMaximumSize(), getPreferredSize(), exp), mx);
}

*/