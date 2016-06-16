import tools.*;
import static tools.$.is;
import static tools.$.in;
import static tools.$.err;
import static tools.$.i;

import java.awt.LayoutManager2;
import java.awt.Container;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;

import java.io.Serializable;

public class AxisLayoutSimp implements LayoutManager2, Serializable {
	private String axis;
  private boolean fill = false;
  private int gap = 0;
  
	public AxisLayoutSimp(){
		this("Y");
	}
  
  public AxisLayoutSimp(String axis){
		this(axis, false);
	}
  
	public AxisLayoutSimp(String axis, boolean fill){
		saxis(axis);
    sfill(fill);
	}
  
	public String axis(){return axis;}
  
	public void saxis(String axis){
    if (!in(axis, "X", "Y"))throw err("saxis", "Invalid axis = $1", axis);
    this.axis = axis;
  }
  
  public boolean fill(){return fill;}
	public void sfill(boolean f){this.fill = f;}
  
	public void addLayoutComponent(String name, Component c){}
	public void addLayoutComponent(Component c, Object cst){}
	public void removeLayoutComponent(Component c){}
  
	public Dimension preferredLayoutSize(Container cnt){
		synchronized (cnt.getTreeLock()){
			return laysiz(cnt, "pref");
		}
	}
  
	public Dimension minimumLayoutSize(Container cnt){
		synchronized (cnt.getTreeLock()){
			return laysiz(cnt, "min");
		}
	}
  
	public void layoutContainer(Container cnt){
		synchronized (cnt.getTreeLock()){
			if (is(axis, "X"))layx(cnt);
      else layy(cnt);
		}
	}
  
	private void layx(Container cnt){
    Component[] cs = viscs(cnt);
		Insets iset = cnt.getInsets();
		int tot = cnt.getSize().height - iset.top - iset.bottom;
		int x = iset.left;
		for (Component c : cs){
			Dimension d = c.getPreferredSize();
			d.height = ghei(c, tot);
      c.setSize(d);
      c.setLocation(x, locy(c, tot) + iset.top);
      x += d.width;
		}
	}
  
  private void layy(Container cnt){
    Component[] cs = viscs(cnt);
		Insets iset = cnt.getInsets();
		int tot = cnt.getSize().width - iset.left - iset.right;
		int y = iset.top;
		for (Component c : cs){
			Dimension d = c.getPreferredSize();
			d.width = gwid(c, tot);
      c.setSize(d);
      c.setLocation(locx(c, tot) + iset.left, y);
      y += d.height;
		}
	}
  
  private int ghei(Component c, int tot){
    return gsiz(c.getMinimumSize().height, tot, c.getMaximumSize().height);
  }
  
  private int gwid(Component c, int tot){
    return gsiz(c.getMinimumSize().width, tot, c.getMaximumSize().width);
  }
  
  private int gsiz(int min, int pref, int max){
    return Math.max(Math.min(pref, max), min);
  }
  
  private Component[] viscs(Container cnt){
    Component[] r = {};
    for (Component c : cnt.getComponents()){
			if (c.isVisible())r = (Component[]) $.tai(r, c);
    }
    return r;
  }
  
  private int locy(Component c, int tot){
		return pos(c, tot, c.getSize().height, c.getAlignmentY());
  }
  
  private int locx(Component c, int tot){
		return pos(c, tot, c.getSize().width, c.getAlignmentX());
  }
  
  private int pos(Component c, int tot, int elm, float ali){
    return $.tint((tot - elm) * ali);
  }
  
	private Dimension laysiz(Container cnt, String tp){
		int w = 0;
		int h = 0;
    Component[] cs = viscs(cnt);

		for (Component c : cs){
			Dimension d = gdim(c, tp);
      
			if (is(axis, "X")){
				w += d.width;
				h = Math.max(h, d.height);
			} else {
				h += d.height;
        w = Math.max(w, d.width);
			}
		}
    
		Insets iset = cnt.getInsets();
		w += iset.left + iset.right;
	  h += iset.top + iset.bottom;

		return new Dimension(w, h);
	}

	private Dimension gdim(Component c, String tp){
		switch (tp){
			case "pref": return c.getPreferredSize();
			case "min":  return c.getMinimumSize();
			default: return new Dimension(0, 0);
		}
	}
  
	public Dimension maximumLayoutSize(Container cnt){
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}
  
	public float getLayoutAlignmentX(Container cnt){return 0.5f;}
	public float getLayoutAlignmentY(Container cnt){return 0.5f;}
	public void invalidateLayout(Container cnt){}
  
	public String toString(){
		return getClass().getName() + "[axis=" + axis + "]";
	}
}
