import tools.$;

import javax.swing.Timer;
import java.awt.event.*;
import java.util.Scanner;

public class TimerTest {
  public static void main(String[] args) {
    ActionListener l = new ActionListener(){
      public void actionPerformed(ActionEvent evt){
        $.prn("whoa");
      }
    };
    Timer t1 = new Timer(100, l);
    t1.setRepeats(true);
    t1.start();
    
    String a = "";
    for (int i = 0; i < 10000; i++){
      a += i;
    }
    $.prn("done?");
  }
}
