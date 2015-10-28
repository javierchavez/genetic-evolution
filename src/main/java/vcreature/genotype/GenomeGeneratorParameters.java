package vcreature.genotype;


/**
 *
 */
public class GenomeGeneratorParameters
{
  public static final float MIN_BLOCK_DIM = 1.0f; // minimum size in any x, y, or z direction
  public static final float MAX_BLOCK_DIM = 10.0f; // maximum size in any x, y, or z direction

  public static final int MAX_GENERATION_ATTEMPTS = 100; // maximum number of attempts to try and generate a child block
  public static final int MAX_NEURON_VALUE = 100; // maximum value a neuron input can be
  public static final int MAX_CHILDREN = 5; // maximum number of children any block can have
  public static final int MAX_DEPTH = 5; // maximum number of children any block can be from root

  public static final float CHILD_SPAWN_CHANCE = 0.3f; // chance that a block will spawn a child block
  public static final float RECURSE_CHANCE = 0.2f; // chance that a block will spawn at depth n+1 from root block
}
