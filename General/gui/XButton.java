package gui;

import tools.*;

import javax.swing.JButton;
import javax.swing.Icon;
import java.awt.*;

public class XButton extends JButton implements XElem {
  private Color hover = null;
  private Color press = null;
  
  public XButton(){init();}
  public XButton(String txt){super(txt); init();}
  public XButton(Icon i){super(i); init();}
  public XButton(String txt, Icon i){super(txt, i); init();}
  
  private void init(){
    setOpaque(false);
  }
  
  protected void paintComponent(Graphics g){
    if (getModel().isPressed()){
      if (press == null)g.setColor(X.dar(getBackground()));
      else g.setColor(press);
    } else if (getModel().isRollover()){
      if (hover == null)g.setColor(X.lig(getBackground()));
      else g.setColor(hover);
    } else {
      g.setColor(getBackground());
    }
    g.fillRect(0, 0, getWidth(), getHeight());
    super.paintComponent(g);
  }
  
  public void set(Table t){
    if (t.has("hover"))hover = X.col(t.get("hover"));
    if (t.has("press"))press = X.col(t.get("press"));
  }
  
  // getMaximumSize() uses X.maxdim()
  
  private int[] d = {-1, -1};
  private int[] mn = {-1, -1};
  private int[] mx = {-1, -1};
  private boolean[] exp = {false, false};
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
    Dimension max = X.maxdim();
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