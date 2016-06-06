import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

public class HelloWorld {
  public static void main(String[] args){
    edt(new Rn(){public void r(){
      gui();
    }});
  }
  
  public static void gui(){
    XFrame f = fra("Title");
    add(f, txt("Hello World!", o("ali", "cen")));
    dsp(f);
  }
}