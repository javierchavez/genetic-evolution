package vcreature.genotype;

import com.jme3.math.Vector3f;
import vcreature.utils.Savable;

import java.util.ArrayList;

/**
 * Rigid Part our creature this will be a Block
 */
public class Gene extends AbstractGene<Gene> implements Savable
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

  /**
   * Create a gene with index position.
   *
   * @param position location of gene in list
   */
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


  /**
   * Sensor for hinge joint
   *
   * @return sensor
   */
  public AngleSensor getAngleSensor()
  {
    return angleSensor;
  }

  public void setAngleSensor(AngleSensor angleSensor)
  {
    this.angleSensor = angleSensor;
  }

  /**
   * Collision sensor
   *
   * @return sensor determining collision
   */
  public TouchSensor getTouchSensor()
  {
    return touchSensor;
  }

  public void setTouchSensor(TouchSensor touchSensor)
  {
    this.touchSensor = touchSensor;
  }


  /**
   * Sensor for monitoring height
   *
   * @return sensor for height
   */
  public HeightSensor getHeightSensor()
  {
    return heightSensor;
  }

  public void setHeightSensor(HeightSensor heightSensor)
  {
    this.heightSensor = heightSensor;
  }

  /**
   * Effector is a joint
   *
   * @return effector or joint
   */
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

  /**
   * Set the dimensions of the gene
   *
   * @param size input
   */
  public void setDimensions(Vector3f size)
  {
    this.lengthX = 2*size.x;
    this.heightY = 2*size.y;
    this.widthZ = 2*size.z;
  }

  /**
   * Get the dimensions of the gene
   *
   * @param size output
   */
  public void getDimensions(Vector3f size)
  {
    size.x = lengthX / 2;
    size.y = heightY / 2;
    size.z = widthZ / 2;
  }

  /**
   * Get the rotation of the the block
   *
   * @param rotations list of rotations [y,z,x]
   */
  public void setRotations(float[] rotations)
  {
    rotationY = rotations[0];
    rotationZ = rotations[1];
    rotationX = rotations[2];
  }

  /**
   * Get the rotation of the the block
   *
   * @param rotations list of rotations [y,z,x]
   */
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
    _newGene.setRotations(new float[] {rotationY, rotationZ, rotationX});
    _newGene.setEffector(effector.clone());
    _newGene.setTouchSensor(touchSensor.clone());
    _newGene.setAngleSensor(angleSensor.clone());
    _newGene.setHeightSensor(heightSensor.clone());
    _newGene.setEdges(new ArrayList<>(this.getEdges()));

    return _newGene;
  }

  @Override
  public String toString()
  {
    return "|B()|";
  }

  @Override
  public void write(StringBuilder s)
  {
    super.write(s);
    s.append(position).append(",");
    s.append(lengthX).append(",");
    s.append(widthZ).append(",");
    s.append(heightY).append(",");
    s.append(rotationY).append(",");
    s.append(rotationZ).append(",");
    s.append(rotationX).append(",");
    s.append(recursiveLimit).append(",");

    angleSensor.write(s);
    touchSensor.write(s);
    heightSensor.write(s);
    effector.write(s);

  }


  @Override
  public void read(String s)
  {
    super.read(s);
  }
}
