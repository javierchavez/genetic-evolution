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
 */


import vcreature.utils.Savable;

import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Provides basic functions and describes how a gene should be. A gene is the
 * genotype representation of a block.
 *
 * @param <T> Type of gene being represented
 */
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
   * Clone the gene. This is a deep clone which clones objects within the gene as well
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
   * Whether this gene in active in the genome
   *
   * @return true | false
   */
  public boolean isActive()
  {
    return active;
  }

  /**
   * Turns a gene on or off in a genome
   *
   * @param active true | false
   */
  public void setActive(boolean active)
  {
    this.active = active;
  }

  /**
   * Get the edge to the genes this gene is connected to in the genome
   *
   * @return List of vertex indexes to other genes
   */
  public ArrayList<Integer> getEdges()
  {
    return edges;
  }

  /**
   * Adds an edge to the gene to connect it to another gene in the genome
   *
   * @param vertex the gene index in the genome to create an edge to
   */
  public void addEdge(int vertex)
  {
    edges.add(vertex);
  }

  /**
   * Remove and edge from the genes list
   *
   * @param vertex the gene index to remove from the edge list
   */
  public void removeEdge(int vertex)
  {
    edges.remove((Integer) vertex);
  }

  /**
   * Set the entire list of edges
   *
   * @param edges list of gene indexes to set as edges to the current gene
   */
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
  public void read(StringBuilder s)
  {
    String str = s.toString();
    String[] split = str.split("[|]");
    String[] split2 = split[1].split("[-]");
    for (int i = 0; i < split2.length; i++)
    {
      if (split2.length != 1)
      {
        int num = Integer.parseInt(split2[i]);
        addEdge(num);
      }
    }

    String output = str.replaceAll("\\|.*\\|,", "");
    s.replace(0, output.length(), output);
  }

  @Override
  public String toString()
  {
    return "|" + getGeneType().toString() + "|";
  }

}

