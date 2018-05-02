package package7;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;

public class ProgressBar extends JFrame implements ActionListener
{
  private JProgressBar jp = new JProgressBar();
  private JButton jb = new JButton("Copy");
  private JButton jc = new JButton("Cancel");
  private JTextField fromFile = new JTextField();
  private JTextField toFile = new JTextField();
  private ProgressBarThread thread;

  public static void main(String[] args)  // creating the frame application
  {
    ProgressBar application = new ProgressBar();
    application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    application.setTitle("Copy file");
    application.setSize(400, 180);
    application.setVisible(true); 
  }

  public ProgressBar()                    // class constructor
  {
    Container pane = getContentPane();    // building the GUI   
    pane.setLayout(new BorderLayout());
    jp.setStringPainted(true);            // display style of the progress bar
    pane.add(jp, BorderLayout.NORTH);

    JPanel panel1 = new JPanel(new BorderLayout());
    panel1.setBorder(new TitledBorder("From:"));
    panel1.add(fromFile, BorderLayout.CENTER);
 
    JPanel panel2 = new JPanel(new BorderLayout());
    panel2.setBorder(new TitledBorder("To:"));
    panel2.add(toFile, BorderLayout.CENTER);
 
    JPanel panel3 = new JPanel(new GridLayout(2,1));
    panel3.add(panel1);
    panel3.add(panel2);
    pane.add(panel3, BorderLayout.CENTER);

    JPanel panel4 = new JPanel();
    panel4.add(jb);
    panel4.add(jc);
    pane.add(panel4, BorderLayout.SOUTH);

    jb.addActionListener(this);
    jc.addActionListener(this);
    jc.setEnabled(false);
  }

  public void actionPerformed(ActionEvent e) // buttons events processing
  {
    if (e.getSource() == jb)
    {
      thread = new ProgressBarThread(this, jp, fromFile, toFile);
      thread.start();
      jb.setEnabled(false);
      jc.setEnabled(true);
    }
    else if (e.getSource() == jc)
    {
      thread.cancel = true;
      jc.setEnabled(false);
    } 
  }
}
