package gui;

import tools.*;
import static tools.$.*;

import javax.swing.*;
import java.awt.*;

public class XView2 extends JPanel implements XElem {
  private Component c;
  private View2Layout l;
  
  public XView2(){
    super();
    setOpaque(false);
    setBackground(X.col("trans"));
    l = new View2Layout();
    setLayout(l);
  }
  
  public XView2(Component c){
    this();
    add(c);
  }
  
  protected void paintComponent(Graphics g){
    g.setColor(getBackground());
    g.fillRect(0, 0, getWidth(), getHeight());
    super.paintComponent(g);
  }
  
  protected void addImpl(Component c, Object cst, int n){
    if (!nulp(c))remove(c);
    this.c = c;
    super.addImpl(c, cst, n);
  }
  
  public void set(Table t){l.set(t); revalidate();}
  
  public int[] gloc(){return l.gloc();}
  
  public void sloc(int x, int y){
    l.sloc(x, y);
    revalidate();
  }
  
  public void aloc(int x, int y){
    int[] l = gloc();
    sloc(l[0]+x, l[1]+y);
  }
  
  public void left(int n){aloc(n, 0);}
  public void rig(int n){aloc(-n, 0);}
  public void up(int n){aloc(0, n);}
  public void down(int n){aloc(0, -n);}
  
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