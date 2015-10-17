package vcreature;


import java.util.Random;

import com.jme3.bullet.PhysicsSpace;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import com.jme3.scene.Node;
import com.jme3.math.Vector3f;


/**
 * @author Alex Baker
 *
 * Procedurally generates a random creature
 */
public class RandomCreature extends Creature
{
  private RandomCreatureParameters params = new RandomCreatureParameters();

  public RandomCreature(PhysicsSpace physicsSpace, Node jMonkeyRootNode)
  {
    super(physicsSpace, jMonkeyRootNode);
    generateCreature();
  }

  public void generateCreature()
  {
    generateBlock();
  }

  /**
   * generates a random blocked to be used as the root
   *
   * @return generated block
   */
  private Block generateBlock()
  {
    Random rand = new Random();
    Vector3f size = genRandSize(rand);

    float x = 0f;
    float y = size.y;
    float z = 0f;
    Vector3f center = new Vector3f(x, y, z);

    return addRoot(center, size);
  }

  private float genRandDim(Random rand)
  {
    return params.MIN_BLOCK_DIM + (rand.nextFloat() * (params.MAX_BLOCK_DIM - params.MIN_BLOCK_DIM));
  }

  private Vector3f genRandSize(Random rand)
  {
    float width = genRandDim(rand); // x dimension
    float height = genRandDim(rand); // y dimension
    float depth = genRandDim(rand); // z dimension
    return new Vector3f(width/2, height/2, depth/2);
  }
}
