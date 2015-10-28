package vcreature.genotype;


import java.util.*;

import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector3f;

import vcreature.Environment;
import vcreature.GenomeSynthesizer;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.EnumNeuronInput;


/**
 * Generate a genome
 */
public class GenomeGenerator
{
  private GenomeGeneratorParameters params = new GenomeGeneratorParameters();
  private Genome genome = new Genome();
  private Environment env;

  public GenomeGenerator(Environment env)
  {
    generateGenome();
    this.env = env;
  }

  public GenomeGenerator()
  {
    this(null);
  }

  public void clearGenome()
  {
    genome = new Genome();
  }

  public void setENV(Environment env)
  {
    this.env = env;
  }

  public Genome generateGenome()
  {
    clearGenome();
    if (env != null)
    {
      Gene root = generateGene();
      genome.setRoot(root);
      addGenes(root);
    }
    return genome;
  }

  private void addGenes(Gene parent)
  {
    int attempts = 0;
    Random rand = new Random();
    Gene gene;

    if (getDepth(parent) > params.MAX_DEPTH)
    {
      return;
    }

    while (attempts <= params.MAX_GENERATION_ATTEMPTS)
    {
      if (rand.nextFloat() <= params.CHILD_SPAWN_CHANCE && genome.neighbors(parent).size() <= params.MAX_CHILDREN)
      {
        gene = generateGene(parent);
        if (validGenome())
        {
          if (rand.nextFloat() <= params.RECURSE_CHANCE)
          {
            addGenes(gene);
          }
        }
        else
        {
          genome.remove(gene);
        }
      }
      attempts += 1;
    }
  }

  private Gene generateGene()
  {
    Random rand = new Random();
    Gene gene = new Gene(genome.size());
    gene.setDimensions(genRandSize(rand));
    genome.append(gene);
    return gene;
  }

