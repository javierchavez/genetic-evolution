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

import java.util.ArrayList;


/**
 * This class represents a hinge joint in the genotype
 */
public class Effector implements Savable
{
  private float maxForce;
  private int jointParentIndex;
  private float parentX = 0f; // local coordinates of the joint on the parent block
  private float parentY = 0f;
  private float parentZ = 0f;
  private float childX = 0f; // local coordinates of the joint on the child block
  private float childY = 0f;
  private float childZ = 0f;
  private float pivotAxisX = 0f; // axis of rotation for the joint
  private float pivotAxisY = 0f;
  private float pivotAxisZ = 0f;


  // Slide 27 Each effector (degree of freedom of each joint) has a
  // sequence of 0 to n rules called the effector's rule table
  private ArrayList<NeuralNode> neuralNet;

  // Sims 3.3 first sentence
  // private EffectorInput inputSource;

  /**
   * Effectors shall only be instantiated by Genes
   */
  protected Effector()
  {
    this.maxForce = 0;
    neuralNet = new ArrayList<>();
  }

  /**
   * Get the x location of the joint on the parent
   *
   * @return the x coordinate to the parent block
   */
  public float getParentX()
  {
    return parentX;
  }

  /**
   * Sets the x coordinate for the joint on the parent block
   *
   * @param parentX x coordinate of the joint on the parent block.
   *                this should be in local coordinates of the parent block
   */
  public void setParentX(float parentX)
  {
    this.parentX = parentX;
  }

  /**
   * Get the y location of the joint on the parent
   *
   * @return the y coordinate to the parent block
   */
  public float getParentY()
  {
    return parentY;
  }

  /**
   * Sets the y coordinate for the joint on the parent block
   *
   * @param parentY y coordinate of the joint on the parent block.
   *                this should be in local coordinates of the parent block
   */
  public void setParentY(float parentY)
  {
    this.parentY = parentY;
  }

  /**
   * Get the z location of the joint on the parent
   *
   * @return the z coordinate to the parent block
   */
  public float getParentZ()
  {
    return parentZ;
  }

  /**
   * Sets the z coordinate for the joint on the parent block
   *
   * @param parentZ z coordinate of the joint on the parent block.
   *                this should be in local coordinates of the parent block
   */
  public void setParentZ(float parentZ)
  {
    this.parentZ = parentZ;
  }

  /**
   * Get the x location of the joint on the child
   *
   * @return the x coordinate to the child block
   */
  public float getChildX()
  {
    return childX;
  }

  /**
   * Sets the x coordinate for the joint on the child block
   *
   * @param childX x coordinate of the joint on the child block.
   *                this should be in local coordinates of the child block
   */
  public void setChildX(float childX)
  {
    this.childX = childX;
  }

  /**
   * Get the y location of the joint on the child
   *
   * @return the y coordinate to the child block
   */
  public float getChildY()
  {
    return childY;
  }

  /**
   * Sets the y coordinate for the joint on the child block
   *
   * @param childY y coordinate of the joint on the child block.
   *                this should be in local coordinates of the child block
   */
  public void setChildY(float childY)
  {
    this.childY = childY;
  }

  /**
   * Get the z location of the joint on the child
   *
   * @return the z coordinate to the child block
   */
  public float getChildZ()
  {
    return childZ;
  }

  /**
   * Sets the z coordinate for the joint on the child block
   *
   * @param childZ z coordinate of the joint on the child block.
   *                this should be in local coordinates of the child block
   */
  public void setChildZ(float childZ)
  {
    this.childZ = childZ;
  }

  /**
   * Get the x axis of rotation of the joint
   *
   * @return the x axis which the joint is aligned to
   */
  public float getPivotAxisX()
  {
    return pivotAxisX;
  }

  /**
   * Sets the x axis which the joint is aligned to
   *
   * @param pivotAxisX x axis for the joint to align to.
   */
  public void setPivotAxisX(float pivotAxisX)
  {
    this.pivotAxisX = pivotAxisX;
  }

  /**
   * Get the y axis the joint is aligned to
   *
   * @return the y axis which the joint is aligned to
   */
  public float getPivotAxisY()
  {
    return pivotAxisY;
  }

  /**
   * Sets the y axis which the joint is aligned to
   *
   * @param pivotAxisY y axis for the joint to align to.
   */
  public void setPivotAxisY(float pivotAxisY)
  {
    this.pivotAxisY = pivotAxisY;
  }

  /**
   * Get the z axis the joint is aligned to
   *
   * @return the z axis which the joint is aligned to
   */
  public float getPivotAxisZ()
  {
    return pivotAxisZ;
  }

