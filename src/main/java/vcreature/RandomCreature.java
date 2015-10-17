package vcreature;


import java.util.ArrayList;
import java.util.List;
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
    clearCreature();
    Block root = generateBlock();
    addBlocks(root);
  }

  public void clearCreature()
  {
    for (Block block : body)
    {
      physicsSpace.remove(block.getGeometry());
      jMonkeyRootNode.detachChild(block.getGeometry());
    }
    jMonkeyRootNode.updateGeometricState();
    body.clear();
  }

  private void addBlocks(Block parent)
  {
    int attempts = 0;
    Random rand = new Random();
    while (attempts < params.MAX_GENERATION_ATTEMPTS)
    {
      if (getChildren(parent).size() <= params.MAX_CHILDREN && rand.nextFloat() < params.CHILD_SPAWN_CHANCE)
      {
        Block child = generateBlock(parent);
        if (getDepth(parent, 0) <= params.MAX_DEPTH && rand.nextFloat() < params.RECURSE_CHANCE)
        {
          addBlocks(child);
        }
      }
    }
  }

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

  private Block generateBlock(Block parent)
  {
    Random rand = new Random();
    Vector3f size = genRandSize(rand);

    // add the block to parent
    // this should be done in Joel's new api\
    // also checks for collisions and doesn't
    // place a block if it is colliding with something else
    return null;
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

  private List<Block> getChildren(Block parent)
  {
    ArrayList<Block> children = new ArrayList<>();
    for (Block block : body)
    {
      if (parent.getID() == block.getIdOfParent())
      {
        children.add(block);
      }
    }
    return children;
  }

  private int getDepth(Block block, int depth)
  {
    if (block.getIdOfParent() == -1)
    {
      return depth;
    }

    return getDepth(body.get(block.getIdOfParent()), depth+1);
  }
}
