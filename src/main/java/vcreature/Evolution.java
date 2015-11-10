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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;


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
  private int totalPopulationCurrent = 0;
  private int totalPopulationOld = 0;
  private ArrayList<Integer> activeSubs = new ArrayList<>();
  private String bestFitness;
  private float lastBestFitAverage = 0f;

  /**
   * Create a evolution given a population
   *
   * @param population population that will be split into SubPopulations
   */
  public Evolution(Population population)
  {
    this.population = population;

    subs = new ArrayList<>();
    totalPopulationOld = totalPopulationCurrent = population.size();

    int chunkSize = 100;
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
    activeSubs.add(subpopulation);
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
    float a = (population.getBreeding().getBestFitness() - population.getBreeding().getFirstGenAvgFitness());// + (population.getMutating().getBestFitness() - population.getMutating().getGenAvgFitness());
    return a/2;
  }

  public float getActiveEvolvingFitness()
  {
    if (activeSubs.size() > 0)
    {
      return subs.get(activeSubs.get(0)).getPopulation().getAverageFitness();
    }
    return 0f;
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

  public Being getBest()
  {
    Collections.sort(population);
    return population.get(0);
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
    s.append(LocalDateTime.now()).append("\n");
    for (Being being : population.getBeings())
    {
      being.getGenotype().write(s);
    }
  }

  @Override
  public void read(StringBuilder s)
  {

  }

  public float getBestFitness()
  {
    float bestFit = 0f;
//    for (Subpopulation sub : subs)
//    {
//      bestFit = Math.max(sub.getPopulation().getBestFitness(), bestFit);
//    }


    return getPopulation().getBestFitness();
  }
}
