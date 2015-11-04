package vcreature;


import com.jme3.bullet.PhysicsSpace;
import com.jme3.scene.Node;
import vcreature.genotype.GenomeGenerator;
import vcreature.phenotype.Creature;

/**
 * Generates a creature based on a random genome
 */
public class CreatureGenerator
{
  private PhysicsSpace physicsSpace;
  private Node rootNode;

  private GenomeSynthesizer synthesizer;
  private GenomeGenerator generator;
  private Creature creature;

  public CreatureGenerator(PhysicsSpace physicsSpace, Node rootNode)
  {
    this.physicsSpace = physicsSpace;
    this.rootNode = rootNode;
    synthesizer = new GenomeSynthesizer(physicsSpace, rootNode);
    generator = new GenomeGenerator(physicsSpace, rootNode);
  }

  public Creature generateCreature()
  {
    creature = synthesizer.encode(generator.generateGenome());
    return creature;
  }
}
