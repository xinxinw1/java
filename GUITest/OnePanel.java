import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

public class OnePanel {
  public static void main(String[] args){
    edt(new Rn(){public void r(){
      gui();
    }});
  }
  
  public static void gui(){
    XFrame f = fra("Panels");
    
    add(f, pan("Y", txt("Hey!"),
                    inp("Default"),
                    but("Enter")));
    set(f, o("resize", true));
    dsp(f);
  }
}