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


import vcreature.genotype.Genome;


/**
 * Mainly to house the Genome and data about the Being.
 *
 */
public class Being /*Comparable<Being>*/
{

  public static int TOTAL = 0;
  // private String name;
  // private int age;
  private Genome genotype;
  private int timesHillClimbed;
  private int timesBred;
  private int children;
  private float fitness = 0;
  private boolean underEvaluation = false;


  public Being()
  {
    TOTAL++;
  }

  public Being(Genome bird)
  {
    this.genotype = bird;
    TOTAL++;
  }

  public synchronized boolean isUnderEvaluation()
  {
    return underEvaluation;
  }

  public synchronized void setUnderEvaluation(boolean underEvaluation)
  {
    this.underEvaluation = underEvaluation;
  }

  public Genome getGenotype()
  {
    return genotype;
  }

  public void setGenotype(Genome genotype)
  {
    this.genotype = genotype;
  }

  public float getFitness()
  {
    return fitness;
  }

  public void setFitness(float fitness)
  {
    this.fitness = fitness;
  }


  @Override
  public Being clone()
  {
    Being newBeing = new Being();
    newBeing.setGenotype(genotype.clone());
    newBeing.setFitness(fitness);
    return newBeing;
  }
}
