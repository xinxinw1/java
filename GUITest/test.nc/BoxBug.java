import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;

public class BoxBug {
  public static void main(String[] args){
    UIManager.put("swing.boldMetal", Boolean.FALSE);
    SwingUtilities.invokeLater(new Runnable(){
      public void run(){
        gui();
      }
    });
  }
  
  public static void gui(){
    JFrame f = new JFrame("Title");
    Box b = new Box(BoxLayout.Y_AXIS);
    
    JComponent c = new JComponent(){
      public void paint(Graphics g){
        g.setColor(new Color(255, 0, 0));
        g.fillRect(0, 0, getWidth(), getHeight());
      }
      
      // just change the first argument here
      // (even numbers work fine, odd ones fail)
      private Dimension p = new Dimension(3, 20);
      public Dimension getPreferredSize(){return p;}
      public Dimension getMinimumSize(){return p;}
      public Dimension getMaximumSize(){return p;}
    };
    c.setAlignmentY(Component.TOP_ALIGNMENT);
    b.add(c);
    
    f.add(b);
    f.pack();
    f.setVisible(true);
  }
}