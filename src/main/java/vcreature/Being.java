package vcreature;


import com.jme3.bullet.PhysicsSpace;
import com.jme3.scene.Node;
import vcreature.genotype.Genome;
import vcreature.genotype.GenomeGenerator;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;

import java.lang.reflect.InvocationTargetException;

/**
 * Wrapper for Genome and Creature
 *
 * The population will consist of many Beings
 */
public class Being /*Comparable<Being>*/
{

  private static int totalBeings = 0;

  private String name;
  private int age;
  // private final long serial; // joel uses serial
  private Genome genotype;
  private Creature phenotype;
  private int timesHillClimbed;
  private int timesBred;
  private int children;
  private float fitness;

  public Genome getGenotype()
  {
    return genotype;
  }

  public void setGenotype(Genome genotype)
  {
    this.genotype = genotype;
  }

  public float getFitness()
  {
    return fitness;
  }

  public void setFitness(float fitness)
  {
    this.fitness = fitness;
  }

  public Block[] getBodyBlocks ()
  {
    int totalBlocks = phenotype.getNumberOfBodyBlocks();
    Block[] blocks = new Block[totalBlocks];
    for (int i = 0; i < totalBlocks; i++)
    {
      blocks[i] = phenotype.getBlockByID(i);
    }

    return blocks;
  }

  public void update(float delta)
  {
    phenotype.updateBrain(delta);
  }

  public void setPhenotype(Class<? extends Creature> creatureClass, Environment environment)
  {
    try
    {
      this.phenotype = creatureClass.getDeclaredConstructor(PhysicsSpace.class, Node.class).newInstance(
              environment.getBulletAppState().getPhysicsSpace(), environment.getRootNode());
    }
    catch (InstantiationException e)
    {
      e.printStackTrace();
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
    catch (InvocationTargetException e)
    {
      e.printStackTrace();
    }
    catch (NoSuchMethodException e)
    {
      e.printStackTrace();
    }

    CreatureSynthesizer synthesizer = new CreatureSynthesizer();
    genotype = synthesizer.encode(phenotype);
    phenotype.remove();

  }

  public Creature getPhenotype()
  {
    return phenotype;
  }

  public Creature createPhenotype(Environment env)
  {
    phenotype = GenomeSynthesizer.init(env).encode(genotype);
    return phenotype;
  }

  public void removePhenotype()
  {
    phenotype.remove();
  }
}
