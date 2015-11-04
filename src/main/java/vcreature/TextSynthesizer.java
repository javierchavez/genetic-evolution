package vcreature;


import vcreature.genotype.Genome;

import java.io.*;

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
          sb.append(line);
          while (true)
          {

            line = br.readLine();
            sb.append(line);

            if (line.equalsIgnoreCase("END"))
            {
              genome.read(sb.toString());

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
