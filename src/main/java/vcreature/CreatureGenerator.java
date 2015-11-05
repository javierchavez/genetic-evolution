package vcreature;

/**
 * @author Javier Chavez
 * @author Alex Baker
 * @author Dominic Salas
 * @author Carrie Martinez
 * <p>
 * Date November 4, 2015
 * CS 351
 * Genetic Evolution
 */


import com.jme3.bullet.PhysicsSpace;
import com.jme3.scene.Node;

import vcreature.genotype.GenomeGenerator;
import vcreature.phenotype.Creature;


/**
 * This class will generate a random phenotype.
 * It uses the genome generator to first generate a genome
 * and then synthesize it to a creature (phenotype)
 */
public class CreatureGenerator
{
  private PhysicsSpace physicsSpace;
  private Node rootNode;

  private GenomeSynthesizer synthesizer;
  private GenomeGenerator generator;
  private Creature creature;

  /**
   * Create a new generator instance with the given physics space and environment
   *
   * @param physicsSpace physics space to create the creature in
   * @param rootNode root node of the physics space
   */
  public CreatureGenerator(PhysicsSpace physicsSpace, Node rootNode)
  {
    this.physicsSpace = physicsSpace;
    this.rootNode = rootNode;
    synthesizer = new GenomeSynthesizer(physicsSpace, rootNode);
    generator = new GenomeGenerator(physicsSpace, rootNode);
  }

  /**
   * Procedurally generate a random creature
   *
   * @return generated creature
   */
  public Creature generateCreature()
  {
    creature = synthesizer.encode(generator.generateGenome());
    return creature;
  }
}
