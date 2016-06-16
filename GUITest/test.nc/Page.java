import tools.*;

import gui.*;
import static gui.X.col;
import static gui.X.dj;
import static gui.X.fra;
import static gui.X.add;
import static gui.X.dsp;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Page extends JPanel {
  public static void main(String[] args){
    dj(new Rn(){public void r(){
      gui();
    }});
	}
  
  public static void gui(){
    XFrame f = fra("Page");
    add(f, new Page(6));
    dsp(f);
  }
  
	private int i_lesson;
	private String s_title;
	private String tcol; // title color
	private Title t_title;
	private Text t_text;
	private JScrollPane sp_text;
	private JPanel p_title, p_text, p_vid, p_footer;
	private Btn b_prev, b_next;
	private JTextField tf_pg;

	public Page(int l)
	{
		this.setLayout(null);
		this.setBackground(col("lgrey"));
		this.i_lesson = l;
		setContent(getContent());
	}

	public String getContent(){
		String s_file = "/content/" + i_lesson + ".txt";
		String s_text = "";
		boolean isList = false;
    
    String[] l = $.lns(s_file);
    
    tcol = l[0];
    s_title = l[1];
    
    String t;
    for (int i = 2; i < l.length; i++){
      t = l[i];
      if ($.beg(t, "<list>")){
        s_text += "<ul>";
        isList = true;
      } else if ($.beg(t, "</list>")){
        s_text += "</ul>";
        isList = false;
      } else if (isList){
				s_text += "<li>" + t + "</li>";
      } else {
				s_text += "<p>" + t + "</p>";
      }
    }
		return format(s_text);
	}

	public void setContent(String s_text){
		t_text = new Text(s_text);
		t_title = new Title(36, "white", s_title);

		sp_text = new JScrollPane(t_text);
		sp_text.setBorder(BorderFactory.createEmptyBorder());
		sp_text.setSize(700 - 30 - 30, 600 - 120 - 50 - 20 - 20);
		sp_text.setLocation(30, 140);

		p_title = new Content(700, 120, 0, 0, tcol);
		p_vid = new JPanel();
		p_footer = new Content(700, 50, 0, 550, "dgrey");

		b_prev = new Btn(40, tcol, "prev");
		b_next = new Btn(40, tcol, "next");

		tf_pg = new JTextField();
		tf_pg.setSize(70, 40);
		tf_pg.setBackground(col("grey"));
		tf_pg.setLocation((700 - 70) / 2, (50 - 40) / 2);
		tf_pg.setBorder(null);
		tf_pg.setHorizontalAlignment(JTextField.CENTER);
		tf_pg.setFont(new Font("Arial", Font.BOLD, 18));
		tf_pg.setText("" + i_lesson);

		a(a(p_title, t_title), sp_text, a(p_footer, b_prev, tf_pg, b_next));
	}

	public void a(JComponent... c)
	{
		for (JComponent j : c)
			this.add(j);
	}

	public JComponent a(JPanel a, JComponent... c)
	{
		for (JComponent j : c)
			a.add(j);
		return a;
	}

	public String format(String a)
	{

		a = "<html><body style=\"font-family:Verdana; color:#3c3c3c\">" + a + "</body></html>";
		return a;
	}

	

}

class Content extends JPanel
{
	public Content(int w, int h, int x, int y, String col)
	{
		this.setLayout(null);
		this.setSize(w, h);
		this.setLocation(x, y);
		this.setBackground(col(col));
	}
}

class Text extends JTextPane
{
	public Text(String t)
	{
		this.setEditable(false);
		this.setLocation(0, 0);
		this.setContentType("text/html");
		this.setBackground(col("lgrey"));
		this.setText(t);
		this.setSize(640, 80);
		this.setBorder(BorderFactory.createEmptyBorder());
	}

}

class Title extends JTextField
{
	public Title(int s, String col, String t)
	{
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setEditable(false);
		this.setBorder(null);
		this.setLocation(30, 20);
		this.setForeground(col("white"));
		this.setBackground(null);
		this.setFont(new Font("Verdana", Font.PLAIN, s));
		this.setText(t);
		this.setSize(640, 80);
	}
}

class Btn extends JButton
{
	public Btn(int s, String c, String n)
	{
		if (n.equals("prev"))
		{
			this.setText("<");
			this.setLocation((700 - s) / 2 - 80, (50 - s) / 2);
		}
		else
		{
			this.setText(">");
			this.setLocation((700 - s) / 2 + 80, (50 - s) / 2);
		}
		this.setSize(s, s);
		this.setBackground(col("grey"));
		this.setBorder(null);
		this.setForeground(col("dgrey"));
		this.setFont(new Font("Arial", Font.BOLD, 22));
	}
}