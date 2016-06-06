package gui;

import tools.*;
import static tools.$.*;
import static gui.X.*;

import java.awt.*;
import javax.swing.*;

import java.io.Serializable;

public class SimpLayout implements LayoutManager2, Serializable {
  public void addLayoutComponent(String name, Component c){}
	public void addLayoutComponent(Component c, Object cst){}
	public void removeLayoutComponent(Component c){}
  
  public Dimension preferredLayoutSize(Container cnt){
		synchronized (cnt.getTreeLock()){
			return laysiz(cnt, "prf");
		}
	}
  
	public Dimension minimumLayoutSize(Container cnt){
		synchronized (cnt.getTreeLock()){
      return laysiz(cnt, "min");
		}
	}
  
  public Dimension maximumLayoutSize(Container cnt){
		synchronized (cnt.getTreeLock()){
      return laysiz(cnt, "max");
		}
	}
  
  public void layoutContainer(Container cnt){
    synchronized (cnt.getTreeLock()){
			Component[] cs = vis(cnt);
      if (len(cs) == 0)return;
      Component c = cs[0];
      c.setSize(btwd(dim(wid(cnt), hei(cnt)), gmin(c), gmax(c)));
      Insets iset = cnt.getInsets();
      c.setLocation(iset.left, iset.top);
    }
	}
  
  private Dimension laysiz(Container cnt, String tp){
    Component[] cs = vis(cnt);
    if (len(cs) == 0)return dim(0, 0);
    Dimension d;
    switch (tp){
      case "prf": d = gprf(cs[0]); break;
      case "min": d = gmin(cs[0]); break;
      case "max": d = gmax(cs[0]); break;
      default: throw err("laysiz", "Invalid tp = $1", tp);
    }
    return wins(d, cnt.getInsets());
  }
  
	public float getLayoutAlignmentX(Container cnt){return 0.0f;}
	public float getLayoutAlignmentY(Container cnt){return 0.0f;}
	public void invalidateLayout(Container cnt){}
  
	public String toString(){
		return getClass().getName() + "[]";
	}
}
