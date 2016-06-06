import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

public class Panels {
  public static void main(String[] args){
    edt(new Rn(){public void r(){
      gui();
    }});
  }
  
  public static void gui(){
    String txt = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sagittis, lacus eu eleifend bibendum, magna est elementum justo, non fringilla arcu neque non sem. Cras in erat fringilla, fringilla nisi eget, ornare mi. Integer congue facilisis fermentum. Etiam et suscipit arcu. Nulla ornare rutrum urna, et facilisis turpis blandit et. Vivamus facilisis ligula sed orci commodo molestie. Etiam in imperdiet metus, sed fermentum tellus. Fusce sapien metus, semper sed neque in, molestie posuere lacus. Donec vitae molestie turpis.";
    
    
    final XFrame f = fra("Panels");
    
    XButton clos = but("Close", new Rn(){public void r(){
                                  f.close();
                                }});
    
    add(f, pan("Y", o("dim", a(600, 500),
                      "gap", 0),
                    pan("Y", o("exp", a(true, false),
                               "maxw", maxint(),
                               "pad", 10,
                               "back", "purple"),
                             txt("Hello World!", o("ali", "cen",
                                                   "siz", 50,
                                                   "b", true,
                                                   "col", "white"))),
                    pan("Y", o("aliy", "top",
                               "pad", a(3, 7, 7, 7)),
                             txt(txt, o("ali", "jus",
                                        "siz", 20,
                                        "exp", true))),
                    pan("X", o("exp", a(true, false),
                               "maxw", maxint(),
                               "pad", 5,
                               "back", "dgrey"),
                             clos, but("Reset"))));
    set(f, o("resize", true));
    dsp(f);
  }
}