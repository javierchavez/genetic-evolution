package vcreature.genotype;

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


/**
 * This class is a static class to hold parameters used by the
 * procedural generation of genomes. Changing these values will
 * change the look and response of the genomes created
 */
public class GenomeGeneratorParameters
{
  public static final float MIN_BLOCK_DIM = 1f; // minimum size in any x, y, or z direction
  public static final float MAX_BLOCK_DIM = 6f; // maximum size in any x, y, or z direction

  public static final int MAX_GENERATION_ATTEMPTS = 100; // maximum number of attempts to try and generate a child block
  public static final int MAX_NEURON_RULES = 4; // maximum number of neurons per joint
  public static final int MAX_CHILDREN = 4; // maximum number of children any block can have
  public static final int MAX_DEPTH = 4; // maximum number of children any block can be from root

  public static final float CHILD_SPAWN_CHANCE = 0.4f; // chance that a block will spawn a child block
  public static final float RECURSE_CHANCE = 0.0f; // chance that a block will spawn at depth n+1 from root block

  public static final int MIN_CONST = 0;
  public static final int MAX_CONST = 100;
}
