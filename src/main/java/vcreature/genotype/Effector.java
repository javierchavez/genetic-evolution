package vcreature.genotype;


import com.jme3.math.Vector3f;
import vcreature.utils.Savable;

import java.util.ArrayList;

/**
 * Effector is analogous to a joint
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

  public float getParentX()
  {
    return parentX;
  }

  public void setParentX(float parentX)
  {
    this.parentX = parentX;
  }

  public float getParentY()
  {
    return parentY;
  }

  public void setParentY(float parentY)
  {
    this.parentY = parentY;
  }

  public float getParentZ()
  {
    return parentZ;
  }

  public void setParentZ(float parentZ)
  {
    this.parentZ = parentZ;
  }

  public float getChildX()
  {
    return childX;
  }

  public void setChildX(float childX)
  {
    this.childX = childX;
  }

  public float getChildY()
  {
    return childY;
  }

  public void setChildY(float childY)
  {
    this.childY = childY;
  }

  public float getChildZ()
  {
    return childZ;
  }

  public void setChildZ(float childZ)
  {
    this.childZ = childZ;
  }

  public float getPivotAxisX()
  {
    return pivotAxisX;
  }

  public void setPivotAxisX(float pivotAxisX)
  {
    this.pivotAxisX = pivotAxisX;
  }

  public float getPivotAxisY()
  {
    return pivotAxisY;
  }

  public void setPivotAxisY(float pivotAxisY)
  {
    this.pivotAxisY = pivotAxisY;
  }

  public float getPivotAxisZ()
  {
    return pivotAxisZ;
  }

  public void setPivotAxisZ(float pivotAxisZ)
  {
    this.pivotAxisZ = pivotAxisZ;
  }

  public float getMaxForce()
  {
    return maxForce;
  }

  public void setMaxForce(float maxForce)
  {
    this.maxForce = maxForce;
  }

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
   * Set all the Nurons controlling this joint/Effector
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
   * @param jointParentIndex
   */
  public void setJointParentIndex(int jointParentIndex)
  {
    this.jointParentIndex = jointParentIndex;
  }

  /**
   * Set the axis to which the joint in aligned
   *
   * @param vector3f containing data
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
   * @param vector3f containing parent data
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
   * @param vector3f containing parent data
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
   * @param vector3f output
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
   * @param vector3f output
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
   * @param vector3f output
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
  public void read(String s)
  {

  }

}
