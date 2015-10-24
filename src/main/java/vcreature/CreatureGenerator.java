package vcreature;


import vcreature.genotype.Genome;
import vcreature.genotype.GenomeGenerator;
import vcreature.phenotype.Creature;

/**
 * Generates a creature based on a random genome
 */
public class CreatureGenerator
{
  Environment env;
  Creature creature;
  GenomeGenerator generator;

  public CreatureGenerator(Environment env)
  {
    this.env = env;
    generator = new GenomeGenerator();
    generateCreature();
  }

  public void setENV(Environment env)
  {
    this.env = env;
  }

  public Environment getENV()
  {
    return env;
  }

  public Creature getCreature()
  {
    return creature;
  }

  public Creature generateCreature()
  {
    removeCreature();
    creature = GenomeSynthesizer.init(env).encode(generator.generateGenome());
    return creature;
  }

  public void removeCreature()
  {
    try
    {
      creature.remove();
    }
    catch (NullPointerException ex) {}
  }
}
