package vcreature.Algorithms;

import vcreature.Being;
import vcreature.Population;
import vcreature.genotype.Gene;
import vcreature.genotype.Genome;
import vcreature.genotype.NeuralInput;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

/**
 * Created by dsalas on 10/25/15.
 */
public class HillClimbing
{
  private Genome genome;
  private double amount;
  private Population initialPopulation;
  private double currentOptimizedFitness, currentFitnessValue;
  private int geneCount = 10, previousGeneValue, currentGeneValue;
  private Random rnd = new Random();

  private HashMap<NeuralInput.InputPosition, Integer> hillClimbMap = new HashMap<>();

  public HillClimbing (Population population, HCMain hcMain)
  {
    this.initialPopulation = population;
    hillClimbMap.put(NeuralInput.InputPosition.A, 1);
    hillClimbMap.put(NeuralInput.InputPosition.B, 1);
    hillClimbMap.put(NeuralInput.InputPosition.C, 1);
    hillClimbMap.put(NeuralInput.InputPosition.D, 1);
    hillClimbMap.put(NeuralInput.InputPosition.E, 1);

  }

  public Population getInitialPopulation()
  {
    return initialPopulation;
  }

  /**
   * Pass in a being and iterate through that being testing if there are any
   * improvements to the fitness by making changes to the genes.
   * @param individual
   */
  private void hillClimbingEvaluation(Being individual)
  {
    LinkedList<Gene> genes = individual.getGenotype().getGenes();

    for (Gene gene : genes)
    {
      //Simple test to change attributes of gene
      int x = rnd.nextInt(6);
      switch (x)
      {
        //Increase height
        case 0:
          if (gene.getHeightY() + 0.5f <= 10.0f)
          {
            gene.setHeightY(gene.getHeightY() + 0.5f);
          }
          break;
        //Decrease height
        case 1:
          if (gene.getHeightY() - 0.5f >= 0.0f)
          {
            gene.setHeightY(gene.getHeightY() - 0.5f);
          }
          break;
        //Increase length
        case 2:
          if (gene.getLengthX() + 0.5f <= 10.0f)
          {
            gene.setLengthX(gene.getLengthX() + 1);
          }
          break;
        //Decrease length
        case 3:
          if (gene.getLengthX() - 0.5f >= 0.0f)
          {
            gene.setLengthX(gene.getLengthX() - 0.5f);
          }
          break;
        //Increase width
        case 4:
          if (gene.getWidthZ() + 0.5f <= 10.0f)
          {
            gene.setWidthZ(gene.getWidthZ() + 1);
          }
          break;
        //Decrease width
        case 5:
          if (gene.getWidthZ() - 0.5f >= 0.0f)
          {
            gene.setWidthZ(gene.getWidthZ() - 0.5f);
          }
          break;
      }
    }
  }

  /**
   * Will iterate through beings within the population and will returned
   * an evolved population.
   * @return and evolved population.
   */
  public Vector<Being> evolvePopulation ()
  {
    Vector<Being> currentGeneration= this.initialPopulation.getBeings();

    for (Being being : currentGeneration)
    {
      hillClimbingEvaluation(being);
    }

    return currentGeneration;
  }
}
