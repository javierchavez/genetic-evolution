package vcreature.genotype;

import com.jme3.math.Vector3f;
import vcreature.utils.Savable;

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

  public int getPosition()
  {
    return position;
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
    Gene _newGene = new Gene(position);
    _newGene.setDimensions(lengthX, widthZ, heightY);
    _newGene.setRotations(new float[] {rotationY, rotationZ, rotationX});
    _newGene.setEffector(effector.clone());
    _newGene.setTouchSensor(touchSensor.clone());
    _newGene.setAngleSensor(angleSensor.clone());
    _newGene.setHeightSensor(heightSensor.clone());

    for (Integer edge : getEdges())
    {
      _newGene.addEdge(edge);
    }

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
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }

    Gene gene = (Gene) o;

    if (Float.compare(gene.lengthX, lengthX) != 0)
    {
      return false;
    }
    if (Float.compare(gene.widthZ, widthZ) != 0)
    {
      return false;
    }
    if (Float.compare(gene.heightY, heightY) != 0)
    {
      return false;
    }
    if (Float.compare(gene.rotationY, rotationY) != 0)
    {
      return false;
    }
    if (Float.compare(gene.rotationZ, rotationZ) != 0)
    {
      return false;
    }
    if (Float.compare(gene.rotationX, rotationX) != 0)
    {
      return false;
    }
    if (position != gene.position)
    {
      return false;
    }
    if (recursiveLimit != gene.recursiveLimit)
    {
      return false;
    }
    if (angleSensor != null ? !angleSensor.equals(gene.angleSensor) : gene.angleSensor != null)
    {
      return false;
    }
    if (touchSensor != null ? !touchSensor.equals(gene.touchSensor) : gene.touchSensor != null)
    {
      return false;
    }
    if (heightSensor != null ? !heightSensor.equals(gene.heightSensor) : gene.heightSensor != null)
    {
      return false;
    }
    return !(effector != null ? !effector.equals(gene.effector) : gene.effector != null);

  }

  @Override
  public int hashCode()
  {
    int result = (lengthX != +0.0f ? Float.floatToIntBits(lengthX) : 0);
    result = 31 * result + (widthZ != +0.0f ? Float.floatToIntBits(widthZ) : 0);
    result = 31 * result + (heightY != +0.0f ? Float.floatToIntBits(heightY) : 0);
    result = 31 * result + (rotationY != +0.0f ? Float.floatToIntBits(rotationY) : 0);
    result = 31 * result + (rotationZ != +0.0f ? Float.floatToIntBits(rotationZ) : 0);
    result = 31 * result + (rotationX != +0.0f ? Float.floatToIntBits(rotationX) : 0);
    result = 31 * result + position;
    result = 31 * result + recursiveLimit;
    result = 31 * result + (angleSensor != null ? angleSensor.hashCode() : 0);
    result = 31 * result + (touchSensor != null ? touchSensor.hashCode() : 0);
    result = 31 * result + (heightSensor != null ? heightSensor.hashCode() : 0);
    result = 31 * result + (effector != null ? effector.hashCode() : 0);
    return result;
  }

  @Override
  public void read(String s)
  {
    super.read(s);
  }
}
