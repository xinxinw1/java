import tools.$;

import javax.swing.Timer;
import java.awt.event.*;

public class TimerTest {
  public static void main(String[] args) {
    ActionListener l = new ActionListener(){
      public void actionPerformed(ActionEvent evt){
        $.prn("whoa");
      }
    };
    Timer t1 = new Timer(0, l);
    t1.setRepeats(false);
    t1.start();
    
    String a = "";
    for (int i = 0; i < 5000; i++){
      a += i;
    }
  }
}
