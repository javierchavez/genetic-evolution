package vcreature;


import vcreature.genotype.Genome;

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
  // private Creature phenotype;
  private int timesHillClimbed;
  private int timesBred;
  private int children;
  private float fitness =0;

  public Being(Genome bird) {
    this.genotype = bird;

    //setPhenotype(bird.getClass(), phy, n);
  }


//  public Being(Creature bird, PhysicsSpace phy, Node n) {
//    this.phenotype = bird;
//
//    //setPhenotype(bird.getClass(), phy, n);
//  }

  public Being() {

  }

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

//  public Block[] getBodyBlocks ()
//  {
//    int totalBlocks = phenotype.getNumberOfBodyBlocks();
//    Block[] blocks = new Block[totalBlocks];
//    for (int i = 0; i < totalBlocks; i++)
//    {
//      blocks[i] = phenotype.getBlockByID(i);
//    }
//
//    return blocks;
//  }

//  public void update(float delta)
//  {
//    //phenotype.updateBrain(delta);
//  }

//  public void setPhenotype(Class<? extends Creature> creatureClass, PhysicsSpace phy, Node n)
//  {
//     // System.out.println(genotype);
//    try
//    {
//      this.phenotype = creatureClass.getDeclaredConstructor(PhysicsSpace.class, Node.class).newInstance(phy, n);
//    }
//    catch (InstantiationException e)
//    {
//      e.printStackTrace();
//    }
//    catch (IllegalAccessException e)
//    {
//      e.printStackTrace();
//    }
//    catch (InvocationTargetException e)
//    {
//      e.printStackTrace();
//    }
//    catch (NoSuchMethodException e)
//    {
//      e.printStackTrace();
//    }
//
//    CreatureSynthesizer synthesizer = new CreatureSynthesizer();
//    genotype = synthesizer.encode(phenotype);
//    // System.out.println(genotype);
//    phenotype.remove();
//
//
//
//  }

//  public Creature getPhenotype()
//  {
//    return phenotype;
//  }

//  public Creature createPhenotype()
//  {
//
//    return phenotype;
//  }

//  public void removePhenotype()
//  {
//    phenotype.remove();
//  }

  @Override
  public Being clone()
  {
    Being newBeing = new Being();
    newBeing.setGenotype(genotype.clone());
    newBeing.setFitness(fitness);
    return newBeing;
  }
}
