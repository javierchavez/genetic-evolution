package vcreature;


import com.jme3.bullet.PhysicsSpace;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.Neuron;

import java.util.Random;


/**
 * @author Alex Baker
 *
 * Procedurally generates a random creature
 */
public class RandomCreature extends Creature
{
  // private RandomCreatureParameters params = new RandomCreatureParameters();

  public RandomCreature(PhysicsSpace physicsSpace, Node jMonkeyRootNode)
  {
    super(physicsSpace, jMonkeyRootNode);
    generateCreature();
  }

  public void generateCreature()
  {
    int attempts = -1;
    Block root;
    do
    {
      remove();
      root = generateBlock();
      addBlocks(root);
      attempts++;
    }
    while (!validCreature() && attempts < RandomCreatureParameters.MAX_GENERATION_ATTEMPTS);

    if (!validCreature())
    {
      remove();
      generateBlock();
    }
    placeOnGround();
  }

  @Override
  public void remove()
  {
    try
    {
      super.remove();
    }
    catch (IndexOutOfBoundsException ex) {}
  }

  private void addBlocks(Block parent)
  {
    int attempts = 0;
    Random rand = new Random();
    Block child;

    if (getDepth(parent, 0) > RandomCreatureParameters.MAX_DEPTH)
    {
      return;
    }

    // TODO:  Figure out how to optimise this!
    //        Joel does not support removing a block from the creature anymore so
    //        I have to throw away an entire creature instead of just a block
    //        when a creature is invalid (colliding blocks)
    while (attempts < RandomCreatureParameters.MAX_GENERATION_ATTEMPTS && validCreature())
    {
      if (parent.getChildList().size() <= RandomCreatureParameters.MAX_CHILDREN && rand.nextFloat() < RandomCreatureParameters.CHILD_SPAWN_CHANCE)
      {
        child = generateBlock(parent);
        if (rand.nextFloat() < RandomCreatureParameters.RECURSE_CHANCE)
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
    float y = 0f;
    float z = 0f;
    Vector3f center = new Vector3f(x, y, z);

    return addRoot(center, size);
  }

  private Block generateBlock(Block parent)
  {
    Random rand = new Random();
    Vector3f size = genRandSize(rand);
    Vector3f parentSize = new Vector3f(parent.getSizeX()/2, parent.getSizeY()/2, parent.getSize()/2);
    float[] rotations = {0f, 0f, 0f};

    int side = rand.nextInt(6);
    Vector3f pivot;
    Vector3f parentPivot;
    Vector3f axis;
    Vector3f parentAxis;

    float x, y, z;
    float parentX, parentY, parentZ;

    switch (side)
    {
      case 0: // XY plane 1
        parentX = (parent.getSizeX()/2) - (rand.nextFloat()*parent.getSizeX());
        parentY = (parent.getSizeY()/2) - (rand.nextFloat()*parent.getSizeY());
        parentZ = parentSize.z;
        parentPivot = new Vector3f(parentX, parentY, parentZ);

        x = size.x - (rand.nextFloat()*2*size.x);
        y = size.y - (rand.nextFloat()*2*size.y);
        z = -size.z;
        pivot = new Vector3f(x, y, z);

        //rotations = new float[] {0f, 0f, (float) (rand.nextFloat()*2*Math.PI)};
        if (rand.nextBoolean())
        {
          parentAxis = Vector3f.UNIT_X;
          axis = Vector3f.UNIT_X;
        }
        else
        {
          parentAxis = Vector3f.UNIT_Y;
          axis = Vector3f.UNIT_Y;
        }
        break;
      case 1: // XY plane 2
        parentX = (parent.getSizeX()/2) - (rand.nextFloat()*parent.getSizeX());
        parentY = (parent.getSizeY()/2) - (rand.nextFloat()*parent.getSizeY());
        parentZ = -parentSize.z;
        parentPivot = new Vector3f(parentX, parentY, parentZ);

        x = size.x - (rand.nextFloat()*2*size.x);
        y = size.y - (rand.nextFloat()*2*size.y);
        z = size.z;
        pivot = new Vector3f(x, y, z);

        //rotations = new float[] {0f, 0f, (float) (rand.nextFloat()*2*Math.PI)};
        if (rand.nextBoolean())
        {
          parentAxis = Vector3f.UNIT_X;
          axis = Vector3f.UNIT_X;
        }
        else
        {
          parentAxis = Vector3f.UNIT_Y;
          axis = Vector3f.UNIT_Y;
        }
        break;
      case 2: // XZ plane 1
        parentX = (parent.getSizeX()/2) - (rand.nextFloat()*parent.getSizeX());
        parentY = parentSize.y;
        parentZ = (parent.getSize()/2) - (rand.nextFloat()*parent.getSize());
        parentPivot = new Vector3f(parentX, parentY, parentZ);

        x = size.x - (rand.nextFloat()*2*size.x);
        y = -size.y;
        z = size.z - (rand.nextFloat()*2*size.z);
        pivot = new Vector3f(x, y, z);

        //rotations = new float[] {0f, (float) (rand.nextFloat()*2*Math.PI), 0f};
        if (rand.nextBoolean())
        {
          parentAxis = Vector3f.UNIT_X;
          axis = Vector3f.UNIT_X;
        }
        else
        {
          parentAxis = Vector3f.UNIT_Z;
          axis = Vector3f.UNIT_Z;
        }
        break;
      case 3: // XZ plane 2
        parentX = (parent.getSizeX()/2) - (rand.nextFloat()*parent.getSizeX());
        parentY = -parentSize.y;
        parentZ = (parent.getSize()/2) - (rand.nextFloat()*parent.getSize());
        parentPivot = new Vector3f(parentX, parentY, parentZ);

        x = size.x - (rand.nextFloat()*2*size.x);
        y = size.y;
        z = size.z - (rand.nextFloat()*2*size.z);
        pivot = new Vector3f(x, y, z);

        //rotations = new float[] {0f, (float) (rand.nextFloat()*2*Math.PI), 0f};
        if (rand.nextBoolean())
        {
          parentAxis = Vector3f.UNIT_X;
          axis = Vector3f.UNIT_X;
        }
        else
        {
          parentAxis = Vector3f.UNIT_Z;
          axis = Vector3f.UNIT_Z;
        }
        break;
      case 4: // YZ plane 1
        parentX = parentSize.x;
        parentY = (parent.getSizeY()/2) - (rand.nextFloat()*parent.getSizeY());
        parentZ = (parent.getSize()/2) - (rand.nextFloat()*parent.getSize());
        parentPivot = new Vector3f(parentX, parentY, parentZ);

        x = -size.x;
        y = size.y - (rand.nextFloat()*2*size.y);
        z = size.z - (rand.nextFloat()*2*size.z);
        pivot = new Vector3f(x, y, z);

        //rotations = new float[] {(float) (rand.nextFloat()*2*Math.PI), 0f, 0f};
        if (rand.nextBoolean())
        {
          parentAxis = Vector3f.UNIT_Y;
          axis = Vector3f.UNIT_Y;
        }
        else
        {
          parentAxis = Vector3f.UNIT_Z;
          axis = Vector3f.UNIT_Z;
        }
        break;
      default: // YZ plane 2
        parentX = -parentSize.x;
        parentY = (parent.getSizeY()/2) - (rand.nextFloat()*parent.getSizeY());
        parentZ = (parent.getSize()/2) - (rand.nextFloat()*parent.getSize());
        parentPivot = new Vector3f(parentX, parentY, parentZ);

        x = size.x;
        y = size.y - (rand.nextFloat()*2*size.y);
        z = size.z - (rand.nextFloat()*2*size.z);
        pivot = new Vector3f(x, y, z);

        //rotations = new float[] {(float) (rand.nextFloat()*2*Math.PI), 0f, 0f};
        if (rand.nextBoolean())
        {
          parentAxis = Vector3f.UNIT_Y;
          axis = Vector3f.UNIT_Y;
        }
        else
        {
          parentAxis = Vector3f.UNIT_Z;
          axis = Vector3f.UNIT_Z;
        }
        break;
    }
    return addBlock(rotations, size, parent, parentPivot, pivot, parentAxis, axis);
  }

  private float genRandDim(Random rand)
  {
    return RandomCreatureParameters.MIN_BLOCK_DIM + (rand.nextFloat() * (RandomCreatureParameters.MAX_BLOCK_DIM - RandomCreatureParameters.MIN_BLOCK_DIM));
  }

  private Vector3f genRandSize(Random rand)
  {
    float width = genRandDim(rand); // x dimension
    float height = genRandDim(rand); // y dimension
    float depth = genRandDim(rand); // z dimension
    return new Vector3f(width/2, height/2, depth/2);
  }

  private int getDepth(Block block, int depth)
  {
    int parentID = block.getIdOfParent();
    if (parentID == -1)
    {
      return depth;
    }

    return getDepth(getBlockByID(parentID), depth+1);
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
    neuron.setInputValue(Neuron.A, rand.nextInt(RandomCreatureParameters.MAX_NEURON_VALUE));
    neuron.setInputValue(Neuron.B, rand.nextInt(RandomCreatureParameters.MAX_NEURON_VALUE));
    neuron.setInputValue(Neuron.C, rand.nextInt(RandomCreatureParameters.MAX_NEURON_VALUE));
    neuron.setInputValue(Neuron.D, rand.nextInt(RandomCreatureParameters.MAX_NEURON_VALUE));
    neuron.setInputValue(Neuron.E, rand.nextInt(RandomCreatureParameters.MAX_NEURON_VALUE));

    return neuron;
  }

  private EnumNeuronInput getRandInput(Random rand)
  {
    return EnumNeuronInput.values()[rand.nextInt(EnumNeuronInput.values().length)];
  }

  private boolean validCreature()
  {
    Block block1;
    Block block2;
    for (int i = 0; i < getNumberOfBodyBlocks(); i++)
    {
      block1 = getBlockByID(i);
      for(int j = 0; j < getNumberOfBodyBlocks(); j++)
      {
        block2 = getBlockByID(j);
        if (canCollide(block1, block2))
        {
          if (getCollision(block1, block2).size() > 0)
          {
            return false;
          }
        }
      }
    }
    return true;
  }

  private CollisionResults getCollision(Block block1, Block block2)
  {
    CollisionResults results = new CollisionResults();
    block1.getGeometry().collideWith(block2.getGeometry().getWorldBound(), results);
    return results;
  }

  private boolean isParent(Block block1, Block block2)
  {
    return block1.getID() == block2.getIdOfParent();
  }

  private boolean canCollide(Block block1, Block block2)
  {
    return !(block1.getID() == block2.getID()) && !(isParent(block1, block2) || isParent(block2, block1));
  }
}
