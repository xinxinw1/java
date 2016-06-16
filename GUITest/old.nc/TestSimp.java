import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

import javax.swing.*;
import java.awt.*;

public class TestSimp {
  public static void main(String[] args){
    edt(new Rn(){public void r(){gui();}});
  }
  
  public static void gui(){
    String txt = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sagittis, lacus eu eleifend bibendum, magna est elementum justo, non fringilla arcu neque non sem. Cras in erat fringilla, fringilla nisi eget, ornare mi. Integer congue facilisis fermentum. Etiam et suscipit arcu. Nulla ornare rutrum urna, et facilisis turpis blandit et. Vivamus facilisis ligula sed orci commodo molestie. Etiam in imperdiet metus, sed fermentum tellus. Fusce sapien metus, semper sed neque in, molestie posuere lacus. Donec vitae molestie turpis.";
    
    XFrame f = fra("Title");
    add(f, pan("Y", o("dim", a(50, 100)),
                    pan("Y", o("exp", a(true, false),
                               "pad", 10,
                               "back", "purple"),
                             txt("Hello World!", o("ali", "cen",
                                                   "siz", 50,
                                                   "b", true,
                                                   "col", "white"))),
                    lin(pan("Y", txt(txt, o("exp", true))))));
    set(f, o("resize", true));
    dsp(f);
  }
}