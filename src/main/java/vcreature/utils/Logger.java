package vcreature.utils;


import java.io.*;


/**
 * Class that helps with continual logging.
 *
 */
public class Logger
{
  private StringBuilder stringBuilder = new StringBuilder();
  private BufferedWriter writer = null;

  /**
   * Start the logger with a supplied filename.
   *
   * @param fileName name of file default path is proj root.
   */
  public Logger(String fileName)
  {
    File file = new File(fileName);
    try
    {
      writer = new BufferedWriter(new FileWriter(file, true));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Defaults to saving data to logger.txt at project root
   *
   */
  public Logger()
  {
    this("temp.txt");
  }

  /**
   * Export or Save a object to file.
   *
   * @param s Savable object
   */
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
