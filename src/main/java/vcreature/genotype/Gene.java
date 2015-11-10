package vcreature.genotype;

/**
 * @author Javier Chavez
 * @author Alex Baker
 * @author Dominic Salas
 * @author Carrie Martinez
 * <p>
 * Date November 4, 2015
 * CS 351
 * Genetic Evolution
 */


import com.jme3.math.Vector3f;
import vcreature.utils.Savable;


/**
 * A gene is the representation of a block in the genotype. This class
 * is used to store data and attributes about a block used in the phenotype
 */
public class Gene extends AbstractGene<Gene> implements Savable
{
  public static int TOTAL = 0;
  
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
    TOTAL++;
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
   * @return get the angle sensor for this gene
   */
  public AngleSensor getAngleSensor()
  {
    return angleSensor;
  }

  /**
   * Set the angle sensor for the gene
   *
   * @param angleSensor the angle sensor for this gene to use
   */
  public void setAngleSensor(AngleSensor angleSensor)
  {
    this.angleSensor = angleSensor;
  }

  /**
   * Collision sensor for this gene
   *
   * @return the touchSensor for this gene
   */
  public TouchSensor getTouchSensor()
  {
    return touchSensor;
  }

  /**
   * Set the touch sensor for this gene to use
   *
   * @param touchSensor new touch sensor for this gene to use
   */
  public void setTouchSensor(TouchSensor touchSensor)
  {
    this.touchSensor = touchSensor;
  }

  /**
   * Sensor for monitoring height
   *
   * @return sensor for height for this gene
   */
  public HeightSensor getHeightSensor()
  {
    return heightSensor;
  }

  /**
   * Set the height sensor this gene uses
   *
   * @param heightSensor new heightSnesor to set for this gene to use
   */
  public void setHeightSensor(HeightSensor heightSensor)
  {
    this.heightSensor = heightSensor;
  }

  /**
   * Get the effector or joint for this gene
   *
   * @return effector or joint for this gene
   */
  public Effector getEffector()
  {
    return effector;
  }

  /**
   * set a new effector for this gene to use
   *
   * @param effector new effector to apply to the gene
   */
  public void setEffector(Effector effector)
  {
    this.effector = effector;
  }

  /**
   * The size along the x axis of this gene (block)
   *
   * @return size along the x axis
   */
  public float getLengthX()
  {
    return lengthX;
  }

  /**
   * Set the size of the gene along the x axis
   *
   * @param lengthX length to apply to the x axis of the gene
   */
  public void setLengthX(float lengthX)
  {
    this.lengthX = lengthX;
  }

  /**
   * The size along the z axis of this gene (block)
   *
   * @return size along the z axis
   */
  public float getWidthZ()
  {
    return widthZ;
  }

  /**
   * Set the size of the gene along the z axis
   *
   * @param widthZ width to apply to the z axis of the gene
   */
  public void setWidthZ(float widthZ)
  {
    this.widthZ = widthZ;
  }

  /**
   * The size along the y axis of this gene (block)
   *
   * @return size along the y axis
   */
  public float getHeightY()
  {
    return heightY;
  }

  /**
   * Set the size of the gene along the y axis
   *
   * @param heightY height to apply to the y axis of the gene
   */
  public void setHeightY(float heightY)
  {
    this.heightY = heightY;
  }

  /**
   * Set the dimensions of the gene
   * NOTE: this is the true dimension as these values are not halved
   *
   * @param x size along x axis
   * @param z size along z axis
   * @param y size along y axis
   */
  public void setDimensions(float x, float z, float y)
  {
    this.lengthX = x;
    this.widthZ = z;
    this.heightY = y;
  }

  /**
   * Set the dimensions of the gene. This is the size
   * relative to the center of the block, so the
   * values are doubled
   *
   * @param size vector with size along the x,y,z axis
   */
  public void setDimensions(Vector3f size)
  {
    this.lengthX = 2*size.x;
    this.heightY = 2*size.y;
    this.widthZ = 2*size.z;
  }

  /**
   * Get the dimensions of the gene relative to the center of the block.
   * This means the dimenions are half of their true size.
   *
   * @param size vector to assign the x,y,z sizes to
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
   * @param rotations array of rotations [y,z,x]
   */
  public void setRotations(float[] rotations)
  {
    rotationY = rotations[0];
    rotationZ = rotations[1];
    rotationX = rotations[2];
  }

  /**
   * Get the position of the gene in the genome
   *
   * @return position (index) of the gene in the genome
   */
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
  public void read(StringBuilder s)
  {
    super.read(s);
    String str = s.toString();
    String[] split = str.split("[,]");
    for (int i = 0; i < split.length; i++)
    {
      if (Character.isDigit(split[i].charAt(0)))
      {
        int num = (int)Float.parseFloat(split[i]);
        addEdge(num);
      }
    }
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
  public String toString()
  {
    return "|B()|";
  }
}
