package vcreature;


import vcreature.genotype.Genome;

/**
 * Wrapper for Genome and Creature
 *
 * The population will consist of many Beings
 */
public class Being /*Comparable<Being>*/
{

  private static int totalBeings = 0;

  private String name;
  private int age;
  private Genome genotype;
  private int timesHillClimbed;
  private int timesBred;
  private int children;
  private float fitness = 0;
  private boolean underEvaluation = false;


  public Being()
  {
  }

  public Being(Genome bird)
  {
    this.genotype = bird;
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
