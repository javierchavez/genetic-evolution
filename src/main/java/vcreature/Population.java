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
 * <p>
 * Module description here
 */


import vcreature.Algorithms.GeneticAlgorithm;
import vcreature.Algorithms.HillClimbing;

import java.util.List;
import java.util.Vector;


/**
 * Class that handles the entire population... at every update
 * some number of beings are chosen and sent to the breeder to be
 * evolved
 *
 */
public class Population extends Vector<Being>
{
  private final Vector<Being> beings;

  private volatile int generations;
  private volatile float averageFitness;
  private volatile float bestFitness;

  private GeneticAlgorithm breeding;
  private HillClimbing mutating;

  // private volatile long lifetimeOffspring;
  // private volatile long lifetimeHillClimbs;
  // private volatile long currentRejectedCreatures;
  // private volatile long currentFailedHillClimbs;
  // private volatile long lifetimeRejectedCreatures;
  // private volatile long lifetimeFailedHillClimbs;
  // private boolean isEvolving = false;


  public Population(Vector<Being> beings, GeneticAlgorithm breeding, HillClimbing mutating)
  {
    this.beings = beings;
    this.breeding = breeding;
    this.mutating = mutating;
  }

  public Population(GeneticAlgorithm breeding, HillClimbing mutating)
  {
    this(new Vector<>(2001), breeding, mutating);
  }

  public Vector<Being> getBeings()
  {
    return beings;
  }

  public void setGenerations(int generations)
  {
    this.generations = generations;
  }

  public void setAverageFitness(float averageFitness)
  {
    this.averageFitness = averageFitness;
  }

  public void setBestFitness(float bestFitness)
  {
    this.bestFitness = bestFitness;
  }

  /**
   * This is called by the subpopulation threads. I have a check(isEvovling) just
   * in case we want to use it.
   *
   */
  public void update() {
    generations++;
    /*
      OR maybe this is where the population gets switched.

    if (!isEvolving)
    {
      isEvolving = true;

      for (Being being : beings)
      {
        logger.export(being.getGenotype());
      }

      breeding.evolvePopulation(beings, this);
    }
    */
  }

  public GeneticAlgorithm getBreeding()
  {
    return breeding;
  }

  public HillClimbing getMutating()
  {
    return mutating;
  }

  @Override
  public synchronized List<Being> subList(int fromIndex, int toIndex)
  {
    return beings.subList(fromIndex, toIndex);
  }

  @Override
  public synchronized Being remove(int index)
  {
    return beings.remove(index);
  }

  @Override
  public synchronized Being get(int index)
  {
    return beings.get(index);
  }

  @Override
  public synchronized int size()
  {
    return beings.size();
  }

  public int getGenerations()
  {
    return generations;
  }

  @Override
  public synchronized boolean add(Being being)
  {
    return this.beings.add(being);
  }

  public synchronized void replace(int i, Being being)
  {
    this.beings.removeElementAt(i);
    this.beings.add(i, being);
  }
}
