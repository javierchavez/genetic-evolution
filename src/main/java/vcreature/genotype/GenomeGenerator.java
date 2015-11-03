package vcreature.genotype;


import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector3f;
import vcreature.Environment;
import vcreature.phenotype.Block;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.EnumOperator;

import java.util.*;


/**
 * Generate a genome
 */
public class GenomeGenerator
{
  private static final GenomeGenerator thisInstance = new GenomeGenerator();
  private static Environment env;
  private static GenomeGeneratorParameters params = new GenomeGeneratorParameters();
  private Genome genome = new Genome();

  private GenomeGenerator() {}

  public static GenomeGenerator init(Environment environment)
  {
    env = environment;
    return thisInstance;
  }

  public void clearGenome()
  {
    genome = new Genome();
  }

  public Genome generateGenome()
  {
    clearGenome();
    Gene root = generateGene();
    genome.setRoot(root);
    addGenes(root);
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
        if (true)
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

        childX = size.x - (rand.nextFloat()*2*size.x);
        childY = size.y - (rand.nextFloat()*2*size.y);
        childZ = -size.z;
        if (rand.nextBoolean()) // x axis
        {
          childX = rand.nextBoolean() ? size.x : -size.x;
          parentX = rand.nextBoolean() ? parentSize.x : -parentSize.x;
          //parentX = Math.signum(childX) == 1 ? -parentSize.x : parentSize.x;
          pivotAxis = Vector3f.UNIT_Y;
        }
        else // y axis
        {
          childY = rand.nextBoolean() ? size.y : -size.y;
          parentY = rand.nextBoolean() ? parentSize.y : -parentSize.y;
          //parentY = Math.signum(childY) == 1 ? -parentSize.y : parentSize.y;
          pivotAxis = Vector3f.UNIT_X;
        }
        parentPivot = new Vector3f(parentX, parentY, parentZ);
        childPivot = new Vector3f(childX, childY, childZ);
        break;
      case 1: // XY plane 2
        parentX = parentSize.x - (rand.nextFloat()*2*parentSize.x);
        parentY = parentSize.y - (rand.nextFloat()*2*parentSize.y);
        parentZ = -parentSize.z;

        childX = size.x - (rand.nextFloat()*2*size.x);
        childY = size.y - (rand.nextFloat()*2*size.y);
        childZ = size.z;
        if (rand.nextBoolean()) // x axis
        {
          childX = rand.nextBoolean() ? size.x : -size.x;
          parentX = rand.nextBoolean() ? parentSize.x : -parentSize.x;
          //parentX = Math.signum(childX) == 1 ? -parentSize.x : parentSize.x;
          pivotAxis = Vector3f.UNIT_Y;
        }
        else // y axis
        {
          childY = rand.nextBoolean() ? size.y : -size.y;
          parentY = rand.nextBoolean() ? parentSize.y : -parentSize.y;
          //parentY = Math.signum(childY) == 1 ? -parentSize.y : parentSize.y;
          pivotAxis = Vector3f.UNIT_X;
        }
        parentPivot = new Vector3f(parentX, parentY, parentZ);
        childPivot = new Vector3f(childX, childY, childZ);
        break;
      case 2: // XZ plane 1
        parentX = parentSize.x - (rand.nextFloat()*2*parentSize.x);
        parentY = parentSize.y;
        parentZ = parentSize.z - (rand.nextFloat()*2*parentSize.z);

        childX = size.x - (rand.nextFloat()*2*size.x);
        childY = -size.y;
        childZ = size.z - (rand.nextFloat()*2*size.z);
        if (rand.nextBoolean()) // x axis
        {
          childX = rand.nextBoolean() ? size.x : -size.x;
          parentX = rand.nextBoolean() ? parentSize.x : -parentSize.x;
          //parentX = Math.signum(childX) == 1 ? -parentSize.x : parentSize.x;
          pivotAxis = Vector3f.UNIT_Z;
        }
        else // z axis
        {
          childZ = rand.nextBoolean() ? size.z : -size.z;
          parentZ = rand.nextBoolean() ? parentSize.z : -parentSize.z;
          //parentZ = Math.signum(childZ) == 1 ? -parentSize.z : parentSize.z;
          pivotAxis = Vector3f.UNIT_X;
        }
        parentPivot = new Vector3f(parentX, parentY, parentZ);
        childPivot = new Vector3f(childX, childY, childZ);
        break;
      case 3: // XZ plane 2
        parentX = parentSize.x - (rand.nextFloat()*2*parentSize.x);
        parentY = -parentSize.y;
        parentZ = parentSize.z - (rand.nextFloat()*2*parentSize.z);

        childX = size.x - (rand.nextFloat()*2*size.x);
        childY = size.y;
        childZ = size.z - (rand.nextFloat()*2*size.z);
        if (rand.nextBoolean()) // x axis
        {
          childX = rand.nextBoolean() ? size.x : -size.x;
          parentX = rand.nextBoolean() ? parentSize.x : -parentSize.x;
          //parentX = Math.signum(childX) == 1 ? -parentSize.x : parentSize.x;
          pivotAxis = Vector3f.UNIT_Z;
        }
        else // z axis
        {
          childZ = rand.nextBoolean() ? size.z : -size.z;
          parentZ = rand.nextBoolean() ? parentSize.z : -parentSize.z;
          //parentZ = Math.signum(childZ) == 1 ? -parentSize.x : parentSize.x;
          pivotAxis = Vector3f.UNIT_X;
        }
        parentPivot = new Vector3f(parentX, parentY, parentZ);
        childPivot = new Vector3f(childX, childY, childZ);
        break;
      case 4: // YZ plane 1
        parentX = parentSize.x;
        parentY = parentSize.y - (rand.nextFloat()*2*parentSize.y);
        parentZ = parentSize.z - (rand.nextFloat()*2*parentSize.z);

        childX = -size.x;
        childY = size.y - (rand.nextFloat()*2*size.y);
        childZ = size.z - (rand.nextFloat()*2*size.z);
        if (rand.nextBoolean()) // y axis
        {
          childY = rand.nextBoolean() ? size.y : -size.y;
          parentY = rand.nextBoolean() ? parentSize.y : -parentSize.y;
          //parentY = Math.signum(childY) == 1 ? -parentSize.y : parentSize.y;
          pivotAxis = Vector3f.UNIT_Z;
        }
        else // z axis
        {
          childZ = rand.nextBoolean() ? size.z : -size.z;
          parentZ = rand.nextBoolean() ? parentSize.z : -parentSize.z;
          //parentZ = Math.signum(childZ) == 1 ? -parentSize.z : parentSize.z;
          pivotAxis = Vector3f.UNIT_Y;
        }
        parentPivot = new Vector3f(parentX, parentY, parentZ);
        childPivot = new Vector3f(childX, childY, childZ);
        break;
      default: // YZ plane 2
        parentX = -parentSize.x;
        parentY = parentSize.y - (rand.nextFloat()*2*parentSize.y);
        parentZ = parentSize.z - (rand.nextFloat()*2*parentSize.z);

        childX = size.x;
        childY = size.y - (rand.nextFloat()*2*size.y);
        childZ = size.z - (rand.nextFloat()*2*size.z);
        if (rand.nextBoolean()) // y axis
        {
          childY = rand.nextBoolean() ? size.y : -size.y;
          parentY = rand.nextBoolean() ? parentSize.y : -parentSize.y;
          //parentY = Math.signum(childY) == 1 ? -parentSize.y : parentSize.y;
          pivotAxis = Vector3f.UNIT_Z;
        }
        else // z axis
        {
          childZ = rand.nextBoolean() ? size.z : -size.z;
          parentZ = rand.nextBoolean() ? parentSize.z : -parentSize.z;
          //parentZ = Math.signum(childZ) == 1 ? -parentSize.z : parentSize.z;
          pivotAxis = Vector3f.UNIT_Y;
        }
        parentPivot = new Vector3f(parentX, parentY, parentZ);
        childPivot = new Vector3f(childX, childY, childZ);
        break;
    }
    gene.setDimensions(size);
    gene.setRotations(rotations);
    gene.getEffector().setParent(parentPivot);
    gene.getEffector().setChild(childPivot);
    gene.getEffector().setPivotAxis(pivotAxis);
    addNeurons(gene, rand.nextInt(params.MAX_NEURON_RULES));
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

//  private boolean validGenome()
//  {
//    Creature creature = GenomeSynthesizer.init(env).encode(genome);
//
//    Block block1;
//    Block block2;
//    for (int i = 0; i < creature.getNumberOfBodyBlocks(); i++)
//    {
//      block1 = creature.getBlockByID(i);
//      for(int j = 0; j < creature.getNumberOfBodyBlocks(); j++)
//      {
//        if (i != j)
//        {
//          block2 = creature.getBlockByID(j);
//          if (getCollision(block1, block2).size() > 0)
//          {
//            creature.remove();
//            return false;
//          }
//        }
//      }
//    }
//    creature.remove();
//    return true;
//  }

