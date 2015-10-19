package vcreature.genotype;

/**
 * Rigid Part
 */
public class Gene extends AbstractGene<Gene>
{
  private float length;
  private float width;
  private float height;

  // Spec. page 13 slide 26
  private Sensor angleSensor;
  private Sensor touchSensor;
  private Sensor heightSensor;

  // TODO: Does the effector go here?
  private Effector effector;
  private float jointSite;



  public Gene()
  {
    super(GeneType.BLOCK);
    angleSensor = new AngleSensor();
    touchSensor = new TouchSensor();
    heightSensor = new HeightSensor();
    effector = new Effector();
    ConstantInput constant = new ConstantInput();

    // Sims 3.3 first sentence
    //    effector.addConnection(touchSensor);
    // or to add constant
    //    effector.addConnection(constant);
    //  }

    //  public Sensor getSensor()
    //  {
    //    return sensor;
    //  }
    //
    //  public void setSensor(Sensor sensor)
    //  {
    //    this.sensor = sensor;
  }


  public Sensor getAngleSensor()
  {
    return angleSensor;
  }

  public void setAngleSensor(Sensor angleSensor)
  {
    this.angleSensor = angleSensor;
  }

  public Sensor getTouchSensor()
  {
    return touchSensor;
  }

  public void setTouchSensor(Sensor touchSensor)
  {
    this.touchSensor = touchSensor;
  }

  public Sensor getHeightSensor()
  {
    return heightSensor;
  }

  public void setHeightSensor(Sensor heightSensor)
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
}
