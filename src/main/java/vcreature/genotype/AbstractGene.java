package vcreature.genotype;

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


import vcreature.utils.Savable;

import java.util.ArrayList;


public abstract class AbstractGene<T extends AbstractGene<?>> implements Savable
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

  public void removeEdge(int vertex)
  {
    edges.remove((Integer) vertex);
  }

  public void setEdges(ArrayList<Integer> edges)
  {
    this.edges = edges;
  }

  @Override
  public void write(StringBuilder s)
  {
    s.append("|");
    for (Integer integer : getEdges())
    {
      s.append(integer).append("-");
    }
    s.append("|").append(",");
  }

  @Override
  public void read(String s)
  {

  }
}