  private Gene generateGene(Gene parent)
  {
    Random rand = new Random();
    Gene gene = new Gene(genome.size());

    float[] rotations = {0f, 0f, 0f};
    Vector3f size = genRandSize(rand);

    int side = rand.nextInt(6);
    Vector3f childPivot;
    Vector3f parentPivot;
    Vector3f pivotAxis;

    float childX, childY, childZ;
    float parentX, parentY, parentZ;

    Vector3f parentSize = new Vector3f();
    parent.getDimensions(parentSize);

    switch (side)
    {
      case 0: // XY plane 1
        parentX = parentSize.x - (rand.nextFloat()*2*parentSize.x);
        parentY = parentSize.y - (rand.nextFloat()*2*parentSize.y);
        parentZ = parentSize.z;
        parentPivot = new Vector3f(parentX, parentY, parentZ);

        childX = size.x - (rand.nextFloat()*2*size.x);
        childY = size.y - (rand.nextFloat()*2*size.y);
        childZ = -size.z;
        if (rand.nextBoolean()) // x axis
        {
          childX = rand.nextBoolean() ? size.x : -size.x;
          pivotAxis = Vector3f.UNIT_X;
        }
        else // y axis
        {
          childY = rand.nextBoolean() ? size.y : -size.y;
          pivotAxis = Vector3f.UNIT_Y;
        }
        childPivot = new Vector3f(childX, childY, childZ);

        //pivotAxis = rand.nextBoolean() ? Vector3f.UNIT_X : Vector3f.UNIT_Y;
        break;
      case 1: // XY plane 2
        parentX = parentSize.x - (rand.nextFloat()*2*parentSize.x);
        parentY = parentSize.y - (rand.nextFloat()*2*parentSize.y);
        parentZ = -parentSize.z;
        parentPivot = new Vector3f(parentX, parentY, parentZ);

        childX = size.x - (rand.nextFloat()*2*size.x);
        childY = size.y - (rand.nextFloat()*2*size.y);
        childZ = size.z;
        if (rand.nextBoolean()) // x axis
        {
          childX = rand.nextBoolean() ? size.x : -size.x;
          pivotAxis = Vector3f.UNIT_X;
        }
        else // y axis
        {
          childY = rand.nextBoolean() ? size.y : -size.y;
          pivotAxis = Vector3f.UNIT_Y;
        }
        childPivot = new Vector3f(childX, childY, childZ);

        //pivotAxis = rand.nextBoolean() ? Vector3f.UNIT_X : Vector3f.UNIT_Y;
        break;
      case 2: // XZ plane 1
        parentX = parentSize.x - (rand.nextFloat()*2*parentSize.x);
        parentY = parentSize.y;
        parentZ = parentSize.z - (rand.nextFloat()*2*parentSize.z);
        parentPivot = new Vector3f(parentX, parentY, parentZ);

        childX = size.x - (rand.nextFloat()*2*size.x);
        childY = -size.y;
        childZ = size.z - (rand.nextFloat()*2*size.z);
        if (rand.nextBoolean()) // x axis
        {
          childX = rand.nextBoolean() ? size.x : -size.x;
          pivotAxis = Vector3f.UNIT_X;
        }
        else // z axis
        {
          childZ = rand.nextBoolean() ? size.z : -size.z;
          pivotAxis = Vector3f.UNIT_Z;
        }
        childPivot = new Vector3f(childX, childY, childZ);

        //pivotAxis = rand.nextBoolean() ? Vector3f.UNIT_X : Vector3f.UNIT_Z;
        break;
      case 3: // XZ plane 2
        parentX = parentSize.x - (rand.nextFloat()*2*parentSize.x);
        parentY = -parentSize.y;
        parentZ = parentSize.z - (rand.nextFloat()*2*parentSize.z);
        parentPivot = new Vector3f(parentX, parentY, parentZ);

        childX = size.x - (rand.nextFloat()*2*size.x);
        childY = size.y;
        childZ = size.z - (rand.nextFloat()*2*size.z);
        if (rand.nextBoolean()) // x axis
        {
          childX = rand.nextBoolean() ? size.x : -size.x;
          pivotAxis = Vector3f.UNIT_X;
        }
        else // z axis
        {
          childZ = rand.nextBoolean() ? size.z : -size.z;
          pivotAxis = Vector3f.UNIT_Z;
        }
        childPivot = new Vector3f(childX, childY, childZ);

        //pivotAxis = rand.nextBoolean() ? Vector3f.UNIT_X : Vector3f.UNIT_Z;
        break;
      case 4: // YZ plane 1
        parentX = parentSize.x;
        parentY = parentSize.y - (rand.nextFloat()*2*parentSize.y);
        parentZ = parentSize.z - (rand.nextFloat()*2*parentSize.z);
        parentPivot = new Vector3f(parentX, parentY, parentZ);

        childX = -size.x;
        childY = size.y - (rand.nextFloat()*2*size.y);
        childZ = size.z - (rand.nextFloat()*2*size.z);
        if (rand.nextBoolean()) // y axis
        {
          childY = rand.nextBoolean() ? size.y : -size.y;
          pivotAxis = Vector3f.UNIT_Y;
        }
        else // z axis
        {
          childZ = rand.nextBoolean() ? size.z : -size.z;
          pivotAxis = Vector3f.UNIT_Z;
        }
        childPivot = new Vector3f(childX, childY, childZ);

        //pivotAxis = rand.nextBoolean() ? Vector3f.UNIT_Y : Vector3f.UNIT_Z;
        break;
      default: // YZ plane 2
        parentX = -parentSize.x;
        parentY = parentSize.y - (rand.nextFloat()*2*parentSize.y);
        parentZ = parentSize.z - (rand.nextFloat()*2*parentSize.z);
        parentPivot = new Vector3f(parentX, parentY, parentZ);

        childX = size.x;
        childY = size.y - (rand.nextFloat()*2*size.y);
        childZ = size.z - (rand.nextFloat()*2*size.z);
        if (rand.nextBoolean()) // y axis
        {
          childY = rand.nextBoolean() ? size.y : -size.y;
          pivotAxis = Vector3f.UNIT_Y;
        }
        else // z axis
        {
          childZ = rand.nextBoolean() ? size.z : -size.z;
          pivotAxis = Vector3f.UNIT_Z;
        }
        childPivot = new Vector3f(childX, childY, childZ);

        //pivotAxis = rand.nextBoolean() ? Vector3f.UNIT_Y : Vector3f.UNIT_Z;
        break;
    }
    gene.setDimensions(size);
    gene.setRotations(rotations);
    gene.getEffector().setParent(parentPivot);
    gene.getEffector().setChild(childPivot);
    gene.getEffector().setPivotAxis(pivotAxis);
    genome.append(gene);
    genome.linkGenes(genome.getGenes().indexOf(parent), genome.getGenes().indexOf(gene));
    return gene;
  }

  private float genRandDim(Random rand)
  {
    return params.MIN_BLOCK_DIM + (rand.nextFloat() * (params.MAX_BLOCK_DIM - params.MIN_BLOCK_DIM));
  }

  private Vector3f genRandSize(Random rand)
  {
    float width = genRandDim(rand); // x dimension
    float height = genRandDim(rand); // y dimension
    float depth = genRandDim(rand); // z dimension
    return new Vector3f(width/2, height/2, depth/2);
  }

  private EnumNeuronInput getRandInput(Random rand)
  {
    return EnumNeuronInput.values()[rand.nextInt(EnumNeuronInput.values().length)];
  }

  private int getDepth(Gene gene)
  {
    int depth = 0;
    Queue<Gene> frontier = new LinkedList<>();
    frontier.add(genome.getRoot());
    HashMap<Gene, Gene> cameFrom = new HashMap<>();
    HashMap<Gene, Integer> geneDepth = new HashMap<>();
    cameFrom.put(genome.getRoot(), null);
    geneDepth.put(genome.getRoot(), depth);

    while (!frontier.isEmpty())
    {
      Gene current = frontier.remove();
      List<Gene> neighbors = genome.neighbors(current);

      for (Gene next : neighbors)
      {
        if (!cameFrom.containsKey(next))
        {
          frontier.add(next);
          cameFrom.put(next, current);
          geneDepth.put(next, geneDepth.get(current) + 1);
        }
      }
    }
    return geneDepth.get(gene);
  }

  private boolean validGenome()
  {
    Creature creature = GenomeSynthesizer.init(env).encode(genome);

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
}
