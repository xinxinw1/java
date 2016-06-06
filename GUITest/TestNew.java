import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

public class TestNew {
  public static void main(String[] args){
    edt(new Rn(){public void r(){gui();}});
  }
  
  public static void gui(){
    final XFrame f = fra("Title");
    add(f, pan("Y", txt("Title"),
                    but("But 1", o("exp", true)),
                    but("But 2", o("exp", true)),
                    img("/math.jpg", 10, 10)));
    set(f, o("resize", true));
    dsp(f);
  }
}