  /**
   * Sets the z axis which the joint can pivot around
   *
   * @param pivotAxisZ z axis for the joint to pivot around.
   */
  public void setPivotAxisZ(float pivotAxisZ)
  {
    this.pivotAxisZ = pivotAxisZ;
  }

  /**
   * Get the max force that can be applied to the joint
   *
   * @return max force
   */
  public float getMaxForce()
  {
    return maxForce;
  }

  /**
   * Sets the max force which can be applied to the joint
   *
   * @param maxForce maximum force the joint can output
   */
  public void setMaxForce(float maxForce)
  {
    this.maxForce = maxForce;
  }

  /**
   * Get the list of neurons for the effector
   *
   * @return a list of neurons currently in the effector
   */
  public ArrayList<NeuralNode> getNeuralNet()
  {
    return neuralNet;
  }

  /**
   * Append neuron to list
   *
   * @param node neuron to be appended.
   */
  public void addNeuralNode(NeuralNode node)
  {
    neuralNet.add(node);
  }

  /**
   * Set all the Neurons controlling this joint/Effector
   *
   * @param neuralNet list of neurons
   */
  public void setNeuralNet(ArrayList<NeuralNode> neuralNet)
  {
    this.neuralNet = neuralNet;
  }

  /**
   * Get the index to which this gene in attached to by a joint
   *
   * @return index of parent
   */
  public int getJointParentIndex()
  {
    return jointParentIndex;
  }

  /**
   * Set the index to which this gene in attached to by a joint
   *
   * @param jointParentIndex idex of the joint the child is attached to
   */
  public void setJointParentIndex(int jointParentIndex)
  {
    this.jointParentIndex = jointParentIndex;
  }

  /**
   * Set the axis to which the joint in aligned
   *
   * @param vector3f containing x,y,z coordinates of the axis
   */
  public void setPivotAxis(Vector3f vector3f)
  {
    pivotAxisX = vector3f.x;
    pivotAxisY = vector3f.y;
    pivotAxisZ = vector3f.z;
  }

  /**
   * Set the child joint attach location
   *
   * @param vector3f a vector is x,y,z coordinates for the attach location
   */
  public void setChild(Vector3f vector3f)
  {
    childX = vector3f.x;
    childY = vector3f.y;
    childZ = vector3f.z;
  }

  /**
   * Set the parent joint attach location
   *
   * @param vector3f a vector with x,y,z coordinates for the attach location
   */
  public void setParent(Vector3f vector3f)
  {
    parentX = vector3f.x;
    parentY = vector3f.y;
    parentZ = vector3f.z;
  }

  /**
   * Get the pivot of which the joint is aligned
   *
   * @param vector3f a vector which will be assigned values of the pivot axis
   */
  public void getPivotAxis(Vector3f vector3f)
  {
    vector3f.x = pivotAxisX;
    vector3f.y = pivotAxisY;
    vector3f.z = pivotAxisZ;
  }

  /**
   * Get location on the child of connection
   *
   * @param vector3f a vector which will be assigned values for the child joint location
   */
  public void getChild(Vector3f vector3f)
  {
    vector3f.x = childX;
    vector3f.y = childY;
    vector3f.z = childZ;
  }

  /**
   * Get location on the parent of connection
   *
   * @param vector3f a vector which will be assigned values for the parent joint location
   */
  public void getParent(Vector3f vector3f)
  {
    vector3f.x = parentX;
    vector3f.y = parentY;
    vector3f.z = parentZ;
  }

  @Override
  public Effector clone()
  {
    Effector effector = new Effector();
    effector.setMaxForce(maxForce);
    effector.setJointParentIndex(jointParentIndex);
    effector.setParent(new Vector3f(parentX, parentY, parentZ));
    effector.setChild(new Vector3f(childX, childY, childZ));
    effector.setPivotAxis(new Vector3f(pivotAxisX, pivotAxisY, pivotAxisZ));

    ArrayList<NeuralNode> newNeuralNet = new ArrayList<>();
    for (NeuralNode neuron : neuralNet)
    {
      newNeuralNet.add(neuron.clone());
    }
    effector.setNeuralNet(newNeuralNet);
    return effector;
  }

  @Override
  public void write(StringBuilder s)
  {
    s.append(maxForce).append(",");
    s.append(jointParentIndex).append(",");
    s.append(parentX).append(",");
    s.append(parentY).append(",");
    s.append(parentZ).append(",");
    s.append(childX).append(",");
    s.append(childY).append(",");
    s.append(childZ).append(",");
    s.append(pivotAxisX).append(",");
    s.append(pivotAxisY).append(",");
    s.append(pivotAxisZ).append(",");

    for (NeuralNode node : neuralNet)
    {
      node.write(s);
    }
  }

  @Override
  public void read(StringBuilder s)
  {

  }

}
