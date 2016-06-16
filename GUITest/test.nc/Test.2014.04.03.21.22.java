import tools.$;
import static tools.$.o;

import gui.*;
import static gui.X.edt;
import static gui.X.fra;
import static gui.X.but;
import static gui.X.pan;
import static gui.X.dsp;
import static gui.X.set;
import static gui.X.add;
import static gui.X.pad;

import javax.swing.JButton;

public class Test {
  public static void main(String[] args){
    edt(new Runnable(){public void run(){
      gui();
    }});
  }
  
  public static void gui(){
    final XFrame f;
    JButton b1, b2, bclose, b3;
    
    f = fra("Title", pad(5, pan("Y", pan("Y", b1 = but("Button 1"),
                                              b2 = but("Button 2")),
                                     pan("X", bclose = but("Close"),
                                              b3 = but("Button 3")))));
    
    set(b1, o("onclick", new Runnable(){public void run(){
                           $.prn("clicked button 1");
                         }}));
    
    set(b2, o("onclick", new Runnable(){public void run(){
                           $.prn("clicked button 2");
                         }}));
    
    set(bclose, o("onclick", new Runnable(){public void run(){
                               $.prn("clicked button close");
                               f.close();
                             }}));
    
    set(b3, o("onclick", new Runnable(){public void run(){
                           $.prn("clicked button 3");
                         }}));
    
    set(f, o("onclose", new Runnable(){public void run(){
                          $.prn("closing");
                        }},
             "defbut", bclose));
    
    dsp(f);
  }
}