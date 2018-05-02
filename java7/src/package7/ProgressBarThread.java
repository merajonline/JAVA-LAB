package package7;

import javax.swing.JFrame;

import javax.swing.*;

import java.io.*;

class ProgressBarThread extends Thread
{
  private JFrame frame;
  private JProgressBar jp;
  private JTextField fromFile;
  private JTextField toFile;
  public boolean cancel = false;

  public ProgressBarThread(JFrame f, JProgressBar jp, 
                           JTextField from, JTextField to)
  {
    frame = f;                                // thread constructor
    this.jp = jp;
    fromFile = from;
    toFile = to;
  }

  public void run()
  {
    BufferedInputStream in = null;
    BufferedOutputStream out = null;
    try
    {
      File inFile = new File(fromFile.getText().trim());  // input stream
      in = new BufferedInputStream(new FileInputStream(inFile));

      File outFile = new File(toFile.getText().trim());   // output stream
      out =  new BufferedOutputStream(new FileOutputStream(outFile));

      long fileSize = in.available();        // input file size in bytes
      jp.setValue(0);                        // set up the progress bar
      jp.setMaximum(100);                    // indicator

      int r = 0;
      long bytesRead = 0;
      byte[] buffer = new byte[5];           // read/write buffer
      while ((r = in.read(buffer, 0, buffer.length)) != -1)
      {
        out.write(buffer, 0, r);             // write to the file     
        bytesRead += r;
        int copyProgress = (int) (bytesRead*100.0/fileSize);
        jp.setValue(copyProgress);           // update the indicator
        if (cancel) return;                  // kill the thread
      }
    }
    catch(FileNotFoundException e)
    {
      JOptionPane.showMessageDialog(frame, "File not found",
      "Error message", JOptionPane.ERROR_MESSAGE);
    }
    catch(IOException e)
    {
      JOptionPane.showMessageDialog(frame, "Cannot write to file",
      "Error message", JOptionPane.ERROR_MESSAGE);
    }
    finally                                  // close the files
    {
      try
      {
        if (in != null) in.close();
        if (out != null) out.close();
      }
      catch(Exception e)
      {
        JOptionPane.showMessageDialog(frame, "I/O error",
        "Error message", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}