package vcreature;

import java.util.Vector;


/**
 * Sub Population mainly to handle evolving creatures on different threads since
 * the update() method (see Population.update()) could take a long time to complete
 * if it does evolving can scale horizontally.
 *
 */
public class Subpopulation extends Thread
{


  private final Population population;
  public static int TOTAL_SUBPOPULATIONS = 0;
  private final Population totalPop;

  private volatile boolean paused = true;
  private boolean running = true;

  public Subpopulation(String name, Population population, int lowerBound, int upperBound)
  {
    this.setName(name);
    this.totalPop = population;
    this.population = new Population(new Vector<>(population.subList(lowerBound, upperBound)), population.getBreeding());


    TOTAL_SUBPOPULATIONS++;
  }

  public void nextGeneration()
  {
    synchronized (this)
    {
      population.update();
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
          nextGeneration();
        }
      }
    }
  }

  public int getGenerations()
  {
    return population.getGenerations();
  }

  public Being getBeing(int index)
  {
    return population.get(index);
  }

  public Population getPopulation()
  {
    return population;
  }

  public void addBeing(Being h)
  {
    population.add(h);
  }

  public int getSize()
  {
    return population.size();
  }
}
