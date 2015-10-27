package vcreature.genotype;

import com.jme3.math.Vector3f;

/**
 * Rigid Part our creature this will be a Block
 */
public class Gene extends AbstractGene<Gene>
{
  private float lengthX;
  private float widthZ;
  private float heightY;

  private float rotationY = 0f; // rotation of the block
  private float rotationZ = 0f;
  private float rotationX = 0f;

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

  public void setDimensions(Vector3f size)
  {
    this.lengthX = 2*size.x;
    this.heightY = 2*size.y;
    this.widthZ = 2*size.z;
  }

  public void getDimensions(Vector3f size)
  {
    size.x = lengthX / 2;
    size.y = heightY / 2;
    size.z = widthZ / 2;
  }

  public void setRotations(float[] rotations)
  {
    rotationY = rotations[0];
    rotationZ = rotations[1];
    rotationX = rotations[2];
  }

  public void getRotation(float[] rotations)
  {
    rotations[0] = rotationY;
    rotations[1] = rotationZ;
    rotations[2] = rotationX;
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
