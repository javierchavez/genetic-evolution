package vcreature.genotype;

/**
 * Rigid Part
 */
public class BlockGene extends Gene<BlockGene>
{
  private float length;
  private float width;
  private float height;
  private Sensor angleSensor;
  private Sensor touchSensor;
  private Sensor heightSensor;
  private Effector effector;
  private float jointSite;

  public BlockGene()
  {
    super(GeneType.BLOCK);
    angleSensor = new AngleSensor();
    touchSensor = new TouchSensor();
    heightSensor = new HeightSensor();
    effector = new Effector();
    ConstantInput constant = new ConstantInput();


  }

  //  public Sensor getSensor()
  //  {
  //    return sensor;
  //  }
  //
  //  public void setSensor(Sensor sensor)
  //  {
  //    this.sensor = sensor;
  //  }

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
    _newGene.setDimensions(length,width,height);
    _newGene.setEffector(effector);
    // _newGene.setSensor(sensor);
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
