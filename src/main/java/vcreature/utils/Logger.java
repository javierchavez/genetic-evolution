package vcreature.utils;

/**
 * @author Javier Chavez
 * @author Alex Baker
 * @author Dominic Salas
 * @author Carrie Martinez
 * <p>
 * Date November 4, 2015
 * CS 351
 * Genetic Evolution
 */


import java.io.*;


/**
 * Class that helps with continual logging.
 * Writes objects out to a file so they can be read back
 * in a a later time
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
   * Defaults to saving data to temp.txt at project root
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
