package vcreature.genotype;

/**
 * Rigid Part
 */
public class Gene extends AbstractGene<Gene>
{
  private float length;
  private float width;
  private float height;
  private int recursiveLimit = 1;

  // Spec. page 13 slide 26
  private AngleSensor angleSensor;
  private TouchSensor touchSensor;
  private HeightSensor heightSensor;

  private Effector effector;

  // location of the joint, maybe different data type
  private float jointSite;



  public Gene()
  {
    super(GeneType.BLOCK);
    angleSensor = new AngleSensor();
    touchSensor = new TouchSensor();
    heightSensor = new HeightSensor();
    effector = new Effector();

  }


  public AngleSensor getAngleSensor()
  {
    return angleSensor;
  }

  public void setAngleSensor(AngleSensor angleSensor)
  {
    this.angleSensor = angleSensor;
  }

  public TouchSensor getTouchSensor()
  {
    return touchSensor;
  }

  public void setTouchSensor(TouchSensor touchSensor)
  {
    this.touchSensor = touchSensor;
  }

  public HeightSensor getHeightSensor()
  {
    return heightSensor;
  }

  public void setHeightSensor(HeightSensor heightSensor)
  {
    this.heightSensor = heightSensor;
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
    this.length = length;
    this.width = width;
    this.height = height;
  }


  @Override
  public Gene clone()
  {
    Gene _newGene = new Gene();
    _newGene.setDimensions(length, width, height);
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

  public void setSenors(TouchSensor touchSensor, HeightSensor heightSensor, AngleSensor angleSensor)
  {
    this.touchSensor = touchSensor;
    this.heightSensor = heightSensor;
    this.angleSensor = angleSensor;
  }

  @Override
  public String toString()
  {
    return "|B()|";
  }
}
