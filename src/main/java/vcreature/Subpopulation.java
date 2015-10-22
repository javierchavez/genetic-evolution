package vcreature;

import java.util.List;

public class Subpopulation extends Thread
{

  private final List<Being> subpopulation;
  private final Population population;
  private static int TOTAL_SUBPOPULATIONS = 0;

  private volatile boolean paused = true;
  private boolean running = true;
  private int lowerBound;
  private int upperBound;

  public Subpopulation(String name, Population population, int lowerBound, int upperBound)
  {
    this.setName(name);
    this.population = population;
    this.upperBound = upperBound;
    this.lowerBound = lowerBound;
    this.subpopulation = population.subList(lowerBound, upperBound);
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
    return subpopulation.get(index);
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
    return subpopulation.size();
  }
}
