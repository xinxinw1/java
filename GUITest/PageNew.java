import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

public class PageNew {
	public static void main(String[] args){
    edt(new Rn(){public void r(){gui();}});
  }
  
  public static void gui(){
    int lesson = 6;
    String file = "/data/" + lesson + ".txt";
		String[] l = lns(file);
    
    String tcol = l[0];
    String title = l[1];
    
    String t = joi(sli(l, 2), "\n");
    
    final XFrame f = fra(title);
    
    Table nav = o("back", "grey",
                  "col", "dgrey",
                  "font", "Arial",
                  "b", true,
                  "bor", null,
                  "exp", a(false, true));
    
    def("but", wit(nav, "w", 40,
                        "siz", 22),
        "inp", wit(nav, "w", 70,
                        "siz", 18,
                        "ali", "cen"));
    
    add(f, pan("Y", o("dim", a(700, 600),
                      "gap", 0),
                    pan("Y", o("h", 120,
                               "exp", a(true, false),
                               "maxw", maxint(),
                               "back", tcol),
                             txt(title, o("font", "Verdana",
                                          "siz", 36,
                                          "col", "white"))),
                    pan("Y", o("max", maxint(),
                               "back", "lgrey",
                               "alix", "lef",
                               "aliy", "top",
                               "pad", a(20, 25, 25, 25)),
                             txt(t, o("font", "Verdana",
                                      "siz", 14,
                                      "col", "#3c3c3c"))),
                    pan("X", o("h", 50,
                               "exp", a(true, false),
                               "maxw", maxint(),
                               "back", "dgrey",
                               "gap", 25,
                               "pad", 5),
                             but("<"),
                             inp("6"),
                             but(">"))));
    set(f, o("resize", true));
    dsp(f);
  }
}