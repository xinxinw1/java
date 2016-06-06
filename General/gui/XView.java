package gui;

import tools.*;
import static tools.$.*;

import javax.swing.*;
import java.awt.*;

public class XView extends JPanel implements XElem {
  private XView2 v;
  
  public XView(){
    super();
    setOpaque(false);
    setBackground(X.col("trans"));
    setLayout(new SimpLayout());
    v = new XView2();
    super.addImpl(v, null, -1);
  }
  
  public XView(Component c){
    this();
    add(c);
  }
  
  protected void paintComponent(Graphics g){
    g.setColor(getBackground());
    g.fillRect(0, 0, getWidth(), getHeight());
    super.paintComponent(g);
  }
  
  protected void addImpl(Component c, Object cst, int n){
    v.addImpl(c, cst, n);
  }
  
  public int[] gloc(){return v.gloc();}
  public void sloc(int x, int y){v.sloc(x, y);}
  public void aloc(int x, int y){v.aloc(x, y);}
  public void left(int n){v.left(n);}
  public void rig(int n){v.rig(n);}
  public void up(int n){v.up(n);}
  public void down(int n){v.down(n);}
  
  public void set(Table t){v.set(t);}
  
  private int[] d = {-1, -1};
  private int[] mn = {-1, -1};
  private int[] mx = {-1, -1};
  private boolean[] exp = {true, true};
  private boolean bug = false;
  
  public Dimension getMinimumSize(){
    Dimension min = super.getMinimumSize();
    Dimension prf = getPreferredSize();
    if (bug)$.prn("min: $1", min);
    return X.wdim(X.wexp(X.btwd(min, $.mni(), prf), prf, exp), mn);
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
    return X.wdim(X.wexp(X.btwd(max, prf, $.mxi()), prf, exp), mx);
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