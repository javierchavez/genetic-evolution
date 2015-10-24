package vcreature;


import com.jme3.math.Vector3f;
import vcreature.genotype.Gene;
import vcreature.genotype.Genome;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class GenomeSynthesizer extends Synthesizer<Genome, Creature>
{
  private final static GenomeSynthesizer ourInstance = new GenomeSynthesizer();
  private static Environment env;
  private Genome genome;
  private Creature creature;

  public static GenomeSynthesizer getInstance()
  {
    return ourInstance;
  }

  private GenomeSynthesizer()
  {
  }

  public static GenomeSynthesizer init(Environment environment)
  {
    env = environment;
    return ourInstance;
  }

  @Override
  public Creature encode(Genome typeToConvert)
  {
    this.genome = typeToConvert;
    this.creature = new Creature(env.getBulletAppState().getPhysicsSpace(),
                                     env.getRootNode());

    Queue<Gene> frontier = new LinkedList<>();
    frontier.add(genome.getRoot());
    HashMap<Gene, Gene> cameFrom = new HashMap<>();
    HashMap<Gene, Block> blockParent = new HashMap<>();
    cameFrom.put(genome.getRoot(), null);
    blockParent.put(genome.getRoot(), null);

    Block currentBlock;
    while (!frontier.isEmpty())
    {
      Gene current = frontier.remove();
      List<Gene> neighbors = genome.neighbors(current);

      currentBlock = synthesizeGene(current, blockParent.get(current));

      for (Gene next : neighbors)
      {
        if (!cameFrom.containsKey(next))
        {
          frontier.add(next);
          cameFrom.put(next, current);
          blockParent.put(next, currentBlock);
        }
      }

    }
    creature.placeOnGround();
    return creature;
  }

  private Block synthesizeGene(Gene current, Block parent)
  {
    Block block;
    Vector3f size = new Vector3f(current.getLengthX()/2, current.getHeightY()/2, current.getWidthZ()/2);

    if (parent == null)
    {
      // start the creature at the origin
      // creature.placeOnGround() will move it as necessary
      block = creature.addRoot(new Vector3f(0, 0, 0), size);
    }
    else
    {
      float[] rotations = new float[3];
      current.getEffector().getRotation(rotations);

      Vector3f parentPivot = new Vector3f();
      current.getEffector().getParent(parentPivot);

      Vector3f currentPivot = new Vector3f();
      current.getEffector().getChild(currentPivot);

      Vector3f pivotAxis = new Vector3f();
      current.getEffector().getPivotAxis(pivotAxis);

      block = creature.addBlock(rotations, size, parent, parentPivot, currentPivot, pivotAxis);
    }
    return block;
  }


  @Override
  public Genome decode(Creature typeToConvert)
  {
    return null;
  }

  @Override
  public String toString()
  {
    return null;
  }

  @Override
  public String synthesizedToString()
  {
    return creature.toString();
  }
}
