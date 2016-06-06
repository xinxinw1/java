package gui;

import tools.*;
import static tools.$.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class XFrame extends JFrame implements XElem {
  private boolean isDisposed = false;
  
  public XFrame(String title){
    super(title);
    setOnClose("dispose");
    setResizable(false);
    setLocationByPlatform(true);
  }
  
  public void set(Table t){
    if (t.has("closeact"))setOnClose(s(t.get("closeact")));
  }
  
  public void setDefaultButton(JButton b){
    getRootPane().setDefaultButton(b);
  }
  
  public void redisplay(){
    if (!isDisposed){
      //repaint();
      pack();
      center();
    }
  }
  
  public void center(){
    X.cen(this);
  }
  
  public void setOnClose(String o){
    switch (o){
      case "exit": defclose(JFrame.EXIT_ON_CLOSE); break;
      case "dispose": defclose(WindowConstants.DISPOSE_ON_CLOSE); break;
      case "hide": defclose(WindowConstants.HIDE_ON_CLOSE); break;
      case "none": defclose(WindowConstants.DO_NOTHING_ON_CLOSE); break;
      default: setOnClose("dispose"); break;
    }
  }
  
  private void defclose(int o){
    setDefaultCloseOperation(o);
  }
  
  public void close(){
    WindowEvent evt = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
    processWindowEvent(evt);
  }
  
  public void dispose(){
    super.dispose();
    isDisposed = true;
  }
  
  public void pack(){
    setMinimumSize(getMinimumSize());
    super.pack();
  }
  
  private Component last = new JComponent(){};
  public Component add(Component c){
    last = c;
    return super.add(c);
  }
  
  // http://www.java-forums.org/awt-swing/21828-setmaximumsize-jframe-problem.html
  public void paint(Graphics g){
    Dimension d = getSize();
    Dimension m = getMaximumSize();
    if (d.width > m.width || d.height > m.height){
      d.width = $.min(d.width, m.width);
      d.height = $.min(d.height, m.height);
      setSize(d);
    }
    super.paint(g);
  }
  
  private int[] d = {-1, -1};
  private int[] mn = {-1, -1};
  private int[] mx = {-1, -1};
  private boolean[] exp = {true, true};
  private boolean bug = false;
  
  public Dimension getMinimumSize(){
    Dimension min = last.getMinimumSize();
    Dimension prf = getPreferredSize();
    if (bug)$.prn("min: $1", min);
    return X.wins(X.wdim(X.wexp(min, prf, exp), mn), getInsets());
  }
  
  public Dimension getPreferredSize(){
    Dimension prf = last.getPreferredSize();
    if (bug)$.prn("prf: $1", prf);
    return X.wins(X.wdim(prf, d), getInsets());
  }
  
  public Dimension getMaximumSize(){
    Dimension max = last.getMaximumSize();
    Dimension prf = getPreferredSize();
    if (bug)$.prn("max: $1", max);
    return X.wins(X.wdim(X.wexp(max, prf, exp), mx), getInsets());
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