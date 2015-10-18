package vcreature.genotype;

import java.util.LinkedList;

/**
 * Genome is a collection of genes. Genetic representation of this morphology is a directed graph of
 * nodes and connections. More simply, genome is analogous
 * to a directed graph and Gene is the vertex, edges and weights are stored in the Gene.
 */
public class Genome
{

  private LinkedList<Gene<?>> genes;
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
    for (int i = start; i < end; i++)
    {
      genes.add(parent.getGenes().get(i).clone());
    }
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
  public LinkedList<Gene<?>> getGenes()
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

  public void linkRandomGeneTo(int geneIndex)
  {
    Gene<?> gene = genes.get(geneIndex);
    if (gene == null)
    {
      return;
    }

  }

  public void linkGenes(int gene1, int gene2, double linkWeight)
  {
    Gene<?> gene = genes.get(gene1);
    if (gene == null)
    {
      return;
    }
  }

  public void append(Gene gene)
  {
    genes.add(gene);
  }


}
