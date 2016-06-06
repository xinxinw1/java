package gui;

import tools.*;
import static tools.$.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class X {
  public static JComponent glu(String axis){
    switch (axis){
      case "X": return (JComponent) Box.createHorizontalGlue();
      case "Y": return (JComponent) Box.createVerticalGlue();
      default: throw err("glue", "Unknown axis = $1", axis);
    }
  }
  
  public static Component spa(int w, int h){
    return Box.createRigidArea(new Dimension(w, h));
  }
  
  public static Color col(Object a){
    if (a instanceof Color)return (Color) a;
    if (strp(a))return col(s(a));
    if (arrp(a)){
      int[] r = ir(a);
      if (len(a) == 3)return col(r[0], r[1], r[2]);
      if (len(a) == 4)return col(r[0], r[1], r[2], r[3]);
    }
    if (intp(a))return col(i(a), i(a), i(a));
    throw err("col", "Invalid a = $1", a);
  }
  
  public static Color col(int r, int g, int b){
    return new Color(r, g, b);
  }
  
  public static Color col(int r, int g, int b, int a){
    return new Color(r, g, b, a);
  }
  
  public static Color col(String s){
	  if (has("#", s)){
      if (len(s) == 9){
        Color c = Color.decode(s(butn(2, s)));
        return col(c.getRed(), c.getGreen(), c.getBlue(), tint(lasn(2, s), 16));
      }
      return Color.decode(s);
    }
    Table t = o(
      "trans", "FFFFFF00",
      "black", "000000",
      "white", "FFFFFF",
      "dgrey","3c3c3c",
      "grey", "8c8c8c",
      "lgrey", "d2d2d2",
      "green", "00793d",
      "lgreen", "00BF60",
      "blue", "1c75bc",
      "lblue", "34BAE3",
      "red", "DB3333",
      "lred", "F75959",
      "purple", "8255a3",
      "lpurple", "BA7EE6",
      "turq", "008C8F",
      "lturq", "0BD4B9",
      "ygreen", "53991D",
      "lygreen", "A5DB46",
      "orange", "ED6B00",
      "lorange", "FFA719",
      "pink", "E6155A",
      "lpink", "FA66B5"
    );
    // t = $.map(function (a){ return col("#" + a); }, t);
    for (Object k : t.keys()){
      t.set(k, col("#" + s(t.get(k))));
    }
	  return (Color) t.get(s);
  }
  
  public static Color lig(Color c){
    return c.brighter();
  }
  
  public static Color dar(Color c){
    return c.darker();
  }
  
  public static Dimension dim(int w, int h){
    return new Dimension(w, h);
  }
  
  public static Dimension dim(int[] wh){
    return dim(wh[0], wh[1]);
  }
  
  public static int[] dtarr(Dimension d){
    return a(d.width, d.height);
  }
  
  public static Dimension maxdim(){
    return dim(mxi(), mxi());
  }
  
  public static int[] cenloc(Component c){
    Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension com = c.getSize();
    
    int winX = scr.width/2-com.width/2;
    int winY = scr.height/2-com.height/2-20;
    
    if (winX < 0)winX = 0;
    if (winY < 0)winY = 0;
    
    return a(winX, winY);
  }
  
  public static Component cen(Component c){
    return loc(cenloc(c), c);
  }
  
  public static Component loc(int[] d, Component c){
    return loc(d[0], d[1], c);
  }
  
  public static Component loc(int x, int y, Component c){
    c.setLocation(x, y);
    return c;
  }
  
  public static Component siz(int w, int h, Component c){
    c.setSize(w, h);
    return c;
  }
  
  public static <T extends JComponent> T bor(Border b, T c){
    Border curr = c.getBorder();
    if (curr != null){
      c.setBorder((Border) app(b, curr));
    } else {
      c.setBorder(b);
    }
    return c;
  }
  
  public static <T extends JComponent> T rmobor(T c){
    Border b = c.getBorder();
    if (b instanceof CompoundBorder){
      c.setBorder(((CompoundBorder) b).getInsideBorder());
    }
    return c;
  }
  
  public static <T extends JComponent> T rmibor(T c){
    Border b = c.getBorder();
    if (b instanceof CompoundBorder){
      c.setBorder(((CompoundBorder) b).getOutsideBorder());
    }
    return c;
  }
  
  public static EmptyBorder pad(){
    return pad(5);
  }
  
  public static EmptyBorder pad(int n){
    return pad(n, n, n, n);
  }
  
  public static EmptyBorder pad(int x, int y){
    return pad(x, y, x, y);
  }
  
  // I know that in CSS, it's tlbr, but it's counterintuitive
  // if dim is (w, h), loc is (x, y), etc
  public static EmptyBorder pad(int l, int t, int r, int b){
    return new EmptyBorder(t, l, b, r);
  }
  
  public static <T extends JComponent> T pad(T c){
    return bor(pad(), c);
  }
  
  public static <T extends JComponent> T pad(int n, T c){
    return bor(pad(n), c);
  }
  
  public static <T extends JComponent> T pad(int x, int y, T c){
    return bor(pad(x, y), c);
  }
  
  public static <T extends JComponent> T pad(int l, int t, int r, int b, T c){
    return bor(pad(l, t, r, b), c);
  }
  
  public static <T extends JComponent> T pad(int[] p, T c){
    switch (p.length){
      case 4: return pad(p[0], p[1], p[2], p[3], c);
      case 2: return pad(p[0], p[1], c);
      default: throw err("pad", "Invalid length of p = $1", p);
    }
  }
  
  public static LineBorder lin(){
    return lin(col("black"));
  }
  
  public static LineBorder lin(Color col){
    return lin(1, col);
  }
  
  public static LineBorder lin(int w){
    return lin(w, col("black"));
  }
  
  public static LineBorder lin(int w, Color col){
    return new LineBorder(col, w);
  }
  
  public static <T extends JComponent> T lin(T c){
    return bor(lin(), c);
  }
  
  public static <T extends JComponent> T lin(Color col, T c){
    return bor(lin(col), c);
  }
  
  public static <T extends JComponent> T lin(int w, T c){
    return bor(lin(w), c);
  }
  
  public static <T extends JComponent> T lin(int w, Color col, T c){
    return bor(lin(w, col), c);
  }
  
  public static JComponent usiz(JComponent c){
    c.setMaximumSize(maxdim());
    return c;
  }
  
  public static JComponent alix(String ali, JComponent c){
    c.setAlignmentX(nali(ali));
    return c;
  }
  
  public static JComponent aliy(String ali, JComponent c){
    c.setAlignmentY(nali(ali));
    return c;
  }
  
  public static float nali(String ali){
    switch (ali){
      case "lef": return Component.LEFT_ALIGNMENT;
      case "rig": return Component.RIGHT_ALIGNMENT;
      case "top": return Component.TOP_ALIGNMENT;
      case "bot": return Component.BOTTOM_ALIGNMENT;
      case "cen": return Component.CENTER_ALIGNMENT;
      default: throw err("nali", "Unknown ali = $1", ali);
    }
  }
  
  public static Dimension wdim(Dimension d, int w, int h){
    Dimension s = d;
    if (w != -1)s = dim(w, s.height);
    if (h != -1)s = dim(s.width, h);
    return s;
  }
  
  public static Dimension wdim(Dimension d, int[]... rst){
    Dimension r = d;
    for (int[] wh : rst){
      r = wdim(r, wh[0], wh[1]);
    }
    return r;
  }
  
  public static Dimension wins(Dimension d, Insets i){
    return dim(sum(d.width, i.left, i.right), sum(d.height, i.top, i.bottom));
  }
  
  public static Dimension wexp(Dimension unlim, Dimension lim, boolean[] exp){
    return dim(rplt(exp, dtarr(lim), dtarr(unlim)));
  }
  
  public static Dimension btwd(Dimension a, Dimension mn, Dimension mx){
    return dim(btw(a.width, mn.width, mx.width), btw(a.height, mn.height, mx.height));
  }
  
  public static Dimension btwd(Dimension a, int mn, Dimension mx){
    return dim(btw(a.width, mn, mx.width), btw(a.height, mn, mx.height));
  }
  
  public static Dimension btwd(Dimension a, Dimension mn, int mx){
    return dim(btw(a.width, mn.width, mx), btw(a.height, mn.height, mx));
  }
  
  public static Dimension btwd(Dimension a, int mn, int mx){
    return dim(btw(a.width, mn, mx), btw(a.height, mn, mx));
  }
  
  /*public static int[] mksiz(String tp, Object a, int[] curr){
    if (arrp(a))return mksiz(tp, ir(a), curr);
    return mksiz(tp, a(i(a), i(a)), curr);
  }
  
  public static int[] mksiz(String tp, int[] a, int[] curr){
    return rplt(map([is _ -2], a), a, curr);
  }*/
  
  public static Component[] vis(Container cnt){
    Component[] r = {};
    for (Component c : cnt.getComponents()){
			if (c.isVisible())r = tai(r, c);
    }
    return r;
  }
  
  public static Dimension[] sizs(Component a){
    return ar(gmin(a), gprf(a), gmax(a));
  }
  
  public static Dimension gmin(Component a){
    return a.getMinimumSize();
  }
  
  public static Dimension gprf(Component a){
    return a.getPreferredSize();
  }
  
  public static Dimension gmax(Component a){
    return a.getMaximumSize();
  }
  
  public static int gwid(Dimension a){
    return a.width;
  }
  
  public static int ghei(Dimension a){
    return a.height;
  }
  
  public static int wid(Container c){
    Insets i = c.getInsets();
    return c.getSize().width - i.left - i.right;
  }
  
  public static int hei(Container c){
    Insets i = c.getInsets();
    return c.getSize().height - i.top - i.bottom;
  }
  
  public static Dimension wh(Container c){
    return dim(wid(c), hei(c));
  }
  
  public static int gminw(Component a){return gwid(gmin(a));}
  public static int gminh(Component a){return ghei(gmin(a));}
  public static int gprfw(Component a){return gwid(gprf(a));}
  public static int gprfh(Component a){return ghei(gprf(a));}
  public static int gmaxw(Component a){return gwid(gmax(a));}
  public static int gmaxh(Component a){return ghei(gmax(a));}
  
  public static Dimension[][] msizs(Component[] a){
    Dimension[][] r = new Dimension[len(a)][];
    for (int i = 0; i < len(a); i++)r[i] = X.sizs(a[i]);
    return r;
  }
  
  public static Dimension[] mgprf(Component[] a){
    Dimension[] r = new Dimension[len(a)];
    for (int i = 0; i < len(a); i++)r[i] = X.gprf(a[i]);
    return r;
  }
  
  public static Dimension[] mgmin(Component[] a){
    Dimension[] r = new Dimension[len(a)];
    for (int i = 0; i < len(a); i++)r[i] = X.gmin(a[i]);
    return r;
  }
  
  public static Dimension[] mgmax(Component[] a){
    Dimension[] r = new Dimension[len(a)];
    for (int i = 0; i < len(a); i++)r[i] = X.gmax(a[i]);
    return r;
  }
  
  public static int[] mgprf(int[][] a){
    int[] r = new int[len(a)];
    for (int i = 0; i < len(a); i++)r[i] = a[i][1];
    return r;
  }
  
  public static int[] mgmin(int[][] a){
    int[] r = new int[len(a)];
    for (int i = 0; i < len(a); i++)r[i] = a[i][0];
    return r;
  }
  
  public static int[] mgmax(int[][] a){
    int[] r = new int[len(a)];
    for (int i = 0; i < len(a); i++)r[i] = a[i][2];
    return r;
  }
  
  public static int[] mgwid(Dimension[] a){
    int[] r = new int[len(a)];
    for (int i = 0; i < len(a); i++)r[i] = X.gwid(a[i]);
    return r;
  }
  
  public static int[] mghei(Dimension[] a){
    int[] r = new int[len(a)];
    for (int i = 0; i < len(a); i++)r[i] = X.ghei(a[i]);
    return r;
  }
  
  public static <T extends Container> T add(T cnt, Object... cs){
    for (Object c : cs){
      if (c instanceof XGroup){
        add(cnt, (Object[]) ((XGroup) c).elms());
      } else if (arrp(c)){
        for (int i = 0; i < len(c); i++){
          add(cnt, ref(c, i));
        }
      } else {
        cnt.add((Component) c);
      }
    }
    return cnt;
  }
  
  public static <T extends Container> T rem(T cnt, Object... cs){
    for (Object c : cs){
      if (c instanceof XGroup){
        rem(cnt, (Object[]) ((XGroup) c).elms());
      } else if (arrp(c)){
        for (int i = 0; i < len(c); i++){
          rem(cnt, ref(c, i));
        }
      } else {
        cnt.remove((Component) c);
      }
    }
    return cnt;
  }
  
  public static void setall(JComponent c, Table t){
    setmou(c, t);
    setbor(c, t);
    setcol(c, t);
    setfont(c, t);
    setdims(c, t);
    setx(c, t);
  }
  
  public static void setmou(Component c, Table t){
    if (t.has("onenter"))onenter(c, (Runnable) t.get("onenter"));
    if (t.has("onleave"))onleave(c, (Runnable) t.get("onleave"));
  }
  
  public static void setbor(JComponent c, Table t){
    if (t.has("bor"))c.setBorder((Border) t.get("bor"));
    if (t.has("pad")){
      Object p = t.get("pad");
      if (arrp(p))pad((int[]) p, c);
      else pad(i(p), c);
    }
  }
  
  public static void setcol(Component c, Table t){
    setfore(c, t);
    setback(c, t);
  }
  
  public static void setfore(Component c, Table t){
    if (t.has("col"))c.setForeground(col(t.get("col")));
  }
  
  public static void setback(Component c, Table t){
    if (t.has("back"))c.setBackground(col(t.get("back")));
  }
  
  public static void setfont(Component c, Table t){
    if (t.has("font"))setfont(c, "nm", t.get("font"));
    if (t.has("siz"))setfont(c, "siz", t.get("siz"));
    if (t.has("i"))setfont(c, "i", t.get("i"));
    if (t.has("b"))setfont(c, "b", t.get("b"));
  }
  
  public static void setdims(Component c, Table t){
    if (t.has("dim"))setdim(c, "dim", t.get("dim"));
    if (t.has("min"))setdim(c, "min", t.get("min"));
    if (t.has("max"))setdim(c, "max", t.get("max"));
    if (c instanceof XElem){
      XElem x = (XElem) c;
      if (t.has("w"))x.wid(i(t.get("w")));
      if (t.has("h"))x.hei(i(t.get("h")));
      if (t.has("minw"))x.minw(i(t.get("minw")));
      if (t.has("minh"))x.minh(i(t.get("minh")));
      if (t.has("maxw"))x.maxw(i(t.get("maxw")));
      if (t.has("maxh"))x.maxh(i(t.get("maxh")));
      if (t.has("exp")){
        Object exp = t.get("exp");
        if (arrp(exp)){
          x.expx(br(exp)[0]);
          x.expy(br(exp)[1]);
        } else if (bolp(exp)){
          x.expx(b(exp));
          x.expy(b(exp));
        }
      }
      if (t.has("expx"))x.expx(b(t.get("expx")));
      if (t.has("expy"))x.expy(b(t.get("expy")));
      if (t.has("bug"))x.bug(b(t.get("bug")));
    }
  }
  
  public static void setx(Component c, Table t){
    if (c instanceof XElem){
      XElem x = (XElem) c;
      x.set(t);
    }
  }
  
  public static void setdim(Component c, String tp, Object d){
    if (d instanceof Dimension){
      setdim(c, tp, (Dimension) d);
    } else if (arrp(d)){
      setdim(c, tp, dim(ir(d)[0], ir(d)[1]));
    } else if (intp(d)){
      setdim(c, tp, dim(i(d), i(d)));
    }
  }
  
  public static void setdim(Component c, String tp, Dimension d){
    if (c instanceof XElem){
      XElem x = (XElem) c;
      if (is(tp, "dim")){
        x.wid(d.width);
        x.hei(d.height);
      } else if (is(tp, "min")){
        x.minw(d.width);
        x.minh(d.height);
      } else if (is(tp, "max")){
        x.maxw(d.width);
        x.maxh(d.height);
      }
    } else {
      if (is(tp, "dim"))c.setPreferredSize(d);
      if (is(tp, "min"))c.setMinimumSize(d);
      if (is(tp, "max"))c.setMaximumSize(d);
    }
  }
  
  public static void setdim(Component c, Object d){setdim(c, "all", d);}
  
  public static void setfont(Component c, Object k, Object v){
    c.setFont(wfont(c.getFont(), o(k, v)));
  }
  
  public static Font wfont(Font f, Table t){
    String nm = s(cnul(t.get("nm"), f.getName()));
    int siz = i(cnul(t.get("siz"), f.getSize()));
    int sty = f.getStyle();
    boolean i = f.isItalic();
    boolean b = f.isBold();
    if (t.has("i")){
      if (i && !b(t.get("i")))sty -= Font.ITALIC;
      else sty = sty | Font.ITALIC;
    }
    if (t.has("b")){
      if (b && !b(t.get("b")))sty -= Font.BOLD;
      else sty = sty | Font.BOLD;
    }
    return new Font(nm, sty, siz);
  }
  
  public static <T extends JFrame> T set(T c, Table t){
    setmou(c, t);
    setdims(c, t);
    setx(c, t);
    if (t.has("resize"))c.setResizable(b(t.get("resize")));
    if (t.has("defbut"))c.getRootPane().setDefaultButton((JButton) t.get("defbut"));
    if (t.has("lis"))c.addWindowListener((WindowListener) t.get("lis"));
    if (t.has("onclose")){
      final Runnable r = (Runnable) t.get("onclose");
      c.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e){
          r.run();
        }
      });
    }
    if (t.has("onfocus")){
      final Runnable r = (Runnable) t.get("onfocus");
      c.addWindowListener(new WindowAdapter(){
        public void windowActivated(WindowEvent e){
          r.run();
        }
      });
    }
    if (t.has("onblur")){
      final Runnable r = (Runnable) t.get("onblur");
      c.addWindowListener(new WindowAdapter(){
        public void windowDeactivated(WindowEvent e){
          r.run();
        }
      });
    }
    if (t.has("vis"))c.setVisible(b(t.get("vis")));
    if (t.has("loc"))loc((int[]) t.get("loc"), c);
    return c;
  }
  
  public static <T extends JPanel> T set(T c, Table t){
    setmou(c, t);
    setbor(c, t);
    setcol(c, t);
    setdims(c, t);
    setx(c, t);
    if (t.has("onover")){
      final Runnable r = (Runnable) t.get("onover");
      c.addMouseListener(new MouseAdapter(){
        public void mouseEntered(MouseEvent e){
          r.run();
        }
      });
    }
    if (t.has("onout")){
      final Runnable r = (Runnable) t.get("onout");
      c.addMouseListener(new MouseAdapter(){
        public void mouseExited(MouseEvent e){
          r.run();
        }
      });
    }
    return c;
  }
  
  public static <T extends JLayeredPane> T set(T c, Table t){
    setmou(c, t);
    setbor(c, t);
    setcol(c, t);
    setdims(c, t);
    setx(c, t);
    return c;
  }
  
  public static <T extends JScrollPane> T set(T c, Table t){
    setmou(c, t);
    setbor(c, t);
    setcol(c, t);
    setdims(c, t);
    setx(c, t);
    return c;
  }
  
  public static <T extends JViewport> T set(T c, Table t){
    setmou(c, t);
    setbor(c, t);
    setcol(c, t);
    setdims(c, t);
    setx(c, t);
    return c;
  }
  
  public static <T extends JButton> T set(T c, Table t){
    setmou(c, t);
    setbor(c, t);
    setfore(c, t);
    setcol(c, t);
    setfont(c, t);
    setdims(c, t);
    setx(c, t);
    if (t.has("back")){
      c.setContentAreaFilled(false);
      c.setFocusPainted(false);
      c.setBackground(col(t.get("back")));
    }
    if (t.has("lis"))c.addActionListener((ActionListener) t.get("lis"));
    if (t.has("onclick")){
      final Runnable r = (Runnable) t.get("onclick");
      c.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          r.run();
        }
      });
    }
    if (t.has("onover")){
      final Runnable r = (Runnable) t.get("onover");
      c.addMouseListener(new MouseAdapter(){
        public void mouseEntered(MouseEvent e){
          r.run();
        }
      });
    }
    if (t.has("onout")){
      final Runnable r = (Runnable) t.get("onout");
      c.addMouseListener(new MouseAdapter(){
        public void mouseExited(MouseEvent e){
          r.run();
        }
      });
    }
    return c;
  }
  
  public static <T extends JTextField> T set(T c, Table t){
    setmou(c, t);
    setbor(c, t);
    setcol(c, t);
    setfont(c, t);
    setdims(c, t);
    setx(c, t);
    if (t.has("ali"))c.setHorizontalAlignment(ninpali(s(t.get("ali"))));
    return c;
  }
  
  public static <T extends JTextPane> T set(T c, Table t){
    setmou(c, t);
    setbor(c, t);
    setcol(c, t);
    setdims(c, t);
    setx(c, t);
    return c;
  }
  
  public static <T extends JRadioButton> T set(T c, Table t){
    setmou(c, t);
    setbor(c, t);
    setcol(c, t);
    setfont(c, t);
    setdims(c, t);
    setx(c, t);
    if (t.has("lis"))c.addActionListener((ActionListener) t.get("lis"));
    if (t.has("onclick")){
      final Runnable r = (Runnable) t.get("onclick");
      c.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          r.run();
        }
      });
    }
    return c;
  }
  
  public static <T extends JWebBrowser> T set(T c, Table t){
    setmou(c, t);
    setbor(c, t);
    setdims(c, t);
    setx(c, t);
    if (t.has("bars"))c.setBarsVisible(b(t.get("bars")));
    return c;
  }
  
  public static <T extends Object> T set(T c, Table t){
    return c;
  }
  
  public static Table defs = o();
  
  public static Table def(Object... t){
    return defs = t(app(defs, o(t)));
  }
  
  public static Table gdef(String k){
    return t(cnul(defs.get(k), o()));
  }
  
  public static <T extends JFrame> T sdef(T f){
    return set(f, gdef("fra"));
  }
  
  public static <T extends JPanel> T sdef(T f){
    return set(f, gdef("pan"));
  }
  
  public static <T extends JLayeredPane> T sdef(T f){
    return set(f, gdef("lay"));
  }
  
  public static <T extends JScrollPane> T sdef(T f){
    return set(f, gdef("scr"));
  }
  
  public static <T extends JViewport> T sdef(T f){
    return set(f, gdef("vie"));
  }
  
  public static <T extends JButton> T sdef(T f){
    return set(f, gdef("but"));
  }
  
  public static <T extends JTextField> T sdef(T f){
    return set(f, gdef("inp"));
  }
  
  public static <T extends JTextPane> T sdef(T f){
    return set(f, gdef("txt"));
  }
  
  public static <T extends XImage> T sdef(T f){
    return set(f, gdef("img"));
  }
  
  public static <T extends JRadioButton> T sdef(T f){
    return set(f, gdef("rad"));
  }
  
  public static <T extends ButtonGroup> T sdef(T f){
    return set(f, gdef("grp"));
  }
  
  public static <T extends JWebBrowser> T sdef(T f){
    return set(f, gdef("web"));
  }
  
  public static <T extends Object> T sdef(T f){
    return f;
  }
  
  public static int ninpali(String ali){
    switch (ali){
      case "lef": return JTextField.LEFT;
      case "cen": return JTextField.CENTER;
      case "rig": return JTextField.RIGHT;
      case "lead": return JTextField.LEADING;
      case "trai": return JTextField.TRAILING;
      default: throw err("ninpali", "Unknown ali = $1", ali);
    }
  }
  
  public static XFrame fra(){
    return fra("");
  }
  
  public static XFrame fra(String title){
    return sdef(new XFrame(title));
  }
  
  public static XFrame fra(String title, Table t){
    return set(fra(title), t);
  }
  
  public static XFrame fra(String title, Object... c){
    return add(fra(title), c);
  }
  
  public static XFrame fra(String title, Table t, Object... c){
    return add(fra(title, t), c);
  }
  
  public static XFrame dsp(XFrame f){
    f.pack();
    f.setVisible(true);
    return f;
  }
  
  public static XFrame chk(XFrame f){
    Dimension curr = f.getSize();
    Dimension mn = f.getMinimumSize();
    Dimension mx = f.getMaximumSize();
    f.setMinimumSize(mn);
    f.setSize(btw(curr.width, mn.width, mx.width), btw(curr.height, mn.height, mx.height));
    f.validate();
    return f;
  }
  
  public static XButton but(String txt){
    return sdef(new XButton(txt));
  }
  
  public static XButton but(String txt, Table t){
    return set(but(txt), t);
  }
  
  public static XButton but(String txt, Runnable r){
    return but(txt, o("onclick", r));
  }
  
  public static XButton but(String txt, Table t, Runnable r){
    return but(txt, wit(t, "onclick", r));
  }
  
  public static XButton but(){
    return but("");
  }
  
  public static XButton but(Table t){
    return but("", t);
  }
  
  public static XButton but(Runnable r){
    return but("", r);
  }
  
  public static XButton but(Table t, Runnable r){
    return but("", t, r);
  }
  
  public static XPanel pan(String axis){
    return pan(axis, o());
  }
  
  public static XPanel pan(String axis, Table t){
    return set(sdef(new XPanel(axis, t)), t);
  }
  
  public static XPanel pan(String axis, Object... c){
    return add(pan(axis), c);
  }
  
  public static XPanel pan(String axis, Table t, Object... c){
    return add(pan(axis, t), c);
  }
  
  public static XLayers lay(){
    return sdef(new XLayers());
  }
  
  public static XLayers lay(Table t){
    return set(lay(), t);
  }
  
  public static XLayers lay(Object... c){
    return add(lay(), c);
  }
  
  public static XLayers lay(Table t, Object... c){
    return add(lay(t), c);
  }
  
  /*public static XScroll scr(){
    return sdef(new XScroll());
  }
  
  public static XScroll scr(Component c){
    return sdef(new XScroll(c));
  }
  
  public static XScroll scr(Table t){
    return set(scr(), t);
  }
  
  public static XScroll scr(Table t, Component c){
    return set(scr(c), t);
  }*/
  
  public static XView vie(){
    return sdef(new XView());
  }
  
  public static XView vie(Component c){
    return sdef(new XView(c));
  }
  
  public static XView vie(Table t){
    return set(vie(), t);
  }
  
  public static XView vie(Table t, Component c){
    return set(vie(c), t);
  }
  
  public static XText txt(){
    return txt("");
  }
  
  public static XText txt(Table t){
    return set(txt(), t);
  }
  
  public static XText txt(String s){
    return sdef(new XText(s));
  }
  
  public static XText txt(String s, Table t){
    return set(txt(s), t);
  }
  
  public static BufferedImage getimg(String f){
    try {
      return ImageIO.read(fil(f));
    } catch (Exception e){
      throw err("getimg", "File error e = $1", e);
    }
  }
  
  public static XImage img(Image res){
    return sdef(new XImage(res));
  }
  
  // http://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
  public static XImage img(String res){
    return img(getimg(res));
  }
  
  public static XImage img(String res, int w, int h){
    return img(scal(getimg(res), w, h));
  }
  
  public static XImage img(String res, int w, int h, String tp){
    return img(scal(getimg(res), w, h, tp));
  }
  
  public static BufferedImage scal(BufferedImage img, int w, int h){
    return scal(img, w, h, "lin");
  }
  
  public static BufferedImage scal(BufferedImage img, int w, int h, String tp){
    double scx = ((double) w)/img.getWidth();
    double scy = ((double) h)/img.getHeight();
    AffineTransform t = AffineTransform.getScaleInstance(scx, scy);
    AffineTransformOp op = new AffineTransformOp(t, nscal(tp));
    return op.filter(img, new BufferedImage(w, h, img.getType()));
  }
  
  public static int nscal(String tp){
    switch (tp){
      case "lin": return AffineTransformOp.TYPE_BILINEAR;
      case "cub": return AffineTransformOp.TYPE_BICUBIC;
      case "nei": return AffineTransformOp.TYPE_NEAREST_NEIGHBOR;
      default: throw err("nscal", "Unknown tp = $1", tp);
    }
  }
  
  public static XInput inp(){
    return sdef(new XInput());
  }
  
  public static XInput inp(String txt){
    return sdef(new XInput(txt));
  }
  
  public static XInput inp(Table t){
    return set(inp(), t);
  }
  
  public static XInput inp(String txt, Table t){
    return set(inp(txt), t);
  }
  
  public static XRadio rad(String txt){
    return sdef(new XRadio(txt));
  }
  
  public static XRadio rad(String txt, Table t){
    return set(rad(txt), t);
  }
  
  public static XRadio rad(String txt, Runnable r){
    return rad(txt, o("onclick", r));
  }
  
  public static XRadio rad(String txt, Table t, Runnable r){
    return rad(txt, wit(t, "onclick", r));
  }
  
  public static XGroup grp(XRadio... b){
    XGroup g = new XGroup();
    for (XRadio r : b)g.add(r);
    return g;
  }
  
  public static XWeb web(String url){
    return sdef(new XWeb(url));
  }
  
  public static XWeb web(String url, Table t){
    return set(web(url), t);
  }
  
  public static XWeb web(String url, int w, int h){
    return web(url, o("dim", a(w, h)));
  }
  
  public static XWeb web(String url, int w, int h, Table t){
    return web(url, wit(t, "dim", a(w, h)));
  }
  
  // defaults from youtube.com
  public static XWeb ytb(String s){
    return ytb(s, 640, 390);
  }
  
  public static XWeb ytb(String s, int w, int h){
    return web("https://www.youtube.com/embed/" + s, w, h);
  }
  
  // Event Dispatch Thread
  public static void edt(Runnable a){
    UIManager.put("swing.boldMetal", Boolean.FALSE);
    SwingUtilities.invokeLater(a);
  }
  
  // http://stackoverflow.com/questions/20440484/embed-a-youtube-video-to-jframe
  public static void dj(Runnable a){
    NativeInterface.open();
    edt(a);
    NativeInterface.runEventPump();
  }
  
  public static void onenter(final Component a, final Runnable r){
    final Container par = a.getParent();
    long mask = AWTEvent.MOUSE_MOTION_EVENT_MASK +
                AWTEvent.MOUSE_EVENT_MASK;
    Toolkit.getDefaultToolkit().addAWTEventListener(
      new AWTEventListener(){
        private boolean exited = true;
        public void eventDispatched(AWTEvent e){
          //$.prn("here!");
          MouseEvent m = (MouseEvent) e;
          Component c = (Component) e.getSource();
          Point p1 = m.getPoint();
          Point p2 = cnvpt(p1, c, a);
          if (hasx(p2, a) && !(m.getID() == MouseEvent.MOUSE_EXITED && sameedge(p1, c, p2, a))){
            if (exited){
              r.run();
              exited = false;
            }
          } else {
            if (!exited){
              exited = true;
            }
          }
        }
    }, mask);
  }
  
  public static void onleave(final Component a, final Runnable r){
    long mask = AWTEvent.MOUSE_MOTION_EVENT_MASK +
                AWTEvent.MOUSE_EVENT_MASK;
    //long mask = 1048575L;
    Toolkit.getDefaultToolkit().addAWTEventListener(
      new AWTEventListener(){
        private boolean exited = true;
        public void eventDispatched(AWTEvent e){
          MouseEvent m = (MouseEvent) e;
          Component c = (Component) e.getSource();
          //$.prn(cls(c));
          //$.prn(sli(m.paramString(), 0, 22));
          Point p1 = m.getPoint();
          Point p2 = cnvpt(m.getPoint(), c, a);
          if (hasx(p2, a) && !(m.getID() == MouseEvent.MOUSE_EXITED && sameedge(p1, c, p2, a))){
            if (exited){
              exited = false;
            }
          } else {
            if (!exited){
              r.run();
              exited = true;
            }
          }
        }
    }, mask);
  }
  
  public static Point cnvpt(Point p, Component a, Component b){
    return SwingUtilities.convertPoint(a, p, b);
  }
  
  public static boolean sameedge(Point p1, Component c1, Point p2, Component c2){
    Dimension d1 = c1.getSize();
    Dimension d2 = c2.getSize();
    return (p1.x == 0 && p2.x == 0) || (p1.x == d1.width && p2.x == d2.width) ||
           (p1.y == 0 && p2.y == 0) || (p1.y == d1.height && p2.y == d2.height);
  }
  
  public static JFrame gfra(Component c){
    return (JFrame) SwingUtilities.getWindowAncestor(c);
  }
  
  public static boolean hasx(Component x, Component a){
    return SwingUtilities.isDescendingFrom(x, a);
  }
  
  public static boolean hasx(Point x, Component a){
    Dimension d = a.getSize();
    return btwp(x.x, 0, d.width) && btwp(x.y, 0, d.height);
  }
  
  private static Object app(Object a, Object b){
    if (a instanceof Border && b instanceof Border){
      return new CompoundBorder((Border) a, (Border) b);
    }
    return $.app(a, b);
  }
  
  public static Timer itr(int n, final Runnable r){
    final Timer t = new Timer(n, null);
    t.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        r.run();
      }
    });
    return t;
  }
}