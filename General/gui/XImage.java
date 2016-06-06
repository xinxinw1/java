package gui;

import tools.$;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class XImage extends JLabel {
  public XImage(Image res){
    super(new ImageIcon(res));
  }
}