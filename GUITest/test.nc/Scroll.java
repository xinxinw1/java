import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

import javax.swing.*;

public class Scroll {
  public static void main(String[] args){
    edt(new Rn(){public void r(){gui();}});
  }
  
  public static void gui(){
    XFrame f = fra("Title");
    
    /*XView v = new XView(but("Hey!", o("dim", a(500, 600))));
    v.bug(true);
    lin(100, v);
    v.wid(1000);
    v.hei(1000);*/
    XView v;
    add(f, v = vie(o("extx", "none",
                     "pad", 5),
                   but("Hey!", o("dim", a(500, 600)))));
    set(f, o("resize", true));
    dsp(f);
    v.sloc(-100, 100);
  }
}