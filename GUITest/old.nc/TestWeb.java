import tools.*;

import gui.*;
import static gui.X.dj;
import static gui.X.web;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestWeb {
  public static void main(String[] args){
    dj(new Rn(){public void r(){
      gui();
    }});
  }
  
  public static void gui(){
    JFrame f = new JFrame("Title");
    JPanel p = new JPanel();
    p.add(web("http://www.google.com"));
    f.add(p);
    f.pack();
    f.setVisible(true);
  }
}