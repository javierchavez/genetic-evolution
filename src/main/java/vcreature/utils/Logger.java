package vcreature.utils;


import java.io.*;

public class Logger
{

  private String fileName = "temp.txt";
  // Assume default encoding.
  private File file;
  private StringBuilder stringBuilder = new StringBuilder();
  private BufferedWriter writer = null;

  public Logger()
  {
    file = new File(fileName);
    try
    {
      writer = new BufferedWriter(new FileWriter(file, true));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public synchronized void export(Savable s)
  {
    s.write(stringBuilder);

    try
    {
      writer.append(stringBuilder);
      writer.flush();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
