import tools.*;
import static tools.$.o;
import static tools.$.a;

import gui.*;
import static gui.X.dj;
import static gui.X.fra;
import static gui.X.but;
import static gui.X.pan;
import static gui.X.dsp;
import static gui.X.set;
import static gui.X.add;
import static gui.X.pad;
import static gui.X.txt;
import static gui.X.col;
import static gui.X.lin;
import static gui.X.img;
import static gui.X.inp;
import static gui.X.dim;
import static gui.X.rad;
import static gui.X.grp;
import static gui.X.web;
import static gui.X.ytb;
import static gui.X.def;
import static gui.X.div;
import static gui.X.hor;

public class PageNew {
	public static void main(String[] args){
    dj(new Rn(){public void r(){
      gui();
    }});
  }
  
  public static void gui(){
    int lesson = 6;
    String file = "/content/" + lesson + ".txt";
		String[] l = $.lns(file);
    
    String tcol = l[0];
    String title = l[1];
    
    String t = $.joi($.sli(l, 2), "\n");
    
    final XFrame f = fra(title);
    
    Table buts = o("back", col("grey"),
                  "col", col("dgrey"),
                  "font", "Arial",
                  "b", true,
                  "bor", null);
    
    def("but", $.wit(buts, "dim", a(40, 40),
                           "size", 22),
        "inp", $.wit(buts, "dim", a(70, 40),
                           "size", 18,
                           "ali", "cen"));
    
    Table tit = o("dim", a(700, 120),
                  "back", col(tcol));
    
    Table tittxt = o("font", "Verdana",
                     "size", 36,
                     "col", col("white"));
    
    Table cnt = o("dim", a(700, 380),
                  "alix", "lef",
                  "aliy", "top");
    
    Table cnttxt = o("font", "Verdana",
                     "col", col("#3c3c3c"),
                     "pad", 10);
    
    Table nav = o("dim", a(700, 50),
                  "back", col("dgrey"),
                  "pad", 10);
    
    add(f, div(div(tit, txt(title, titsty)),
               div(cnt, txt(t, txtsty)),
               hor(nav, but("<"),
                        inp("6"),
                        but(">"))));
    dsp(f);
  }
}