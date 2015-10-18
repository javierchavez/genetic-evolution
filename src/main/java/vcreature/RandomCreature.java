package vcreature;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jme3.bullet.PhysicsSpace;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import com.jme3.scene.Node;
import com.jme3.math.Vector3f;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.Neuron;


/**
 * @author Alex Baker
 *
 * Procedurally generates a random creature
 */
public class RandomCreature extends Creature
{
  private RandomCreatureParameters params = new RandomCreatureParameters();

  private List<Block> body = new ArrayList<>();

  public RandomCreature(PhysicsSpace physicsSpace, Node jMonkeyRootNode)
  {
    super(physicsSpace, jMonkeyRootNode);
    generateCreature();
  }

  public void generateCreature()
  {
    if (body.size() != 0) remove();
    body.clear();
    Block root = generateBlock();
    body.add(root);
    addBlocks(root);
  }

  private void addBlocks(Block parent)
  {
    int attempts = 0;
    Random rand = new Random();

    if (getDepth(parent, 0) > params.MAX_DEPTH)
    {
      return;
    }

    while (attempts < params.MAX_GENERATION_ATTEMPTS)
    {
      if (getChildren(parent).size() <= params.MAX_CHILDREN && rand.nextFloat() < params.CHILD_SPAWN_CHANCE)
      {
        Block child = generateBlock(parent);
        body.add(child);
        if (child != null && rand.nextFloat() < params.RECURSE_CHANCE)
        {
          addBlocks(child);
        }
      }
      attempts++;
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
      Block blockParent = getParent(block);
      if (blockParent != null && parent.getID() == blockParent.getID())
      {
        children.add(block);
      }
    }
    return children;
  }

  private int getDepth(Block block, int depth)
  {
    Block parent = getParent(block);
    if (parent == null)
    {
      return depth;
    }

    return getDepth(parent, depth+1);
  }

  private Block getParent(Block block)
  {
    Block parent;
    try
    {
      parent = body.get(block.getIdOfParent());
    }
    catch (NullPointerException ex)
    {
      parent = null;
    }
    return parent;
  }

  private Neuron genRandNeuron()
  {
    Random rand = new Random();

    EnumNeuronInput inputA = getRandInput(rand);
    EnumNeuronInput inputB = getRandInput(rand);
    EnumNeuronInput inputC = getRandInput(rand);
    EnumNeuronInput inputD = getRandInput(rand);
    EnumNeuronInput inputE = getRandInput(rand);

    Neuron neuron = new Neuron(inputA, inputB, inputC, inputD, inputE);
    neuron.setInputValue(Neuron.A, rand.nextInt(params.MAX_NEURON_VALUE));
    neuron.setInputValue(Neuron.B, rand.nextInt(params.MAX_NEURON_VALUE));
    neuron.setInputValue(Neuron.C, rand.nextInt(params.MAX_NEURON_VALUE));
    neuron.setInputValue(Neuron.D, rand.nextInt(params.MAX_NEURON_VALUE));
    neuron.setInputValue(Neuron.E, rand.nextInt(params.MAX_NEURON_VALUE));

    return neuron;
  }

  private EnumNeuronInput getRandInput(Random rand)
  {
    return EnumNeuronInput.values()[rand.nextInt(EnumNeuronInput.values().length)];
  }
}
