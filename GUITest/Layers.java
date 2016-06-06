import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

import javax.swing.*;
import java.awt.*;

public class Layers {
  public static void main(String[] args){
    edt(new Rn(){public void r(){gui();}});
  }
  
  public static void gui(){
    final XFrame f = fra("Slider Bar");
    
    Table al = o("bor", null);
    
    def("but", wit(al, "dim", a(300, 210),
                       "b", true));
    
    XButton[] bs = {but("1", o("back", a(200, 255, 117),
                               "hover", a(210, 255, 143),
                               "press", a(220, 255, 168))),
                    but("2", o("back", a(117, 216, 255),
                               "hover", a(143, 223, 255),
                               "press", a(168, 230, 255))),
                    but("3", o("back", a(255, 146, 87),
                               "hover", a(255, 162, 112),
                               "press", a(255, 179, 138)))};
    
    def("but", wit(al, "font", "Verdana",
                       "dim", a(50, 110),
                       "siz", 36,
                       "back", a(255, 255, 255, 80),
                       "hover", a(255, 255, 255, 160),
                       "press", a(255, 255, 255, 200),
                       "col", a(100, 100, 100, 90)));
    
    // {null} is a hack to get around Java's final requirement 
    // for use in inner classes
    final XView[] s = {null};
    final Timer tlef = itr(50,
      new Rn(){public void r(){s[0].left(15);}});
    final Timer trig = itr(50,
      new Rn(){public void r(){s[0].rig(15);}});
    
    XButton blef = but("<",
      o("onover", new Rn(){public void r(){tlef.start();}},
        "onout", new Rn(){public void r(){tlef.stop();}}));
    
    XButton brig = but(">",
      o("onover", new Rn(){public void r(){trig.start();}},
        "onout", new Rn(){public void r(){trig.stop();}}));
    
    // reset button default styles
    def("but", o());
                   
    add(f, pan("Y", o("pad", 5),
                    lay(o("back", "white",
                          "expy", false,
                          "pad", 10),
                        s[0] = vie(o("extx", 0,
                                     "w", 510),
                                   pan("X", o("gap", 20),
                                            ob(bs))),
                        pan("X", o("max", mxi(),
                                   "alix", "jus"),
                                 blef, brig)),
                    txt("Yes!!! This now works!!!"),
                    pan("X", but("Button 1"), but("Button 2"))));
    set(f, o("resize", true));
    dsp(f);
  }
}