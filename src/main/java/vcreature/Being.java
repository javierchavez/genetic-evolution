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


import vcreature.genotype.Genome;


/**
 * Mainly to house the Genome and data about the Being.
 * This class describes an object which gets evolved in GA
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

  /**
   * Create a new being and update the total number of beings
   */
  public Being()
  {
    TOTAL++;
  }

  /**
   * Create a being with the given genotype
   *
   * @param bird genome to initialize the being to
   */
  public Being(Genome bird)
  {
    this.genotype = bird;
    TOTAL++;
  }

  /**
   * Returns whether this being is being evaluated
   *
   * @return true | false depending on whether the being in under evaluation
   */
  public synchronized boolean isUnderEvaluation()
  {
    return underEvaluation;
  }

  /**
   * Set whether the being is under evaluation or not
   *
   * @param underEvaluation true | false to set the evaluation state of the being
   */
  public synchronized void setUnderEvaluation(boolean underEvaluation)
  {
    this.underEvaluation = underEvaluation;
  }

  /**
   * Get the genotype of the being
   *
   * @return current genotype of the being
   */
  public Genome getGenotype()
  {
    return genotype;
  }

  /**
   * Set the genotype of the being to a new genotype
   *
   * @param genotype new genotype to apply to the being
   */
  public void setGenotype(Genome genotype)
  {
    this.genotype = genotype;
  }

  /**
   * Get the fitness of the being
   *
   * @return the fitness of this being
   */
  public float getFitness()
  {
    return fitness;
  }

  /**
   * set the fitness for the being
   *
   * @param fitness fitness to apply to the being
   */
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
