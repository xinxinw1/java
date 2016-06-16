package test;

import gui2.*;
import tools.$;

import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class Test {
  public static void main(String[] args){
    UIManager.put("swing.boldMetal", Boolean.FALSE);
    SwingUtilities.invokeLater(new Runnable(){
      public void run(){
        showGUI();
      }
    });
  }
  
  public static void showGUI(){
    final XFrame frame = new XFrame("Title"); // final to access it in inner class
    frame.addWindowListener(new WindowListener(){
      public void windowClosing(WindowEvent e){
        $.prn("closing");
      }
      public void windowDeactivated(WindowEvent e){}
      public void windowActivated(WindowEvent e){}
      public void windowDeiconified(WindowEvent e){}
      public void windowIconified(WindowEvent e){}
      public void windowClosed(WindowEvent e){}
      public void windowOpened(WindowEvent e){}
    });
    
    Container pane = frame.getContentPane();
    
    XPanel panelBack = new XPanel("Y");
    X.pad(panelBack, 5);
    
    XPanel panel1 = new XPanel("Y");
    
    JButton button1 = new JButton("Button 1");
    button1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        $.prn("clicked button 1");
      }
    });
    panel1.add(button1);
    
    JButton button2 = new JButton("Button 2");
    button2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        $.prn("clicked button 2");
      }
    });
    panel1.add(button2);
    
    panelBack.add(panel1);
    
    XPanel panel2 = new XPanel("X");
    
    JButton buttonClose = new JButton("Close");
    buttonClose.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        $.prn("clicked button close");
        frame.close();
      }
    });
    frame.setDefaultButton(buttonClose);
    panel2.add(buttonClose);
    
    JButton button3 = new JButton("Button 3");
    button3.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        $.prn("clicked button 3");
      }
    });
    panel2.add(button3);
    
    panelBack.add(panel2);
    
    pane.add(panelBack);
    
    frame.pack();
    X.cen(frame);
    frame.setVisible(true);
  }
}