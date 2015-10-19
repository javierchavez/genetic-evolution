package vcreature.genotype;


public abstract class AbstractGene<T extends AbstractGene<?>>
{
  private GeneType geneType;
  private boolean active;

  public AbstractGene(GeneType geneType)
  {
    this.geneType = geneType; this.active = true;
  }

  /**
   * Clone the gene.
   *
   * @return cloned gene
   */
  public abstract T clone();

  /**
   * Get the type of the gene
   *
   * @return type of the gene
   */
  public GeneType getGeneType()
  {
    return geneType;
  }


  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }


  @Override
  public String toString()
  {
    return "|" + getGeneType().toString() + "|";
  }
}

