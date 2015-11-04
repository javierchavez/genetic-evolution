package vcreature.genotype;

import com.jme3.bullet.PhysicsSpace;
import com.jme3.collision.CollisionResults;
import com.jme3.scene.Node;
import vcreature.GenomeSynthesizer;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.utils.Savable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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

  /**
   * Append a gene to list. No links are made!
   *
   * @param gene to append
   */
  public void append(Gene gene)
  {
    genes.add(gene);
  }

  /**
   * Remove a gene and its edges.
   *
   * @param gene gene to remove
   */
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
   *
   * @return number of genes
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

  /**
   * Get all the neighbors of given the current location. Does not include self
   * only its links in the graph
   *
   * @param current location
   * @return List of neighbors
   */
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

  public boolean isValid(PhysicsSpace physicsSpace, Node rootNode)
  {
    GenomeSynthesizer synthesizer = new GenomeSynthesizer(physicsSpace, rootNode);
    Creature creature = synthesizer.encode(this);

    Block block1;
    Block block2;
    for (int i = 0; i < creature.getNumberOfBodyBlocks(); i++)
    {
      block1 = creature.getBlockByID(i);
      for(int j = 0; j < creature.getNumberOfBodyBlocks(); j++)
      {
        if (i != j)
        {
          block2 = creature.getBlockByID(j);
          if (getCollision(block1, block2).size() > 0)
          {
            creature.remove();
            return false;
          }
        }
      }
    }
    creature.remove();
    return true;
  }

  private CollisionResults getCollision(Block block1, Block block2)
  {
    CollisionResults results = new CollisionResults();
    block1.getGeometry().collideWith(block2.getGeometry().getWorldBound(), results);
    return results;
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
  public void read(String s)
  {
    String line;
    Scanner scanner = new Scanner(s);
    if (scanner.hasNextLine() && scanner.nextLine().equals("START"))
    {
      genes.clear();
      while (scanner.hasNextLine() && !(line = scanner.nextLine()).equals("END"))
      {
        Gene gene = new Gene(genes.size());
        gene.read(line);
        genes.add(gene);
      }
    }
  }
}
