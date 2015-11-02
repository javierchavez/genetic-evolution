package vcreature;


import vcreature.genotype.GenomeGenerator;
import vcreature.phenotype.Creature;

/**
 * Generates a creature based on a random genome
 */
public class CreatureGenerator
{
  private static final CreatureGenerator thisInstance = new CreatureGenerator();
  private static  Environment env;

  private Creature creature;

  private CreatureGenerator()
  {
  }

  public static CreatureGenerator getInstance()
  {
    return thisInstance;
  }

  public static CreatureGenerator init(Environment environment)
  {
    env = environment;
    return thisInstance;
  }

  public Creature generateCreature()
  {
    creature = GenomeSynthesizer.getInstance().encode(GenomeGenerator.init(env).generateGenome());
    return creature;
  }
}
