package gui;

import tools.$;

import javax.swing.JRadioButton;
import javax.swing.border.CompoundBorder;

import java.awt.Graphics;
import java.awt.Dimension;

public class XRadio extends JRadioButton {
  public XRadio(String txt){
    super(txt);
    X.rmibor(this); // removes default margin
    // note: X.lin(this) has no effect
    setOpaque(false);
    setBackground(X.col("trans"));
  }
  
  protected void paintComponent(Graphics g){
    g.setColor(getBackground());
    g.fillRect(0, 0, getWidth(), getHeight());
    super.paintComponent(g);
  }
}