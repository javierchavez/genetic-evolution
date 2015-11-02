package vcreature;


import vcreature.Algorithms.GeneticAlgorithm;
import vcreature.genotype.GenomeGenerator;

import java.util.List;
import java.util.Vector;

/**
 * Vector because of synchronization
 */
public class Population extends Vector<Being>
{
  private final Vector<Being> beings;

  private volatile int generations;
  private volatile float averageFitness;
  private volatile float bestFitness;
  private volatile long lifetimeOffspring;
  private volatile long lifetimeHillClimbs;
  private volatile long currentRejectedCreatures;
  private volatile long currentFailedHillClimbs;
  private volatile long lifetimeRejectedCreatures;
  private volatile long lifetimeFailedHillClimbs;

  public Environment getEnvironment()
  {
    return environment;
  }

  private volatile Environment environment;
  GeneticAlgorithm breeding;

  public Population(Vector<Being> beings, Environment environment)
  {
    this.beings = beings;
    this.environment = environment;
    breeding = new GeneticAlgorithm(environment);
  }

  public Population(Environment environment)
  {
    this(new Vector<>(2001), environment);
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

  public void initPop()
  {
    for (int i = 0; i < 5; i++)
    {
      Being a = new Being();
      a.setGenotype(GenomeGenerator.init(environment).generateGenome());
      // a.setPhenotype(FlappyBird.class, environment);
      beings.add(i, a);
    }
  }

  public void update() {
    generations++;

    breeding.evolvePopulation(beings);

  }

  @Override
  public synchronized List<Being> subList(int fromIndex, int toIndex)
  {
    return beings.subList(fromIndex, toIndex);
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
}
