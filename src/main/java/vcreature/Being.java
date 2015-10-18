package vcreature;


import vcreature.genotype.Genome;
import vcreature.phenotype.Creature;

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


}
