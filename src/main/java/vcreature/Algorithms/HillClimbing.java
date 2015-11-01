package vcreature.Algorithms;

import vcreature.Being;
import vcreature.Population;
import vcreature.genotype.Gene;
import vcreature.genotype.Genome;

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

  public HillClimbing (Population population, HCMain hcMain)
  {
    this.initialPopulation = population;
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
          gene.setHeightY(gene.getHeightY() + 1);
          break;
        //Decrease height
        case 1:
          gene.setHeightY(gene.getHeightY() - 1);
          break;
        //Increase length
        case 2:
          gene.setLengthX(gene.getLengthX() + 1);
          break;
        //Decrease length
        case 3:
          gene.setLengthX(gene.getLengthX() - 1);
          break;
        //Increase width
        case 4:
          gene.setWidthZ(gene.getWidthZ() + 1);
          break;
        //Decrease width
        case 5:
          gene.setWidthZ(gene.getWidthZ() - 1);
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
