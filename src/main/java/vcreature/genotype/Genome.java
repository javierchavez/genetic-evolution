package vcreature.genotype;

import vcreature.utils.Savable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Genome is a collection of genes. Genetic representation of this morphology is a directed graph of
 * nodes and connections. More simply, genome is analogous
 * to a directed graph and AbstractGene is the vertex, edges and weights are stored in the AbstractGene.
 */
public class Genome implements Savable
{

  private LinkedList<Gene> genes;
  private Gene rootVertex;


  public Genome()
  {
    genes = new LinkedList<>();
  }



  /**
   * Merge one genome into this genome
   *
   * @param parent
   * @param start
   * @param end
   */
  public void merge(Genome parent, int start, int end)
  {
    int lastLink = genes.size()-1;
    for (int i = start; i < end; i++)
    {
      genes.add(parent.getGenes().get(i));
    }
    // get the new
    genes.get(lastLink).addEdge(lastLink+1);
  }

  public void append(Gene gene)
  {
    genes.add(gene);
  }

  public void remove(Gene gene)
  {
    for (Gene neighbor : neighbors(gene))
    {
      remove(neighbor);
    }

    int geneIdx = genes.indexOf(gene);
    for (Gene parent : genes)
    {
      parent.removeEdge(geneIdx);
    }
    genes.remove(gene);
  }

  /**
   * Root of the genome
   *
   * @return Gene that is the root of the Genome
   */
  public Gene getRoot()
  {
    if (rootVertex == null)
    {
      return genes.get(0);
    }
    return rootVertex;
  }


  /**
   * Set the root of the Genome
   *
   * @param root
   */
  public void setRoot(Gene root)
  {
    this.rootVertex = root;
  }

  /**
   * Get the genes
   *
   * @return List of Genes
   */
  public LinkedList<Gene> getGenes()
  {
    return genes;
  }

  /**
   * Size of the Genome
   * @return
   */
  public int size()
  {
    return genes.size();
  }

  public void linkGenes(int geneIndex1, int geneIndex2)
  {
    if (geneIndex1 < 0)
    {
      return;
    }

    AbstractGene<?> g1 = genes.get(geneIndex1);
    if (g1 == null)
    {
      return;
    }
    g1.addEdge(geneIndex2);
  }


  public List<Gene> neighbors (Gene current)
  {
    ArrayList<Integer> edges = current.getEdges();
    ArrayList<Gene> neighbors = new ArrayList<>();

    for (Integer integer : edges)
    {
      if (integer >= 0)
      {
        neighbors.add(genes.get(integer));
      }
    }
    return neighbors;
  }

  @Override
  public Genome clone()
  {
    Genome newGenome = new Genome();
    for (Gene gene : genes)
    {
      newGenome.append(gene.clone());
    }
    newGenome.setRoot(getRoot().clone());
    return newGenome;
  }

  @Override
  public void write(StringBuilder s)
  {
    for (Gene gene : genes)
    {
      gene.write(s);
      s.append("\n");
    }
  }

  @Override
  public void read(String s)
  {

  }
}
