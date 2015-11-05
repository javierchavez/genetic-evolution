package vcreature;

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


import vcreature.genotype.Genome;

import java.io.*;


/**
 * This class will read in objects from a text file that was created by the logger.
 */
public class TextSynthesizer extends Synthesizer<File, Genome>
{
  @Override
  public Genome encode(File typeToConvert)
  {
    BufferedReader br = null;
    Genome genome = new Genome();

    try
    {
      br = new BufferedReader(new FileReader(typeToConvert));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null)
      {
        if (line.equalsIgnoreCase("START"))
        {
          sb.append(line).append("\n");
          while (true)
          {
            line = br.readLine();
            sb.append(line).append("\n");

            if (line.equalsIgnoreCase("END"))
            {
              genome.read(sb);

              sb.delete(0, sb.toString().length());
              break;
            }
          }
        }
        line = br.readLine();
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        br.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    return null;
  }

  @Override
  public String toString()
  {
    return null;
  }
}
