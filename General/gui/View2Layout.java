package gui;

import tools.*;
import static tools.$.*;
import static gui.X.*;

import java.awt.*;
import javax.swing.*;

import java.io.Serializable;

public class View2Layout implements LayoutManager2, Serializable {
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
  
  public void set(Table t){
    if (t.has("sloc"))sloc(t.get("sloc"));
    if (t.has("slocx"))slocx(i(t.get("slocx")));
    if (t.has("slocy"))slocy(i(t.get("slocy")));
    // extra on side
    if (t.has("ext"))ext(t.get("ext"));
    if (t.has("extx"))extx(t.get("extx"));
    if (t.has("exty"))exty(t.get("exty"));
    if (t.has("extl"))ext(0, t.get("extl"));
    if (t.has("extt"))ext(1, t.get("extt"));
    if (t.has("extr"))ext(2, t.get("extr"));
    if (t.has("extb"))ext(3, t.get("extb"));
  }
  
  public void ext(Object a){
    if (arrp(a)){
      if (len(a) == 4){
        ext(0, ref(a, 0));
        ext(1, ref(a, 1));
        ext(2, ref(a, 2));
        ext(3, ref(a, 3));
      } else if (len(a) == 2){
        ext(app(a, a));
      }
    } else {
      ext(nof(4, a));
    }
  }
  
  public void ext(Object[] a){
    ext = a;
  }
  
  public void ext(int n, Object a){
    ext[n] = a;
  }
  
  public void extx(Object a){
    if (arrp(a)){
      ext(0, ref(a, 0));
      ext(2, ref(a, 1));
    } else {
      ext(0, a);
      ext(2, a);
    }
  }
  
  public void exty(Object a){
    if (arrp(a)){
      ext(1, ref(a, 0));
      ext(3, ref(a, 1));
    } else {
      ext(1, a);
      ext(3, a);
    }
  }
  
  public void sloc(Object a){
    if (arrp(a)){
      sloc(i(ref(a, 0)), i(ref(a, 1)));
    } else {
      sloc(i(a), i(a));
    }
  }
  
  private int[] loc = {0, 0};
  private Object[] ext = {"unlim", "unlim", "unlim", "unlim"};
  
  public void layoutContainer(Container cnt){
    synchronized (cnt.getTreeLock()){
			Component[] cs = vis(cnt);
      if (len(cs) == 0)return;
      Component c = cs[0];
      c.setSize(gprf(c));
      Insets iset = cnt.getInsets();
      int x = iset.left;
      int y = iset.top;
      Dimension vd = cnt.getSize();
      int vw = vd.width;
      int vh = vd.height;
      Dimension cd = c.getSize();
      int cw = cd.width;
      int ch = cd.height;
      Object[] extx = ar(ext[0], ext[2]);
      Object[] exty = ar(ext[1], ext[3]);
      loc[0] = btw(loc[0], limlow(vw, cw, extx), limhig(vw, cw, extx));
      loc[1] = btw(loc[1], limlow(vh, ch, exty), limhig(vh, ch, exty));
      c.setLocation(x+loc[0], y+loc[1]);
    }
	}
  
  // right and bottom
  public static int limlow(int vsiz, int csiz, Object[] ext){
    int ef = gext(ext[0], vsiz, csiz);
    int eb = gext(ext[1], vsiz, csiz);
    return min(sum(vsiz, -csiz, -eb), ef);
  }
  
  // top and left
  public static int limhig(int vsiz, int csiz, Object[] ext){
    int ef = gext(ext[0], vsiz, csiz);
    int eb = gext(ext[1], vsiz, csiz);
    return max(sum(vsiz, -csiz, -eb), ef);
  }
  
  public static int gext(Object ext, int vsiz, int csiz){
    if (intp(ext))return i(ext);
    if (strp(ext)){
      switch (s(ext)){
        case "none": return 0;
        case "unlim": return mxi();
        case "just": return vsiz;
      }
    }
    throw err("gext", "Invalid ext = $1", ext);
  }
  
  public int[] gloc(){return loc;}
  public void sloc(int x, int y){loc = a(x, y);}
  public void slocx(int x){loc[0] = x;}
  public void slocy(int y){loc[1] = y;}
  public void sloc(int[] xy){sloc(xy[0], xy[1]);}
  
  private Dimension laysiz(Container cnt, String tp){
    Component[] cs = vis(cnt);
    if (len(cs) == 0)return dim(0, 0);
    Dimension d;
    switch (tp){
      case "prf": d = gprf(cs[0]); break;
      case "min": d = dim(0, 0); break;
      case "max": d = maxdim(); break;
      default: throw err("laysiz", "Invalid tp = $1", tp);
    }
    return wins(d, cnt.getInsets());
  }
  
	public float getLayoutAlignmentX(Container cnt){return 0.5f;}
	public float getLayoutAlignmentY(Container cnt){return 0.5f;}
	public void invalidateLayout(Container cnt){}
  
	public String toString(){
		return getClass().getName() + "[]";
	}
}
