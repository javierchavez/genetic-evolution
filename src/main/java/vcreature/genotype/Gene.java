package vcreature.genotype;


import java.util.HashMap;
import java.util.Map;

public abstract class Gene<T extends Gene<?>>
{
  private HashMap<Integer, Double> linkedGeneWeights;
  private GeneType geneType;
  private boolean active;

  public Gene(GeneType geneType)
  {
    linkedGeneWeights = new HashMap<>();
    this.geneType = geneType;
    this.active = true;
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

  /**
   * Edge weights
   *
   * @return
   */
  public HashMap<Integer, Double> getLinkedGeneWeights()
  {
    return linkedGeneWeights;
  }

  /**
   * Set the weights
   *
   * @param linkedGeneWeights
   */
  public void setLinkedGeneWeights(HashMap<Integer, Double> linkedGeneWeights)
  {
    this.linkedGeneWeights = linkedGeneWeights;
  }


  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }


  public HashMap<Integer, Double> cloneLinkedGeneWeights()
  {
    HashMap<Integer, Double> newGeneLinkedWeights = new HashMap<>();
    for (Map.Entry<Integer, Double> entry : getLinkedGeneWeights().entrySet())
    {
      int index = entry.getKey();
      double weight = entry.getValue();
      newGeneLinkedWeights.put(index, weight);
    } return newGeneLinkedWeights;
  }

  @Override
  public String toString()
  {
    return "|" + getGeneType().toString() + "|" + linkedGeneWeights.toString();
  }
}

