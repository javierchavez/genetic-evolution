package vcreature.genotype;

/**
 * Rigid Part
 */
public class BlockGene extends Gene<BlockGene>
{
  private float length;
  private float width;
  private float height;
  private Sensor sensor;
  private Effector effector;
  private float jointSite;

  public BlockGene()
  {
    super(GeneType.BLOCK);
  }

  public Sensor getSensor()
  {
    return sensor;
  }

  public void setSensor(Sensor sensor)
  {
    this.sensor = sensor;
  }

  public Effector getEffector()
  {
    return effector;
  }

  public void setEffector(Effector effector)
  {
    this.effector = effector;
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
    this.length = length; this.width = width; this.height = height;
  }


  @Override
  public BlockGene clone()
  {
    BlockGene _newGene = new BlockGene();
    _newGene.width = width;
    _newGene.length = length;
    _newGene.height = height;
    return _newGene;
  }

  public float getJointSite()
  {
    return jointSite;
  }

  public void setJointSite(float jointSite)
  {
    this.jointSite = jointSite;
  }
}
