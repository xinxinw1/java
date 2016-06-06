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

public class AxisLayout implements LayoutManager2, Serializable {
	private String axis;
  private String alix;
  private String aliy;
  private int gap;
  private Object dist;
  
	public AxisLayout(){
		this("Y");
	}
  
  public AxisLayout(String axis){
		this(axis, o());
	}
  
	public AxisLayout(String axis, Table t){
		saxis(axis);
    alix = "cen";
    aliy = "cen";
    gap = 5;
    dist = "eq";
    set(t);
	}
  
	public String axis(){return axis;}
  
	public void saxis(String axis){
    if (!in(axis, "X", "Y"))throw err("saxis", "Invalid axis = $1", axis);
    this.axis = axis;
  }
  
  public void set(Table t){
    if (t.has("alix"))alix = s(t.get("alix"));
    if (t.has("aliy"))aliy = s(t.get("aliy"));
    if (t.has("gap"))gap = i(t.get("gap"));
    if (t.has("dist"))dist = t.get("dist");
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
  
  /*
  If you are interested, here is the original source code of the next section in Lisp
  (Yes, I "compiled" it by hand into Java).
  
  Notice how much shorter and simpler the Lisp version is:
  
  (def lay (cnt)
    (with (cs (vis cnt)
           (xs ys ws hs) (bnds axis cnt gap alix aliy dist))
      (eachs (c cs x xs y ys w ws h hs)
        (loc x y c)
        (siz w h c))))

  (def bnds (axis cnt gap alix aliy dist)
    (with (cs (vis cnt)
           num (len cs)
           w (wid cnt)
           h (hei cnt)
           tgap (totgap num gap)
           asiz (map sizes cs)
           awid (map [map gwid _] asiz)
           ahei (map [map ghei _] asiz)
           iset (.getInsets cnt)
           x (. iset left)
           y (. iset top))
      (case axis
        "X" (with (ws (sizdir w awid tgap dist)
                   hs (sizopp h ahei)
                   gs (gaps w ws gap alix)
                   xs (posdir x ws gs)
                   ys (posopp y h hs aliy))
              (lis xs ys ws hs))
        "Y" (with (ws (sizopp w awid)
                   hs (sizdir h ahei tgap dist)
                   gs (gaps h hs gap aliy)
                   xs (posopp x w ws alix)
                   ys (posdir y hs gs))
              (lis xs ys ws hs))
        (err "Invalid axis = $1" axis))))

  (def sizes (a) (lis (gmin a) (gprf a) (gmax a)))

  (def gmin (a) (.getMinimumSize a))
  (def gprf (a) (.getPreferredSize a))
  (def gmax (a) (.getMaximumSize a))

  (def gwid (a) (. a width))
  (def ghei (a) (. a height))

  (def siz (w h a) (.setSize a (dim w h)))
  (def loc (x y a) (.setLocation a x y))

  (def sizdir (tot asizdir tgap dist)
    (with (mn (map [_ 0] asizdir)
           prf (map [_ 1] asizdir)
           mx (map [_ 2] asizdir))
      (case dist
        nil prf
        'eq (dequ (- tot tgap (apl + prf)) prf mn mx)
            (err sizdir "Invalid dist = $1" dist))))

  (def sizopp (tot asizdir)
    (map [btw tot (_ 0) (_ 2)] asizdir))

  (def posdir (strt ss gs)
    (sums (+ strt (car gs)) (map + ss (cdr gs))))

  (def posopp (strt tot ss ali)
    (let fl (nali ali)
      (map [+ strt (tint (* (- tot _) fl))] ss)))

  (def gaps (tot ss gp oali)
    (with (ali (gali oali)
           space (- tot (apl + ss))
           num (len ss)
           tgap (totgap num gp)
           ext (- space tgap)
           mids (nof (- num 1) gp))
       (case ali
         'fro (bor mids 0 ext)
         'cen (let (q r) (qr ext 2)
                (bor mids q (+ q r)))
         'end (bor mids ext 0)
         'jus (bor (dequ space (nof (- num 1) 0)) 0 0)
              (err gaps "Invalid ali = $1" ali))))

  (def sums (n a)
    (nrev (rdc (fn (a x) (cons (+ (car a) x) a))
               (lis (+ n (car a))) (cdr a))))

  (def gali (ali)
    (case ali
      'top 'fro
      'lef 'fro
      'cen 'cen
      'bot 'end
      'rig 'end
      'jus 'jus
      ali))

  (def totgap (num gap)
    (if (is num 0) 0
        (* (- num 1) gap)))

  (def bor (a x y)
    (app (lis x) a (lis y)))
  
  
  */
  
  public void lay(Container cnt){
    int[][] bds = bnds(axis, cnt, gap, alix, aliy, dist);
    Component[] cs = vis(cnt);
    int[] xs = bds[0];
    int[] ys = bds[1];
    int[] ws = bds[2];
    int[] hs = bds[3];
    for (int i = 0; i < len(cs); i++){
      loc(xs[i], ys[i], cs[i]);
      siz(ws[i], hs[i], cs[i]);
    }
  }
  
  public static int[][] bnds(String axis, Container cnt, int gap, String alix, String aliy, Object dist){
    Component[] cs = vis(cnt);
    int num = len(cs);
    int w = wid(cnt);
    int h = hei(cnt);
    int tgap = totgap(num, gap);
    Dimension[][] asiz = msizs(cs);
    int[][] awid = mmgwid(asiz);
    int[][] ahei = mmghei(asiz);
    Insets iset = cnt.getInsets();
    int x = iset.left;
    int y = iset.top;
    int[] ws, hs, gs, xs, ys;
    if (is(axis, "X")){
      ws = sizdir(w, awid, tgap, dist);
      hs = sizopp(h, ahei);
      gs = gaps(w, ws, gap, alix);
      xs = posdir(x, ws, gs);
      ys = posopp(y, h, hs, aliy);
    } else {
      ws = sizopp(w, awid);
      hs = sizdir(h, ahei, tgap, dist);
      gs = gaps(h, hs, gap, aliy);
      xs = posopp(x, w, ws, alix);
      ys = posdir(y, hs, gs);
    }
    return aa(xs, ys, ws, hs);
  }
  
  public static int totgap(int num, int gap){
    if (num == 0)return 0;
    return (num-1)*gap;
  }
  
  public static int totgap(Component[] cs, int gap){
    return totgap(len(cs), gap);
  }
  
  public static int[] sizdir(int tot, int[][] asizs, int tgap, Object dist){
    int[] mn = mgmin(asizs);
    int[] prf = mgprf(asizs);
    int[] mx = mgmax(asizs);
    if (is(dist, false))return prf;
    if (is(dist, "eq"))return dequ(tot-tgap-sum(prf), prf, mn, mx);
    throw err("sizdir", "Invalid dist = $1", dist);
  }
  
  public static int[] sizopp(int tot, int[][] asizs){
    int[] r = new int[len(asizs)];
    for (int i = 0; i < len(asizs); i++){
      r[i] = btw(tot, asizs[i][0], asizs[i][2]);
    }
    return r;
  }
  
  public static int[] posdir(int strt, int[] ss, int[] gs){
    return sums(strt+i(fst(gs)), madd(ss, ir(rst(gs))));
  }
  
  public static int[] posopp(int strt, int tot, int[] ss, String ali){
    float fl = nali(ali);
    int[] r = new int[len(ss)];
    for (int i = 0; i < len(ss); i++){
      r[i] = strt + tint((tot-ss[i])*fl);
    }
    return r;
  }
  
  public static int[] gaps(int tot, int[] ss, int gp, String oali){
    String ali = gali(oali);
    int space = tot-sum(ss);
    int num = len(ss);
    int tgap = totgap(num, gp);
    int ext = space-tgap;
    int[] mids = ir(nof(num-1, gp));
    switch (ali){
      case "fro": return ir(bor(mids, 0, ext));
      case "cen":
        int q = ext/2;
        int r = ext%2;
        return ir(bor(mids, q, q+r));
      case "end": return ir(bor(mids, ext, 0));
      case "jus": return ir(bor(dequ(space, ir(nof(num-1, 0))), 0, 0));
      default: throw err("gaps", "Invalid ali = $1", ali);
    }
  }
  
  public static String gali(String ali){
    switch (ali){
      case "top":
      case "lef": return "fro";
      case "cen": return "cen";
      case "bot":
      case "rig": return "end";
      case "jus": return "jus";
      default: return ali;
    }
  }
  
  public static int[][] mmgwid(Dimension[][] a){
    int[][] r = new int[len(a)][];
    for (int i = 0; i < len(a); i++){
      r[i] = new int[len(a[i])];
      for (int j = 0; j < len(a[i]); j++){
        r[i][j] = gwid(a[i][j]);
      }
    }
    return r;
  }
  
  public static int[][] mmghei(Dimension[][] a){
    int[][] r = new int[len(a)][];
    for (int i = 0; i < len(a); i++){
      r[i] = new int[len(a[i])];
      for (int j = 0; j < len(a[i]); j++){
        r[i][j] = ghei(a[i][j]);
      }
    }
    return r;
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
    int w, h;
    if (is(axis, "X")){
      w = sum(sum(mgwid(ds)), totgap(cs, gap));
      h = max(mghei(ds));
    } else {
      w = max(mgwid(ds));
      h = sum(sum(mghei(ds)), totgap(cs, gap));
    }
    return wins(dim(w, h), cnt.getInsets());
	}
  
	public float getLayoutAlignmentX(Container cnt){return 0.5f;}
	public float getLayoutAlignmentY(Container cnt){return 0.5f;}
	public void invalidateLayout(Container cnt){}
  
	public String toString(){
		return getClass().getName() + "[axis=" + axis + "]";
	}
}
