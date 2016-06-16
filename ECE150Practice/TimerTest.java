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
    t1.schedule(l, 100, 100);
    
    Scanner s = new Scanner(System.in);
    String str;
    do {
      str = s.nextLine();
      $.prn(str);
    } while (!str.equals("stop"));
    
    $.prn("here");
    t1.cancel();
    
    $.prn("done?");
  }
}
