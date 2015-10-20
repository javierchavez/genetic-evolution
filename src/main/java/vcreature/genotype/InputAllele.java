package vcreature.genotype;

import vcreature.phenotype.EnumNeuronInput;

/**
 * Character is the
 */
public class InputAllele<V> extends AbstractAllele<InputAllele<V>, V>
{
  private int degreeOfFreedom;
  private int blockIndex;
  private float constant;

  public int getDegreeOfFreedom()
  {
    return degreeOfFreedom;
  }

  public void setDegreeOfFreedom(int degreeOfFreedom)
  {
    this.degreeOfFreedom = degreeOfFreedom;
  }

  public int getBlockIndex()
  {
    return blockIndex;
  }

  public void setBlockIndex(int blockIndex)
  {
    this.blockIndex = blockIndex;
  }

  public float getConstant()
  {
    return constant;
  }

  public void setConstant(float constant)
  {
    this.constant = constant;
  }


  private void setInputValues(EnumNeuronInput neuronInput)
  {
    switch(neuronInput)
    {
      case TOUCH:
      case HEIGHT:
        // set block index
        break;
      case CONSTANT:
        // set const
        break;
      case JOINT:
        // set block index
        // degreee of free
        break;
      case TIME:
      default:
        break;
    }
  }

  @Override
  public InputAllele<V> clone()
  {
    return null;
  }
}
