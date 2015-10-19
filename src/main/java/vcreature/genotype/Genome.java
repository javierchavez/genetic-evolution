package vcreature.genotype;

import java.util.LinkedList;

/**
 * Genome is a collection of abstractGenes. Genetic representation of this morphology is a directed graph of
 * nodes and connections. More simply, genome is analogous
 * to a directed graph and AbstractGene is the vertex, edges and weights are stored in the AbstractGene.
 */
public class Genome
{

  private LinkedList<AbstractGene<?>> abstractGenes;
  private AbstractGene rootVertex;


  public Genome()
  {
    abstractGenes = new LinkedList<>();
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
      abstractGenes.add(parent.getAbstractGenes().get(i).clone());
    }
  }

  /**
   * Root of the genome
   *
   * @return Gene that is the root of the Genome
   */
  public AbstractGene getRoot()
  {
    if (rootVertex == null)
    {
      return abstractGenes.get(0);
    }
    return rootVertex;
  }


  /**
   * Set the root of the Genome
   *
   * @param root
   */
  public void setRoot(AbstractGene root)
  {
    this.rootVertex = root;
  }

  /**
   * Get the genes
   *
   * @return List of Genes
   */
  public LinkedList<AbstractGene<?>> getAbstractGenes()
  {
    return abstractGenes;
  }

  /**
   * Size of the Genome
   * @return
   */
  public int size()
  {
    return abstractGenes.size();
  }

  public void linkRandomGeneTo(int geneIndex)
  {
    AbstractGene<?> abstractGene = abstractGenes.get(geneIndex);
    if (abstractGene == null)
    {
      return;
    }

  }

  public void linkGenes(int gene1, int gene2, double linkWeight)
  {
    AbstractGene<?> abstractGene = abstractGenes.get(gene1);
    if (abstractGene == null)
    {
      return;
    }
  }

  public void append(AbstractGene abstractGene)
  {
    abstractGenes.add(abstractGene);
  }


}
