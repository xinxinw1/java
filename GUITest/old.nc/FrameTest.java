import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

import java.awt.*;

public class FrameTest {
  public static void main(String[] args){
    edt(new Rn(){public void r(){gui();}});
  }
  
  public static void gui(){
    final XFrame f = fra();
    f.pack();
    add(f, but("Hey There Delilah!"));
    $.prn(f.getContentPane());
    set(f, o("resize", true));
    dsp(f);
  }
}