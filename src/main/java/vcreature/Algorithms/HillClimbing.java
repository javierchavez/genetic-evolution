package vcreature.Algorithms;

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
import vcreature.Population;
import vcreature.Subpopulation;
import vcreature.genotype.Gene;
import vcreature.genotype.Genome;
import vcreature.genotype.NeuralInput;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;


public class HillClimbing
{
  private final Environment environment;
  private Genome genome;
  private double amount;

  private double currentOptimizedFitness, currentFitnessValue;
  private int geneCount = 10, previousGeneValue, currentGeneValue;
  private Random rnd = new Random();

  private HashMap<NeuralInput.InputPosition, Integer> hillClimbMap = new HashMap<>();
  private HashMap<HillClimbStrategy.Strategies, Integer> hillClimbMapStrats = new HashMap<>();


  public HillClimbing (Environment environment)
  {
    this.environment = environment;

    hillClimbMap.put(NeuralInput.InputPosition.A, 1);
    hillClimbMap.put(NeuralInput.InputPosition.B, 1);
    hillClimbMap.put(NeuralInput.InputPosition.C, 1);
    hillClimbMap.put(NeuralInput.InputPosition.D, 1);
    hillClimbMap.put(NeuralInput.InputPosition.E, 1);

    hillClimbMapStrats.put(HillClimbStrategy.Strategies.EFFECTOR, 1);
    hillClimbMapStrats.put(HillClimbStrategy.Strategies.NEURON, 1);

  }

  /**
   * Pass in a being and iterate through that being testing if there are any
   * improvements to the fitness by making changes to the genes.
   * @param individual
   */
  private void hillClimbingEvaluation(Being individual)
  {
    HillClimbStrategy.Strategies currentStrategy = null;
    for (Gene gene : individual.getGenotype().getGenes())
    {
      HillClimbStrategy strategy;

      int x = rnd.nextInt(2);
      switch (x)
      {
        case 0:
          System.out.println("hill climbing on " + currentStrategy);
          currentStrategy = HillClimbStrategy.Strategies.EFFECTOR;
          strategy = new EffectorClimbStrategy<>();
          strategy.climb(gene.getEffector());
          break;
        case 1:
          System.out.println("hill climbing on " + currentStrategy);
          currentStrategy = HillClimbStrategy.Strategies.NEURON;
          strategy = new NeurlNetClimbStrategey<>();
          strategy.climb(gene.getEffector().getNeuralNet());
          break;
      }
      currentFitnessValue = individual.getFitness();
      environment.beginEvaluation(individual);
      while (true)
      {
        if (!individual.isUnderEvaluation())
        {
          break;
        }
      }
      currentOptimizedFitness = individual.getFitness();
      System.out.println("Before" + currentFitnessValue + " After " + currentOptimizedFitness);
      if(currentFitnessValue < currentOptimizedFitness)
      {
        hillClimbMapStrats.put(currentStrategy, 1);
      }

    }
  }

  /**
   * Will iterate through beings within the population and will returned
   * an evolved population.
   * @return and evolved population.
   */
  public Vector<Being> evolvePopulation (Subpopulation beings, Population population)
  {
    Vector<Being> current = new Vector<>();
    current.addAll(beings.getPopulation().getBeings());
    for (Being being : current)
    {
      hillClimbingEvaluation(being);
    }

    return new Vector<Being>();

  }



}
