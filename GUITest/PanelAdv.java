import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

public class PanelAdv {
  public static void main(String[] args){
    edt(new Rn(){public void r(){gui();}});
  }
  
  public static void gui(){
    XFrame f = fra("Panels");
    
    add(f, pan("Y", o("alix", "cen",
                      "aliy", "top"),
                    txt("Hey!", o("siz", 50,
                                  "b", true)),
                    inp("Default"),
                    pan("X", o("exp", true,
                               "max", a(400, 200)),
                             pan("Y", o("exp", a(false, true),
                                        "aliy", "jus"),
                                      but("Hey!"),
                                      txt("Look here!")),
                             but("Enter", o("exp", true)),
                             pan("Y", o("exp", a(false, true),
                                        "aliy", "jus"),
                                      but("Hey!"),
                                      txt("Over here!")))));
    set(f, o("resize", true));
    dsp(f);
  }
}