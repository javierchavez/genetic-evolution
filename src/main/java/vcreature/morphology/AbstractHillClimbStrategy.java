package vcreature.morphology;


import vcreature.genotype.Genome;

public abstract class AbstractHillClimbStrategy<T extends Genome,V>  implements HillClimbStrategy<T,V>, Comparable<AbstractHillClimbStrategy>
{
  public float WEIGHT = 1f;

  AbstractHillClimbStrategy(float w)
  {
    this.WEIGHT = w;
  }

  @Override
  public int compareTo(AbstractHillClimbStrategy o)
  {
    // this is intentionally opposite
    if (o.WEIGHT == this.WEIGHT)
      return 0;
    if (this.WEIGHT < o.WEIGHT)
      return 1;
    else
    return -1;

  }

}
