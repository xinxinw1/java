import tools.$;

import java.util.*;

public class TimerTest {
  static String test = "";
  public static void main(String[] args) {
    TimerTask l1 = new TimerTask(){
      public void run(){
        $.prn("start 1 " + test);
        String local = test;
        String a = "";
        for (int i = 0; i < 500; i++){
          a += i;
        }
        test = local + "1";
        $.prn("end 1 " + test);
      }
    };
    
    TimerTask l2 = new TimerTask(){
      public void run(){
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
    Timer t1 = new Timer();
    t1.schedule(l1, 100, 100);
    
    Timer t2 = new Timer();
    t2.schedule(l2, 100, 100);
  }
}
