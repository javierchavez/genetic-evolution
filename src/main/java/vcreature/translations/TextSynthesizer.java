package vcreature.translations;

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


import vcreature.Being;
import vcreature.genotype.Genome;

import java.io.*;
import java.util.ArrayList;


/**
 * This class will read in objects from a text file that was created by the logger.
 */
public class TextSynthesizer extends Synthesizer<File, ArrayList<Being>>
{
  @Override
  public ArrayList<Being> encode(File typeToConvert)
  {
    ArrayList<Being> population = new ArrayList<>();
    BufferedReader br = null;


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
              Genome genome = new Genome();
              Being being = new Being();
              genome.read(sb);
              being.setGenotype(genome);
              population.add(being);

              sb.delete(0, sb.toString().length());
              //break;
              // return genome;

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

    return population;
  }

  @Override
  public String toString()
  {
    return null;
  }
}
