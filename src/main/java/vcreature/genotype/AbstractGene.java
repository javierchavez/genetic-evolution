package vcreature.genotype;


import java.util.ArrayList;

public abstract class AbstractGene<T extends AbstractGene<?>>
{
  private GeneType geneType;
  private boolean active;
  private ArrayList<Integer> edges;

  public AbstractGene(GeneType geneType)
  {
    this.geneType = geneType;
    this.active = true;
    edges = new ArrayList<>();
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

  public ArrayList<Integer> getEdges()
  {
    return edges;
  }

  public void addEdge(int vertex)
  {
    edges.add(vertex);
  }

  public void setEdges(ArrayList<Integer> edges)
  {
    this.edges = edges;
  }
}

