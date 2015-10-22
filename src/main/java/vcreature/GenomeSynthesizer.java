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

  public static void init(Environment environment)
  {
    env = environment;
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
    cameFrom.put(genome.getRoot(), null);

    while (!frontier.isEmpty())
    {
      Gene current = frontier.remove();
      List<Gene> neighbors = genome.neighbors(current);

      Block block = synthesizeGene(current, true);

      for (Gene next : neighbors)
      {
        if (!cameFrom.containsKey(next))
        {
          frontier.add(next);
          cameFrom.put(next, current);

        }
      }

    }


    return null;
  }

  private Block synthesizeGene(Gene current, boolean isRoot)
  {

    Vector3f _vec = new Vector3f(current.getLengthX(), current.getHeightY(), current.getWidthZ());

    if (isRoot)
    {
      // Block torso = this.creature.addRoot(_vec)

    }

    return null;
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
    return null;
  }
}
