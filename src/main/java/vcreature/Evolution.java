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


import vcreature.utils.Savable;

import java.util.ArrayList;


/**
 * This is the wrapper for actually splitting the large population up
 * into smaller ones so that threads can handle scaling.
 */
public class Evolution extends Thread implements Savable
{
  private ArrayList<Subpopulation> subs;
  private Population population;
  private int totalSubpopulations;
  private int totalSubpopulationAvg;
  private int totalSubpopulation;
  private float totalPopulation = 0;
  private float totalPopulationCurrent = 0;
  private float totalPopulationOld;

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

  /**
   * Get the sub-populations from the evolution
   *
   * @return a list of sub-populations
   */
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
    subs.get(subpopulation).interrupt();
  }

  /**
   * Get the number of generations in the population
   *
   * @return number of generations for the current population
   */
  public int generations()
  {
    return population.getGenerations();
  }

  public float fitnessChange()
  {

    totalPopulationCurrent = 0;
    for (Subpopulation sub : subs)
    {
      totalPopulationCurrent += sub.getPopulation().getBestFitness();
    }

    float newOld = totalPopulationCurrent - totalPopulationOld;

    return newOld;
  }


  public int totalBeings ()
  {
    int total = 0;
    for (Subpopulation sub : subs)
    {
      total += sub.getSize();
    }

    return total;
  }

  /**
   * Get the current population
   *
   * @return the current population in the evolution
   */
  public Population getPopulation()
  {
    return population;
  }
  //
  //  public Being getBeing(int i)
  //  {
  //    return population.get(i);
  //  }

  @Override
  public void write(StringBuilder s)
  {
    for (Being being : population.getBeings())
    {
      being.getGenotype().write(s);
    }
  }

  @Override
  public void read(StringBuilder s)
  {

  }
}
