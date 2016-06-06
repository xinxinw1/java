package gui;

import tools.*;

import javax.swing.JTextField;
import java.awt.Dimension;

public class XInput extends JTextField implements XElem {
  public XInput(){
    super();
    setBorder(null);
    X.lin(X.col(122, 138, 153), X.pad(1, this));
  }
  
  public XInput(String txt){
    this();
    setText(txt);
  }
  
  private int[] d = {-1, -1};
  private int[] mn = {-1, -1};
  private int[] mx = {-1, -1};
  private boolean[] exp = {true, false};
  private boolean bug = false;
  
  public Dimension getMinimumSize(){
    Dimension min = super.getMinimumSize();
    Dimension prf = getPreferredSize();
    if (bug)$.prn("min: $1", min);
    return X.wdim(X.wexp(min, prf, exp), mn);
  }
  
  public Dimension getPreferredSize(){
    Dimension prf = super.getPreferredSize();
    if (bug)$.prn("prf: $1", prf);
    return X.wdim(prf, d);
  }
  
  public Dimension getMaximumSize(){
    Dimension max = super.getMaximumSize();
    Dimension prf = getPreferredSize();
    if (bug)$.prn("max: $1", max);
    return X.wdim(X.wexp(max, prf, exp), mx);
  }
  
  public void wid(int n){this.d[0] = n;}
  public void hei(int n){this.d[1] = n;}
  public void minw(int n){this.mn[0] = n;}
  public void minh(int n){this.mn[1] = n;}
  public void maxw(int n){this.mx[0] = n;}
  public void maxh(int n){this.mx[1] = n;}
  public void expx(boolean b){this.exp[0] = b;}
  public void expy(boolean b){this.exp[1] = b;}
  public void bug(boolean b){this.bug = b;}
  
  public void set(Table t){}
}