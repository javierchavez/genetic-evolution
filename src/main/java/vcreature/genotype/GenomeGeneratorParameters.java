package vcreature.genotype;


/**
 *
 */
public class GenomeGeneratorParameters
{
  public static final float MIN_BLOCK_DIM = 1.0f; // minimum size in any x, y, or z direction
  public static final float MAX_BLOCK_DIM = 10.0f; // maximum size in any x, y, or z direction

  public static final int MAX_GENERATION_ATTEMPTS = 100; // maximum number of attempts to try and generate a child block
  public static final int MAX_NEURON_RULES = 10; // maximum number of neurons per joint
  public static final int MAX_CHILDREN = 6; // maximum number of children any block can have
  public static final int MAX_DEPTH = 6; // maximum number of children any block can be from root

  public static final float CHILD_SPAWN_CHANCE = 0.4f; // chance that a block will spawn a child block
  public static final float RECURSE_CHANCE = 0.15f; // chance that a block will spawn at depth n+1 from root block

  public static final int MIN_TIME = 2;
  public static final int MAX_TIME = 10;
  public static final float MIN_ANGLE = (float) -Math.PI / 2;
  public static final float MAX_ANGLE = (float) Math.PI / 2;
  public static final int MIN_CONST = 0;
  public static final int MAX_CONST = 100;
  public static final int MIN_HEIGHT = 10;
  public static final int MAX_HEIGHT = 30;
}