  private CollisionResults getCollision(Block block1, Block block2)
  {
    CollisionResults results = new CollisionResults();
    block1.getGeometry().collideWith(block2.getGeometry().getWorldBound(), results);
    return results;
  }

  private void addNeurons(Gene gene, int numNeurons)
  {
    for (int i = 0; i <= numNeurons; i++)
    {
      gene.getEffector().addNeuralNode(genRandNeuron(gene));
    }
  }

  private NeuralNode genRandNeuron(Gene gene)
  {
    Random rand = new Random();
    NeuralNode neuron = new NeuralNode();

    neuron.setInput(NeuralInput.InputPosition.A, genRandInput(rand, gene));
    neuron.setInput(NeuralInput.InputPosition.B, genRandInput(rand, gene));
    neuron.setInput(NeuralInput.InputPosition.C, genRandInput(rand, gene));
    neuron.setInput(NeuralInput.InputPosition.D, genRandInput(rand, gene));
    neuron.setInput(NeuralInput.InputPosition.E, genRandInput(rand, gene));

    neuron.setOperator(getRandOperator(rand, 0), NeuralNode.NeuralOperatorPosition.FIRST);
    neuron.setOperator(getRandOperator(rand, 1), NeuralNode.NeuralOperatorPosition.SECOND);
    neuron.setOperator(getRandOperator(rand, 2), NeuralNode.NeuralOperatorPosition.THIRD);
    neuron.setOperator(getRandOperator(rand, 3), NeuralNode.NeuralOperatorPosition.FOURTH);
    return neuron;
  }

