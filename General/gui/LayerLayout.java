package gui;

import tools.*;
import static tools.$.*;
import static gui.X.*;

import java.awt.LayoutManager2;
import java.awt.Container;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;

import java.io.Serializable;

public class LayerLayout implements LayoutManager2, Serializable {
	public LayerLayout(){
		this(o());
	}
  
	public LayerLayout(Table t){
    set(t);
	}
  
	public void set(Table t){
    
  }
  
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
			lay(cnt);
		}
	}
  
  public void lay(Container cnt){
    Component[] cs = vis(cnt);
    int w = wid(cnt);
    int h = hei(cnt);
    Insets i = cnt.getInsets();
    int x = i.left;
    int y = i.top;
    for (Component c : cs){
      loc(x, y, c);
      siz(btw(w, gminw(c), gmaxw(c)), btw(h, gminh(c), gmaxh(c)), c);
    }
  }
  
	private Dimension laysiz(Container cnt, String tp){
    Component[] cs = vis(cnt);
    Dimension[] ds;
    switch (tp){
      case "prf": ds = mgprf(cs); break;
      case "min": ds = mgmin(cs); break;
      case "max": ds = mgmax(cs); break;
      default: throw err("laysiz", "Invalid tp = $1", tp);
    }
    int w = max(mgwid(ds));
    int h = max(mghei(ds));
    return wins(dim(w, h), cnt.getInsets());
	}
  
	public float getLayoutAlignmentX(Container cnt){return 0.5f;}
	public float getLayoutAlignmentY(Container cnt){return 0.5f;}
	public void invalidateLayout(Container cnt){}
  
	public String toString(){
		return getClass().getName() + "[]";
	}
}
