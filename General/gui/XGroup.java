package gui;

import tools.$;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

public class XGroup extends ButtonGroup {
  private XRadio[] r;
  
  public XGroup(){
    super();
    r = new XRadio[0];
  }
  
  public void add(AbstractButton b){
    r = (XRadio[]) $.tai(r, b);
    super.add(b);
  }
  
  public XRadio[] elms(){
    return r;
  }
  
  public XRadio sel(){
    // return r[$.pos(getSelection(), $.map(.getModel, r))];
    ButtonModel[] ms = new ButtonModel[r.length];
    for (int i = 0; i < r.length; i++)ms[i] = r[i].getModel();
    int n = $.pos(getSelection(), ms);
    return (n == -1)?null:r[n];
  }
}