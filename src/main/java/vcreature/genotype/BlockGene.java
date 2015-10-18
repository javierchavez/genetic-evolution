package vcreature.genotype;

/**
 * Rigid Part
 */
public class BlockGene extends Gene<BlockGene>
{
  private float length;
  private float width;
  private float height;


  public BlockGene()
  {
    super(GeneType.BLOCK);
  }


  public float getLength()
  {
    return length;
  }

  public void setLength(float length)
  {
    this.length = length;
  }

  public float getWidth()
  {
    return width;
  }

  public void setWidth(float width)
  {
    this.width = width;
  }

  public float getHeight()
  {
    return height;
  }

  public void setHeight(float height)
  {
    this.height = height;
  }

  public void setDimensions(float length, float width, float height)
  {
    this.length = length;
    this.width = width;
    this.height = height;
  }


  @Override
  public BlockGene clone()
  {
    BlockGene _newGene = new BlockGene();
    _newGene.width = width;
    _newGene.length = length;
    _newGene.height = height;
    _newGene.setLinkedGeneWeights(cloneLinkedGeneWeights());
    return _newGene;
  }
}