  private NeuralInput genRandInput(Random rand, Gene gene)
  {
    NeuralInput neuralInput;
    EnumNeuronInput input =  EnumNeuronInput.values()[rand.nextInt(EnumNeuronInput.values().length)];

    switch (input)
    {
      case TIME:
        neuralInput = new TimeInput();
        neuralInput.setValue(params.MIN_TIME + (rand.nextFloat() * (params.MAX_TIME - params.MIN_TIME)));
        break;
      case TOUCH:
        neuralInput = gene.getTouchSensor();
        neuralInput.setValue(rand.nextBoolean() ? 1f : 0f);
        break;
      case HEIGHT:
        neuralInput = gene.getHeightSensor();
        neuralInput.setValue(params.MIN_HEIGHT + (rand.nextFloat() * (params.MAX_HEIGHT - params.MIN_HEIGHT)));
        break;
      case JOINT:
        neuralInput = gene.getAngleSensor();
        neuralInput.setValue(params.MIN_ANGLE + (rand.nextFloat() * (params.MAX_ANGLE - params.MIN_ANGLE)));
        break;
      default:
        neuralInput = new ConstantInput();
        neuralInput.setValue(params.MIN_CONST + (rand.nextFloat() * (params.MAX_CONST - params.MIN_CONST)));
        break;
    }
    return neuralInput;
  }

  private EnumOperator getRandOperator(Random rand, int position)
  {
    EnumOperator operator;
    if (position == 0 || position == 2)
    { // binary operator
      //operator = EnumOperator.ADD;
      operator = EnumOperator.values()[rand.nextInt(7)];
    }
    else
    { // unary operator
      //operator = EnumOperator.IDENTITY;
      operator = EnumOperator.values()[7 + rand.nextInt(EnumOperator.SIZE-7)];
    }
    return operator;
  }
}
