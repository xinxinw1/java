import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

public class Test {
  public static void main(String[] args){
    dj(new Rn(){public void r(){gui();}});
  }
  
  public static void gui(){
    final XFrame f = fra("Title");
    
    XButton b1 = but("Button 1", o("exp", true), new Rn(){public void r(){
                                   $.prn("clicked button 1");
                                 }});
    XButton b2 = but("Button 2", o("exp", true), new Rn(){public void r(){
                                   $.prn("clicked button 2");
                                 }});
    XButton bclose = but("Close", new Rn(){public void r(){
                                    $.prn("clicked button close");
                                    f.close();
                                  }});
    XButton b3 = but("Button 3", new Rn(){public void r(){
                                   $.prn("clicked button 3");
                                 }});
    
    // hack to get around final requirement for use in inner class
    final XInput[] in = {null};
    final XGroup[] rs = {null};
    
    XPanel p;
    
    add(f, p= pan("Y", o("pad", 5),
                    pan("X", o("aliy", "bot"),
                             inp("Text", o("exp", true)),
                             inp("Text"),
                             lin(pan("Y", o("alix", "lef",
                                            "gap", 0,
                                            "exp", true), b1, b2)),
                             txt("Hey", o("font", "Courier",
                                          "siz", 8,
                                          "ali", "rig")),
                             web("http://musiclifephilosophy.com", 500, 100)),
                    /*pan("Y", o("alix", "lef"),
                             but("Test"),
                             but("Test223")),
                    img("/male-elk.jpg", 300, 300),*/
                    in[0] = inp("Default", o("ali", "rig",
                                             "i", true,
                                             "b", true)),
                    rs[0] = grp(rad("Test", new Rn(){public void r(){
                                              $.prn("selected Test");
                                            }}),
                                rad("T2", new Rn(){public void r(){
                                            $.prn("selected T2");
                                          }}),
                                rad("hey!", new Rn(){public void r(){
                                              $.prn("selected hey!");
                                            }})),
                    but("Test", o("exp", true)),
                    txt("Hey!\ntest\n\ntest2",
                        o("ali", "cen",
                          "col", "blue",
                          "i", true)),
                    ytb("b-Cr0EWwaTk", 400, 300),
                    pan("X", bclose, b3)));
    
    set(f, o("onclose", new Rn(){public void r(){
                          $.prn("closing");
                          $.prn("input had $1", in[0].getText());
                          XRadio r = rs[0].sel();
                          $.prn("radio had $1", $.nulp(r)?"none":r.getText());
                        }},
             "defbut", bclose,
             "resize", true));
    dsp(f);
  }
}