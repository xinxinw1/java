import tools.$;

import java.util.*;
import javax.swing.Timer;
import java.awt.event.*;

public class TimerTest {
  static String test = "";
  public static void main(String[] args) {
    ActionListener l1 = new ActionListener(){
      public void actionPerformed(ActionEvent evt){
        $.prn("start 1 " + test);
        String local = test;
        String a = "";
        for (int i = 0; i < 20000; i++){
          a += i;
        }
        test = local + "1";
        $.prn("end 1 " + test);
      }
    };
    
    ActionListener l2 = new ActionListener(){
      public void actionPerformed(ActionEvent evt){
        $.prn("start 2 " + test);
        String local = test;
        String a = "";
        for (int i = 0; i < 100; i++){
          a += i;
        }
        test = local + "2";
        $.prn("end 2 " + test);
      }
    };
    Timer t1 = new Timer(100, l1);
    t1.setRepeats(true);
    t1.start();
    
    Timer t2 = new Timer(100, l2);
    t2.setRepeats(true);
    t2.start();
    
    while (true);
  }
}
