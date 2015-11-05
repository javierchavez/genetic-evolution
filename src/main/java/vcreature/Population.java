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


import vcreature.Algorithms.GeneticAlgorithm;
import vcreature.Algorithms.HillClimbing;

import java.util.List;
import java.util.Vector;


/**
 * Class that handles the entire population... at every update
 * some number of beings are chosen and sent to the breeder to be
 * evolved
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

  /**
   * Creates a population from the given vector of beings. Also takes algorithms
   * for mutating and breeding
   *
   * @param beings A vector of beings to init the population too
   * @param breeding A genetic algorithm which spcifies how beings are bred
   * @param mutating A hill cliimbing algorithm to mutate the beings
   */
  public Population(Vector<Being> beings, GeneticAlgorithm breeding, HillClimbing mutating)
  {
    this.beings = beings;
    this.breeding = breeding;
    this.mutating = mutating;
  }

  /**
   * Creates a population with algorithms
   * for mutating and breeding the population
   *
   * @param breeding A genetic algorithm which spcifies how beings are bred
   * @param mutating A hill cliimbing algorithm to mutate the beings
   */
  public Population(GeneticAlgorithm breeding, HillClimbing mutating)
  {
    this(new Vector<>(2001), breeding, mutating);
  }

  /**
   * Get the beings in the population
   *
   * @return a vector of beings
   */
  public Vector<Being> getBeings()
  {
    return beings;
  }

  /**
   * Set the generations for the population
   *
   * @param generations number of generations to apply to the population
   */
  public void setGenerations(int generations)
  {
    this.generations = generations;
  }

  /**
   * Set the average fitness of the population
   *
   * @param averageFitness average fitness to apply to the population
   */
  public void setAverageFitness(float averageFitness)
  {
    this.averageFitness = averageFitness;
  }

  /**
   * Set the best fitness for the population
   *
   * @param bestFitness the best fitness to apply to the population
   */
  public void setBestFitness(float bestFitness)
  {
    this.bestFitness = bestFitness;
  }

  /**
   * This is called by the subpopulation threads. I have a check(isEvovling) just
   * in case we want to use it.
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

  /**
   * Get the breeding used in the population
   *
   * @return the breeding (genetic) algorithm
   */
  public GeneticAlgorithm getBreeding()
  {
    return breeding;
  }

  /**
   * Get the mutation algorithm in the population
   *
   * @return the mutating (hill climbing) algorithm
   */
  public HillClimbing getMutating()
  {
    return mutating;
  }

  /**
   * Get the number of generations in the population
   *
   * @return number of generations
   */
  public int getGenerations()
  {
    return generations;
  }

  /**
   * Replace a being at index i with a new being
   *
   * @param i index to replace a being
   * @param being new being to insert at index i
   */
  public synchronized void replace(int i, Being being)
  {
    this.beings.removeElementAt(i);
    this.beings.add(i, being);
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

  @Override
  public synchronized boolean add(Being being)
  {
    return this.beings.add(being);
  }
}
