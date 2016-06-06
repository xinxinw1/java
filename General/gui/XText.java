package gui;

import tools.*;
import static tools.$.is;
import static tools.$.o;
import static tools.$.s;
import static tools.$.i;
import static tools.$.b;
import static tools.$.t;

import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import javax.swing.border.Border;

import java.awt.Insets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class XText extends JTextPane implements XElem {
  private StyledDocument doc;
  private SimpleAttributeSet attr;
  
  public XText(){
    this("");
  }
  
  public XText(String txt){
    super();
    doc = getStyledDocument();
    attr = new SimpleAttributeSet(getParagraphAttributes());
    getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none");
    setEditable(false);
    set("ali", "lef");
    setOpaque(false);
    setBackground(X.col("trans"));
    setMargin(new Insets(0, 0, 0, 0));
    setText(txt);
  }
  
  // http://java-sl.com/tip_text_height_measuring.html
  private JTextPane dummy(){
    JTextPane d = new JTextPane(doc);
    d.setMargin(new Insets(0, 0, 0, 0));
    return d;
  }
  
  private int[] d = {-1, -1};
  private int[] mn = {-1, -1};
  private int[] mx = {-1, -1};
  private boolean[] exp = {false, false};
  private boolean bug = false;
  
  public Dimension getMinimumSize(){
    Dimension min = X.dim(0, 0);
    Dimension prf = getPreferredSize();
    if (bug)$.prn("min: $1", min);
    return X.wdim(X.wexp(min, prf, exp), mn);
  }
  
  public Dimension getPreferredSize(){
    Dimension prf = dummy().getPreferredSize();
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
  
  // http://stackoverflow.com/questions/7373345/how-to-create-partly-transparent-jbutton-on-fully-transparent-jframe
  protected void paintComponent(Graphics g){
    g.setColor(getBackground());
    g.fillRect(0, 0, getWidth(), getHeight());
    super.paintComponent(g);
  }
  
  public String getText(){
    try {
      return doc.getText(0, doc.getLength());
    } catch (Exception e){
      return "";
    }
  }
  
  public void addText(String s){
    try {
      doc.insertString(doc.getLength(), s, attr);
    } catch (Exception e){}
  }
  
  public void addText(String s, Table t){
    Table orig = getAll(t.keys());
    setCurr(t);
    try {
      doc.insertString(doc.getLength(), s, attr);
    } catch (Exception e){}
    setCurr(orig);
  }
  
  public void clrText(){
    try {
      doc.remove(0, doc.getLength());
    } catch (Exception e){}
  }
  
  public void setText(String s){
    String[] ls = s.split("\\n\\n");
    if (ls.length >= 1){
      clrText();
      addText(ls[0]);
      int size = i(get("siz"));
      for (int i = 1; i < ls.length; i++){
        addText("\n");
        addText("\n", o("siz", size/2));
        addText(ls[i]);
      }
    }
    revalidate();
  }
  
  public Object get(String k){
    switch (k){
      case "back": return getBackground();
      case "ali": return alin(StyleConstants.getAlignment(attr));
      case "col": return StyleConstants.getForeground(attr);
      case "font": return StyleConstants.getFontFamily(attr);
      case "siz": return StyleConstants.getFontSize(attr);
      case "i": return StyleConstants.isItalic(attr);
      case "b": return StyleConstants.isBold(attr);
      case "u": return StyleConstants.isUnderline(attr);
      default: return null;
    }
  }
  
  public Table getAll(Object[] keys){
    Table t = o();
    for (Object k : keys)t.set(k, get(s(k)));
    return t;
  }
  
  public void setCurr(String k, Object v){
    switch (k){
      case "back": setBackground(X.col(v)); break;
      case "ali": StyleConstants.setAlignment(attr, nali(s(v))); break;
      case "col": StyleConstants.setForeground(attr, X.col(v)); break;
      case "font": StyleConstants.setFontFamily(attr, s(v)); break;
      case "siz": StyleConstants.setFontSize(attr, i(v)); break;
      case "i": StyleConstants.setItalic(attr, b(v)); break;
      case "b": StyleConstants.setBold(attr, b(v)); break;
      case "u": StyleConstants.setUnderline(attr, b(v)); break;
    }
  }
  
  public void setCurr(Table t){
    for (Object k : t.keys()){
      setCurr(s(k), t.get(k));
    }
  }
  
  public void set(String k, Object v){
    setCurr(k, v);
    doc.setParagraphAttributes(0, doc.getLength(), attr, true);
    setText(getText());
  }
  
  public void set(Table t){
    for (Object k : t.keys()){
      set(s(k), t.get(k));
    }
  }
  
  private int nali(String ali){
    switch (ali){
      case "lef": return StyleConstants.ALIGN_LEFT;
      case "cen": return StyleConstants.ALIGN_CENTER;
      case "rig": return StyleConstants.ALIGN_RIGHT;
      case "jus": return StyleConstants.ALIGN_JUSTIFIED;
      default: throw $.err("nali", "Unknown ali = $1", ali);
    }
  }
  
  private String alin(int n){
    switch (n){
      case StyleConstants.ALIGN_LEFT: return "lef";
      case StyleConstants.ALIGN_CENTER: return "cen";
      case StyleConstants.ALIGN_RIGHT: return "rig";
      case StyleConstants.ALIGN_JUSTIFIED: return "jus";
      default: throw $.err("alin", "Unknown n = $1", n);
    }
  }
}