import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

public class Calc {
  public static void main(String[] args){
    dj(new Rn(){public void r(){gui();}});
  }
  
  public static void gui(){
    final XFrame f = fra("Calc");
    add(f, web("http://musiclifephilosophy.com/codes/apps/calculator/latest/calculator.html", 810, 520, o("exp", true)));
    set(f, o("resize", true));
    dsp(f);
  }
}