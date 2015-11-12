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

import java.util.*;


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
   * @param parent the parent genome to merge with
   * @param start start index in the parent genome
   * @param end end index in the parent genome
   */
  public void merge(Genome parent, int start, int end)
  {
    int lastLink = genes.size()-1;
    for (int i = start; i < end; i++)
    {
      genes.add(parent.get(i));
    }
    // get the new
    genes.get(lastLink).addEdge(lastLink+1);
  }

  /**
   * Append a gene to list. No links are made!
   *
   * @param gene to append
   */
  public void append(Gene gene)
  {
    genes.add(gene);
//    genes.add(gene.getPosition(), gene);
//    if (gene.getPosition() > 0)
//    {
//      if (gene.getPosition() != genes.indexOf(gene))
//      {
//        throw new NoSuchElementException(gene.getPosition() + " " + genes.indexOf(gene) + " sz" + genes.size());
//      }
//    }
  }

  /**
   * Remove a gene and its edges.
   *
   * @param gene gene to remove
   */
  public synchronized  void remove(Gene gene)
  {
    for (Gene neighbor : neighbors(gene))
    {
      remove(neighbor);
    }

    int geneIdx = gene.getPosition();
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
   * @param root the gene to set as the root gene
   */
  public void setRoot(Gene root)
  {
    this.rootVertex = root;
//    if (!genes.contains(root))
//    {
//      genes.add(0, root);
//    }

  }

  /**
   * Get the genes
   *
   * @return List of Genes
   */
//  public Iterable<Gene> getGenes()
//  {
//    return genes;
//  }
  public synchronized LinkedList<Gene> getGenes()
  {
    return genes;
  }

  /**
   * Get the index of the gene in the genome
   *
   * @param g the gene to get the index for
   * @return the index of gene g in the genome
   */
  public int indexOf(Gene g)
  {
    return g.getPosition();
  }


  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }

    Genome genome = (Genome) o;

    if (genes != null ? !genes.equals(genome.genes) : genome.genes != null)
    {
      return false;
    }
    return !(rootVertex != null ? !rootVertex.equals(genome.rootVertex) : genome.rootVertex != null);

  }

  /**
   * Get a gene from the genome at the given index
   *
   * @param index index of the gene to get
   * @return the gene at the index in the genome
   */
  public synchronized Gene get(int index)
  {

    if (index != genes.get(index).getPosition())
    {
      try
      {
         return getGeneByPosition(index);
      }
      catch (Exception e)
      {
        System.out.println(e.getMessage());

//        throw new NoSuchElementException("Position is not consistent with index! " + index + " actual " + genes.get(index).getPosition() + " len = " + genes.size());

      }
    }

    return genes.get(index);
  }

  /**
   * Size of the Genome
   *
   * @return number of genes
   */
  public synchronized  int size()
  {
    return genes.size();
  }

  /**
   * Add a gene to the edge list of another
   * @param geneIndex1 index of the gene whose edges list will be added to
   * @param geneIndex2 index of the gene to be added to the edges list
   */
  public void linkGenes(int geneIndex1, int geneIndex2)
  {
    if (geneIndex1 < 0)
    {
      return;
    }

    Gene g1 = getGeneByPosition(geneIndex1);
    if (g1 == null)
    {
      return;
    }
    g1.addEdge(geneIndex2);
  }

  /**
   * Get all the neighbors of given the current location. Does not include self
   * only its links in the graph
   *
   * @param current the gene to get neighbors around
   * @return List of neighbors (children)
   */
  public synchronized  List<Gene> neighbors (Gene current)
  {
    ArrayList<Integer> edges = current.getEdges();
    ArrayList<Gene> neighbors = new ArrayList<>();
//    edges.forEach(integer1 -> integer1);

    int i = 0;
    for (Integer integer : edges)
    {
      i++;
      if (integer >= 0)
      {
        neighbors.add(genes.get(integer));
      }
    }
    return neighbors;
  }

  public synchronized List<Gene> leafs()
  {
    ArrayList<Gene> leafs = new ArrayList<>();

    Queue<Gene> frontier = new LinkedList<>();
    frontier.add(getRoot());

    HashMap<Gene, Gene> cameFrom = new HashMap<>();
    cameFrom.put(getRoot(), null);

    while (!frontier.isEmpty())
    {
      Gene current = frontier.remove();
      List<Gene> neighbors = neighbors(current);

      if (neighbors(current).size() == 0)
      {
        leafs.add(current);
      }

      for (Gene next : neighbors)
      {
        if (!cameFrom.containsKey(next))
        {
          frontier.add(next);
          cameFrom.put(next, current);
        }
      }
    }
    return leafs;
  }

  /**
   * remove the gene at the given position in the genome
   *
   * @param position of the gene to remove
   */
  public synchronized  void remove(int position)
  {
    remove(getGeneByPosition(position));
  }

  private Gene getGeneByPosition(int i)
  {
    // System.out.println(i);

    return genes.stream().filter(gene -> gene.getPosition() == i).findFirst().get();
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
    s.append("START\n");
    for (Gene gene : genes)
    {
      gene.write(s);
      s.append("\n");
    }
    s.append("END\n");
  }

  @Override
  public void read(StringBuilder s)
  {
    String line;
    String str = s.toString();
    Scanner scanner = new Scanner(str);
    if (scanner.hasNextLine() && scanner.nextLine().equals("START"))
    {
      //genes.clear();
      while (scanner.hasNextLine() && !(line = scanner.nextLine()).equals("END"))
      {
        Gene gene = new Gene(genes.size());
        gene.read(new StringBuilder(line));
        genes.add(gene);
      }
    }
  }
}
