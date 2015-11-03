package vcreature.utils;


import java.io.*;

public class Logger
{

  String fileName = "temp.txt";
  // Assume default encoding.
  File file;

  public Logger()
  {
    file = new File(fileName);
  }

  public synchronized void export(Savable s)
  {
    // Always wrap FileWriter in BufferedWriter.

    //    BufferedWriter bufferedWriter
    StringBuilder stringBuilder = new StringBuilder();
    s.write(stringBuilder);
    BufferedWriter writer = null;
    try
    {
      writer = new BufferedWriter(new FileWriter(file));
      writer.append(stringBuilder);
      writer.flush();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
