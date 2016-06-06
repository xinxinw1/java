import tools.*;
import static tools.$.*;
import gui.*;
import static gui.X.*;

public class Quiz {
  int curr = 0;
  int sel = -1;
  int scr = 0;
  boolean[] isAns;
  
  Xarr qs = xr(xr("435 + 563 = ?", a("623", "256", "998", "54"), 2),
               xr("1 + 2 + 3 + ... + 100 = ?", a("15363", "5050", "-346", "Nonexistant"), 1),
               xr("What is today's date?", a("May 11, 2014", "May 12, 2014", "Jan 5, 1997", "What?"), 1),
               xr("Really, really long question that is larger than the preferred size...", a("Works well!", "Fail...", "What?", "How does that work?"), 0));
  
  XFrame f;
  XPanel gpan;
  XText text, tscr;
  XButton check;
  XGroup curgrp = grp();
  
  public Quiz(){
    f = fra("Quiz Time!");
    
    def("but", o("back", "ygreen",
                 "col", "lygreen",
                 "font", "Arial",
                 "siz", 24,
                 "bor", null,
                 "exp", true),
        "rad", o("col", "dgrey",
                 "font", "Arial",
                 "siz", 24));
    
    XButton next = but(" > ", new Rn(){public void r(){nextQ();}});
    check = but(" Check ", o("w", 150), new Rn(){public void r(){check();}});
    XButton prev = but(" < ", new Rn(){public void r(){prevQ();}});
    
    isAns = new boolean[len(qs)];
    
    add(f, pan("Y", o("back", "lgrey",
                      "w", 400),
                    tscr = txt(o("siz", 18,
                                 "col", "dgrey")),
                    text = txt(o("siz", 30,
                                 "col", "dgrey")),
                    gpan = pan("Y", o("alix", "lef",
                                      "gap", 0)),
                    pan("X", o("exp", a(true, false)),
                             prev,
                             check,
                             next)));
    set(f, o("resize", true));
    dspq();
    dsp(f);
  }
  
  public void dspq(){
    sel = -1;
    text.setText(s(ref(qs, curr, 0)));
    check.setText(" Check ");
    rfscr(2);
    rem(gpan, curgrp);
    curgrp = radgrp();
    add(gpan, curgrp);
    dsp(f);
  }
  
  public XGroup radgrp(){
    String[] cs = sr(ref(qs, curr, 1));
    XRadio[] xr = new XRadio[len(cs)];
    for (int i = 0; i < len(cs); i++){
      final int c = i;
      Rn r = new Rn(){public void r(){
        sel = c;
      }};
      xr[i] = rad(cs[i], o("siz", 24), r);
    }
    return grp(xr);
  }
  
  public void check(){
    if (sel == -1)check.setText(" None selected. ");
    else if (sel == i(ref(qs, curr, 2))){
      check.setText(" Correct! ");
      if (!isAns[curr])rfscr(1);
      isAns[curr] = true;
    } else {
      check.setText(" Wrong! ");
      if (!isAns[curr])rfscr(0);
      isAns[curr] = true;
    }
    dsp(f);
  }
  
  public void nextQ(){
    if (curr == len(qs)-1)return;
    curr++;
    dspq();
  }
  
  public void prevQ(){
    if (curr == 0)return;
    curr--;
    dspq();
  }
  
  public void rfscr(int a){ //refresh score
	  if (a == 1)scr += 10;
	  else if (a == 0){
		  scr -= 10;
		  if (scr < 0)scr = 0;
	  }
	  tscr.setText("Score: "+scr);
  }
  
  public static void main(String[] args){
    edt(new Rn(){public void r(){ new Quiz(); }});
  }
}