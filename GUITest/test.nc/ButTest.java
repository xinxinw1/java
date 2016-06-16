import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

public class ButTest {
  public static void main(String[] args){
    edt(new Rn(){public void r(){
      gui();
    }});
  }
  
  public static void gui(){
    XFrame f = fra("Title");
    add(f, pan("Y", o("back", "green"),
                    but("Hey!!!", o("back", "#FFFFFF55",
                                    "hover", "#FFFFFFA0",
                                    "press", "#FFFFFF30",
                                    "bor", null,
                                    "pad", a(5, 15)))));
    dsp(f);
  }
}