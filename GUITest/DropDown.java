import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

import javax.swing.*;
import java.awt.*;

public class DropDown implements Runnable {
  public static void main(String[] args){edt(new DropDown());}
  
  public void run(){
    final XFrame f = fra("Drop Down");
    
    final XView[] s = {null};
    
    final Timer tup = itr(50,
      new Rn(){public void r(){s[0].up(15);}});
    final Timer tdown = itr(50,
      new Rn(){public void r(){s[0].down(15);}});
    
    XButton bdown = but("Down!");
    rmibor(bdown);
    
    XPanel p = pan("Y", o("onenter", new Rn(){public void r(){tdown.stop(); tup.start();}},
                          "onleave", new Rn(){public void r(){tup.stop(); tdown.start();}}),
                        but("Hey!!!", o("dim", a(700, 120))),
                        bdown);
    
    add(f, lay(but(o("exp", true,
                     "back", a(255, 255, 255, 80),
                     "hover", a(255, 255, 255, 160),
                     "press", a(255, 255, 255, 250))),
               s[0] = vie(o("exty", ar("none", gprfh(p)-(gprfh(bdown)+5)),
                            "slocy", mni()),
                          p)));
    set(f, o("resize", true));
    dsp(f);
  }
}