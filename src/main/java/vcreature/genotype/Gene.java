package vcreature.genotype;

/**
 * Rigid Part our creature this will be a Block
 */
public class Gene extends AbstractGene<Gene>
{
  private float lengthX;
  private float widthZ;
  private float heightY;


  private int position;
  private int recursiveLimit = 1;

  // Spec. page 13 slide 26
  private AngleSensor angleSensor;
  private TouchSensor touchSensor;
  private HeightSensor heightSensor;

  private Effector effector;



  public Gene(int position)
  {
    this();
    this.position = position;
  }

  private Gene()
  {
    super(GeneType.BLOCK);
    angleSensor = new AngleSensor(this);
    touchSensor = new TouchSensor(this);
    heightSensor = new HeightSensor(this);
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

  public float getLengthX()
  {
    return lengthX;
  }

  public void setLengthX(float lengthX)
  {
    this.lengthX = lengthX;
  }

  public float getWidthZ()
  {
    return widthZ;
  }

  public void setWidthZ(float widthZ)
  {
    this.widthZ = widthZ;
  }

  public float getHeightY()
  {
    return heightY;
  }

  public void setHeightY(float heightY)
  {
    this.heightY = heightY;
  }

  public void setDimensions(float x, float z, float y)
  {
    this.lengthX = x;
    this.widthZ = z;
    this.heightY = y;
  }


  @Override
  public Gene clone()
  {
    Gene _newGene = new Gene();
    _newGene.setDimensions(lengthX, widthZ, heightY);
    _newGene.setEffector(effector);

    _newGene.setTouchSensor(touchSensor);
    _newGene.setAngleSensor(angleSensor);
    _newGene.setHeightSensor(heightSensor);

    return _newGene;
  }

  @Override
  public String toString()
  {
    return "|B()|";
  }
}
