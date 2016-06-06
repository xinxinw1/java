import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

public class MorePanels {
  public static void main(String[] args){
    edt(new Rn(){public void r(){gui();}});
  }
  
  public static void gui(){
    XFrame f = fra("Panels");
    
    add(f, pan("Y", txt("Hey!"),
                    inp("Default"),
                    pan("X", pan("Y", but("Hey!"),
                                      txt("Look here!")),
                             but("Enter"),
                             pan("Y", but("Hey!"),
                                      txt("Over here!")))));
    set(f, o("resize", true));
    dsp(f);
  }
}