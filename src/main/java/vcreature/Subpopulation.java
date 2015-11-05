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


import java.util.Vector;


/**
 * Sub Population mainly to handle evolving creatures on different threads since
 * the update() method (see Population.update()) could take a long time to complete
 * if it does evolving can scale horizontally.
 */
public class Subpopulation extends Thread
{
  private final Population population;
  public static int TOTAL_SUB_POPULATIONS = 0;
  private final Population totalPop;

  private volatile boolean paused = true;
  private boolean running = true;
  private boolean isEvolving = false;

  /**
   * Create a sub-population from a larger population
   *
   * @param name name of the subpopulation
   * @param population population to generate the subpopulation
   * @param lowerBound lower index from the larger population to start at
   * @param upperBound upper index from the larger population to end at
   */
  public Subpopulation(String name, Population population, int lowerBound, int upperBound)
  {
    this.setName(name);
    this.totalPop = population;
    this.population = new Population(new Vector<>(population.subList(lowerBound, upperBound)), population.getBreeding(), population.getMutating());

    TOTAL_SUB_POPULATIONS++;
  }


  /**
   * Start the next generation in the sub population
   */
  public void nextGeneration()
  {
    synchronized (this)
    {
      if (!isEvolving)
      {
        isEvolving = true;
        totalPop.getBreeding().evolvePopulation(this, totalPop);
        //totalPop.getMutating().evolvePopulation(this, totalPop);

        isEvolving = false;
      }

      population.update();
    }
  }

  /**
   * Get the generations of the population
   *
   * @return number of generations
   */
  public int getGenerations()
  {
    return population.getGenerations();
  }

  /**
   * Get a being at the index from the population
   *
   * @param index index in the population
   * @return the being at index in the population
   */
  public Being getBeing(int index)
  {
    return population.get(index);
  }

  /**
   * Get the population this subpopulation came from
   *
   * @return the parent population
   */
  public Population getPopulation()
  {
    return population;
  }

  /**
   * add a being to the population
   *
   * @param h being to add to the population
   */
  public void addBeing(Being h)
  {
    population.add(h);
  }

  /**
   * Get the size (number of beings) of the population
   *
   * @return
   */
  public int getSize()
  {
    return population.size();
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
          nextGeneration();
        }
      }
    }
  }

}
