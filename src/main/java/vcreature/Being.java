package vcreature;


import vcreature.genotype.Genome;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;

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
}
