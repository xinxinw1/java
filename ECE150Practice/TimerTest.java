import tools.$;

import java.util.*;

public class TimerTest {
  public static void main(String[] args) {
    TimerTask l = new TimerTask(){
      public void run(){
        $.prn("whoa");
      }
    };
    Timer t1 = new Timer();
    t1.schedule(l, 1, 100);
    
    String a = "";
    for (int i = 0; i < 323; i++){
      a += i;
    }
    
    t1.cancel();
  }
}
