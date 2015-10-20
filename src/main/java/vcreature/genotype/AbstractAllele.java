package vcreature.genotype;


public abstract class AbstractAllele<T extends AbstractAllele<?, ?>, V>
{
  private T alleleType;
  private V value;
  private boolean active;
  private float dominance;

  public AbstractAllele()
  {
    this.active = true;
  }


  public float getDominance()
  {
    return dominance;
  }

  public AbstractAllele setDominance(float dominance)
  {
    this.dominance = dominance;
    return this;
  }

  /**
   * Clone the allele.
   *
   * @return cloned allele
   */
  public abstract T clone();

  /**
   * Get the type of the allele
   *
   * @return type of the allele
   */
  public T getAlleleType()
  {
    return alleleType;
  }


  public boolean isActive()
  {
    return active;
  }

  public AbstractAllele setActive(boolean active)
  {
    this.active = active;
    return this;
  }


  @Override
  public String toString()
  {
    return "|" + getAlleleType().toString() + "|";
  }

  public V getValue()
  {
    return value;
  }

  public AbstractAllele setValue(V value)
  {
    this.value = value;
    return this;
  }

  public AbstractAllele setAlleleType(T alleleType)
  {
    this.alleleType = alleleType;
    return this;
  }
}