package vcreature;


import vcreature.utils.Logger;

import java.io.File;
import java.util.ArrayList;


/**
 * This is the wrapper for actually splitting the large population up
 * into smaller ones so that threads can handle scaling.
 */
public class Evolution extends Thread
{

  private ArrayList<Subpopulation> subs;
  private Population population;
  private Logger logger = new Logger();

  /**
   * Create a evolution given a population
   *
   * @param population population that will be split into SubPopulations
   */
  public Evolution(Population population)
  {

    this.population = population;

    subs = new ArrayList<>();
    int chunkSize = 10;
    int numOfChunks = (int) Math.ceil((double) population.size() / chunkSize);

    // Given a list split into equal parts of 10.
    for (int i = 0; i < numOfChunks; i++)
    {
      int start = i * chunkSize;
      int length = Math.min(population.size() - start, chunkSize);

      subs.add(new Subpopulation(String.valueOf(i) + " population",
                                 population,
                                 start,
                                 length + start));
    }
    // Start all the SubPopulation threads
    for (Subpopulation subpopulation : subs)
    {
      subpopulation.start();
    }

  }

  public ArrayList<Subpopulation> getSubs()
  {
    return subs;
  }

  /**
   * Cross a population of creatures
   *
   * @param subpopulation index of SubPopulation
   */
  public void crossSubpopulation(int subpopulation)
  {
    for (Being being : subs.get(subpopulation).getPopulation().getBeings())
    {
      logger.export(being.getGenotype());
    }

    TextSynthesizer textSynthesizer = new TextSynthesizer();
    textSynthesizer.encode(new File("temp.txt"));

    subs.get(subpopulation).interrupt();
  }


  public int generations()
  {
    return population.getGenerations();
  }

  public Population getPopulation()
  {
    return population;
  }
  //
  //  public Being getBeing(int i)
  //  {
  //    return population.get(i);
  //  }


}
