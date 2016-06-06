package gui;

import tools.*;

import javax.swing.JLayeredPane;
import java.awt.*;

public class XLayers extends JLayeredPane implements XElem {
  private Component[] cs;
  private int curr = 0;
  private LayerLayout l;
  
  public XLayers(){
    cs = new Component[0];
    l = new LayerLayout();
    setLayout(l);
    setOpaque(false);
    setBackground(X.col("trans"));
  }
  
  protected void paintComponent(Graphics g){
    g.setColor(getBackground());
    g.fillRect(0, 0, getWidth(), getHeight());
    super.paintComponent(g);
  }
  
  public Component add(Component c){
    add(c, new Integer(curr));
    curr++;
    return c;
  }
  
  public void add(Component c, Object i){
    cs = $.tai(cs, c);
    super.add(c, i);
  }
  
  public void add(Component c, Object i, int p){
    cs = $.tai(cs, c);
    super.add(c, i, p);
  }
  
  /*public static Dimension gmaxsiz(Dimension[] ds){
    return X.dim($.max(X.mgwid(ds)), $.max(X.mghei(ds)));
  }*/
  
  public void set(Table t){
    l.set(t);
  }
  
  private int[] d = {-1, -1};
  private int[] mn = {-1, -1};
  private int[] mx = {-1, -1};
  private boolean[] exp = {true, true};
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
}