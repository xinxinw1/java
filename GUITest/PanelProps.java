import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

public class PanelProps {
  public static void main(String[] args){
    edt(new Rn(){public void r(){gui();}});
  }
  
  public static void gui(){
    XFrame f = fra("Panels");
    
    add(f, pan("Y", o("max", maxint(),
                      "alix", "lef",
                      "aliy", "top"),
                    txt("Hey!"),
                    inp("Default"),
                    pan("X", o("exp", a(true, false)),
                             pan("Y", but("Hey!"),
                                      txt("Look here!")),
                             but("Enter", o("exp", true)),
                             pan("Y", but("Hey!"),
                                      txt("Over here!")))));
    set(f, o("resize", true));
    dsp(f);
  }
}