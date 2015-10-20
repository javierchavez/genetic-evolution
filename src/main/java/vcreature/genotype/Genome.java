package vcreature.genotype;

import java.util.ArrayList;

/**
 * Genome is a collection of genes. Genetic representation of this morphology is a directed graph of
 * nodes and connections. More simply, genome is analogous
 * to a directed graph and AbstractGene is the vertex, edges and weights are stored in the AbstractGene.
 */
public class Genome
{

  private ArrayList<Chromosome> genome;

  private Chromosome rootVertex;


  public Genome()
  {
    genome = new ArrayList<>();
  }



  /**
   * Root of the genome
   *
   * @return Gene that is the root of the Genome
   */
  public Chromosome getRoot()
  {
    if (rootVertex == null)
    {
      return genome.get(0);
    }
    return rootVertex;
  }


  /**
   * Set the root of the Genome
   *
   * @param root
   */
  public void setRoot(Chromosome root)
  {
    this.rootVertex = root;
  }

  /**
   * Get the genes
   *
   * @return List of Genes
   */
  public ArrayList<Chromosome> getGenes()
  {
    return genome;
  }

  /**
   * Size of the Genome
   * @return
   */
  public int size()
  {
    return genome.size();
  }



  public void append(Chromosome abstractGene)
  {
    genome.add(abstractGene);
  }


}
