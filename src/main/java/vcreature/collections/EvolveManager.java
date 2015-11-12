package vcreature.collections;

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
import vcreature.utils.Savable;
import vcreature.utils.Statistics;

import java.util.Collections;


/**
 * This is the wrapper for actually splitting the large population up
 * into smaller ones so that threads can handle scaling.
 */
public class EvolveManager extends Thread implements Savable
{
  private Population population;
  private volatile boolean paused = true;
  private boolean running = true;
  private boolean isEvolving = false;
  private Boolean mutate = true;
  private Statistics statistics;

  /**
   * Create a evolution given a population
   *
   * @param population population that will be split into SubPopulations
   */
  public EvolveManager(Population population, Statistics statistics)
  {
    this.population = population;
    this.statistics = statistics;
  }


  public boolean isEvolving()
  {
    return isEvolving;
  }

  /**
   * Start the next generation in the sub population
   */
  public void cross()
  {
    synchronized (this)
    {
      if (!isEvolving)
      {
        isEvolving = true;


        if (mutate)
        {
          population.getMutating().evolve(population, this);
          interrupt();
          isEvolving = false;
          System.out.println("DONE HC");
        }
        else
        {
          population.getBreeding().evolve(population, this);
          interrupt();
          isEvolving = false;
          System.out.println("DONE Mating");
        }

        Collections.sort(population);
        double avg = statistics.getAverageFitness();

        for (int i = 0; i < population.size(); i++)
        {
          if ( (avg - population.get(i).getFitness())/ avg >= .65)
          {
            population.remove(i);
          }
        }

      }
    }
  }

  @Override
  public void run()
  {
    while (running)
    {
      synchronized (this)
      {
        if (Thread.interrupted())
        {
          paused = !paused;
        }

        if (!paused)
        {
          cross();
        }
      }
    }
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

  public void setMuting(boolean mutate)
  {
    this.mutate = mutate;
  }
}
