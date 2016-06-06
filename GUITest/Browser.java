import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

public class Browser {
  public static void main(String[] args){
    dj(new Rn(){public void r(){gui();}});
  }
  
  public static void gui(){
    final XFrame f = fra("Browser");
    add(f, web("https://www.google.com/", 1000, 600, o("bars", true,
                                                       "exp", true)));
    set(f, o("resize", true));
    dsp(f);
  }
}