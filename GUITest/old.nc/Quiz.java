	

    import static gui.X.add;
    import static gui.X.but;
    import static gui.X.dsp;
    import static gui.X.pad;
    import static gui.X.pan;
    import static tools.$.o;
     
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
     
    import javax.swing.ButtonGroup;
    import javax.swing.JButton;
    import javax.swing.JPanel;
    import javax.swing.JRadioButton;
     
    import gui.XFrame;
     
    //Created Apr 25, 2014 by Stephanie
     
    public class Quiz
    {
            //Variables
            JButton but_next;
            JPanel jp_radio;
            ActionListener al_radio;
     
            public Quiz()//this is the constructor
            {
                    start();
            }
     
            public void start()
            {
                    but_next = but("Next", o("onclick", new Runnable() //What happens "onclick" of the button?
                            {
                                    public void run()
                                    {
                                            //Do stuff
                                    }
                            }));
                   
                    al_radio= new ActionListener()
                    {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                    //What happens when one of the radio buttons is clicked?
                            }
                    };
                   
                    jp_radio = new JPanel();
                   
                    newRadio("steph", "is awesome"); //YES
                    newFrame("Quiz", 500, 600);
            }
     
           
            public void newRadio(String... s)//optional params, yay
            {
                    ButtonGroup g = new ButtonGroup();//group buttons together
                   
                    for(String t : s)//for-each loop
                    {
                            JRadioButton rb = new JRadioButton(t);
                            rb.addActionListener(al_radio);
                            g.add(rb);//add to group
                            jp_radio.add(rb);//add to radio panel
                    }
            }
     
            public void newFrame(String n, int w, int h)
            {
                    XFrame frame = new XFrame(n);
                    add(frame, //add contents onto the frame
                                    pad(5, //padding of 5px
                                                    pan("X", jp_radio,but_next)));//make panel, add radio panel and next button
     
                    dsp(frame);//display
            }
           
            public static void main(String[] args)
            {
                    new Quiz();//goes to constructor
            }
     
    }

