package vcreature.morphology;

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


import vcreature.Being;
import vcreature.Environment;
import vcreature.collections.EvolveManager;
import vcreature.genotype.Gene;
import vcreature.utils.Statistics;

import java.util.*;


public class HillClimb
{
  private final Environment environment;
  private Statistics stats;
  private long iterations;

  private float lowest = 0;
  private float highest = 7;
  public static int fails = 0;

  private double currentOptimizedFitness, currentFitnessValue;


  private int lifetimeRuns = 0;
  private ArrayList<Being> kill = new ArrayList<>();
  private PriorityQueue<AbstractHillClimbStrategy> strategies = new PriorityQueue<>();


  public HillClimb(Environment environment)
  {
    this.environment = environment;

    strategies.add(new RootGeneDimensionClimbStrategy<>(1f));
    strategies.add(new LimbGeneDimensionClimbStrategy<>(.80f));
    strategies.add(new EffectorClimbStrategy(.30f));
    strategies.add(new NeuralNetClimbStrategy(.20f));
    strategies.peek();

  }

  /**
   * Pass in a being and iterate through that being testing if there are any
   * improvements to the fitness by making changes to the genes.
   * @param individual
   */

  private boolean hillClimbingEvaluation(Being individual)
  {
    AbstractHillClimbStrategy currentStrategy = null;
    float currentFitneesBeing = individual.getFitness();
    int tries = 0;


    for (int i = 0; i < individual.getGenotype().size(); i++)
    {
      iterations++;
      tries++;
      lifetimeRuns++;
      lowest = Math.min(individual.getFitness(), lowest);
      highest = Math.max(individual.getFitness(), highest);

      /*TODO add gene to strategy*/
      Gene gene= individual.getGenotype().get(i);

      // get a strategy from the queue.
      currentStrategy = strategies.poll();

      // call the strategy
      currentStrategy.climb(individual.getGenotype(), gene);

      System.out.println(currentStrategy.toString());

      currentFitnessValue = individual.getFitness();
      environment.beginEvaluation(individual);
      // test it
      while (true)
      {
        if (!individual.isUnderEvaluation())
        {
          break;
        }
      }


      currentOptimizedFitness = individual.getFitness();

      stats.addFitnessToSum(((float) currentOptimizedFitness));

      // If the being is still failing to jump, get rid of it.
      if (currentFitnessValue < 1 && currentOptimizedFitness < 1)
      {
        if (tries > 4)
        {
          // kill.add(individual);
          // System.out.println("Killing individual");f
          break;
        }
      }

      // if this was a bad mutation
      if (currentFitnessValue >= currentOptimizedFitness)
      {
        float factor = .10f;
        // if it is now worse than the worst being, set the factor higher
        if (currentFitnessValue <= lowest)
        {
          factor = .30f;
          fails++;
        }
        currentStrategy.WEIGHT =- factor;
        strategies.add(currentStrategy);
        // if this creature is not improving then escape
        if (tries > 4)
        {
          System.out.println("Local min");
          break;
        }
      }
      else // this is a good mutation
      {
        float factor = .10f;
        // if the being is now the best
        if (currentOptimizedFitness >= highest)
        {
          factor = .55f;
          stats.setBestBeing(individual);
        }

        currentStrategy.WEIGHT += factor;
        strategies.add(currentStrategy);

        // if this creature is not improving then escape
        if (tries > 4 && currentOptimizedFitness <= highest)
        {
          System.out.println("Local max");
          break;
        }
        // try the mutation again!!!!!
        --i;
      }

    }



    System.out.println("Individual done improved by " + (currentOptimizedFitness - currentFitneesBeing));
    if (currentStrategy != null)
    {
      strategies.add(currentStrategy);
    }
    return false;
  }

  /**
   * Will iterate through beings within the population and will returned
   * an evolved population.
   * @return and evolved population.
   */

  public boolean evolve(ArrayList<Being> beings, EvolveManager evolveManager)
  {

    // this is morphing the population straight away.
    for (int i = 0; i < beings.size(); i++)
    {
      hillClimbingEvaluation(beings.get(i));

      if ( stats.getBestFitness()/(stats.getAverageFitness()/iterations) <=  .15)
      {
        System.out.println("Activating GA");
        stats.addGenerationToSum(1);
        evolveManager.setMuting(false);
        return false;
      }
    }
    stats.addGenerationToSum(1);

    return true;
  }

  public void setDataHandler(Statistics dataHandler)
  {
    this.stats = dataHandler;
  }
}
