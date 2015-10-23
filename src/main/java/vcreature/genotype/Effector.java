package vcreature.genotype;


import com.jme3.math.Vector3f;

import java.util.ArrayList;

/**
 * Effector is analogous to a joint
 */
public class Effector
{
  private float maxForce;
  private int jointParentIndex;
  private float parentX = 0f;
  private float parentY = 0f;
  private float parentZ = 0f;
  private float childX = 0f;
  private float childY = 0f;
  private float childZ = 0f;
  private float parentAxisX = 0f;
  private float parentAxisY = 0f;
  private float parentAxisZ = 0f;
  private float childAxisX = 0f;
  private float childAxisY = 0f;
  private float childAxisZ = 0f;


  // Slide 27 Each effector (degree of freedom of each joint) has a
  // sequence of 0 to n rules called the effector's rule table
  private ArrayList<NeuralNode> neuralNet;

  // Sims 3.3 first sentence
  // private EffectorInput inputSource;

  protected Effector()
  {
    this.maxForce = 0;
    neuralNet = new ArrayList<>();
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

  public void addNeuralNode(NeuralNode node)
  {
    neuralNet.add(node);
  }

  public void setNeuralNet(ArrayList<NeuralNode> neuralNet)
  {
    this.neuralNet = neuralNet;
  }

  public int getJointParentIndex()
  {
    return jointParentIndex;
  }

  public void setJointParentIndex(int jointParentIndex)
  {
    this.jointParentIndex = jointParentIndex;
  }

  public void setChildAxis(Vector3f vector3f)
  {
    childAxisX = vector3f.x;
    childAxisY = vector3f.y;
    childAxisZ = vector3f.z;
  }

  public void setParentAxis(Vector3f vector3f)
  {
    parentAxisX = vector3f.x;
    parentAxisY = vector3f.y;
    parentAxisZ = vector3f.z;
  }


  public void setChild(Vector3f vector3f)
  {
    childX = vector3f.x;
    childY = vector3f.y;
    childZ = vector3f.z;
  }


  public void setParent(Vector3f vector3f)
  {
    parentX = vector3f.x;
    parentY = vector3f.y;
    parentZ = vector3f.z;
  }


  public void getChildAxis(Vector3f vector3f)
  {
    vector3f.x = childAxisX;
    vector3f.y = childAxisY;
    vector3f.z = childAxisZ;
  }

  public void getParentAxis(Vector3f vector3f)
  {
    vector3f.x = parentAxisX;
    vector3f.y = parentAxisY;
    vector3f.z = parentAxisZ;
  }


  public void getChild(Vector3f vector3f)
  {
    vector3f.x = childX;
    vector3f.y = childY;
    vector3f.z = childZ;
  }


  public void getParent(Vector3f vector3f)
  {
    vector3f.x = parentX;
    vector3f.y = parentY;
    vector3f.z = parentZ;
  }

  /**
   * Source of the stimulation either from Sensor or Neural source
   *
   * @param inputSource
   */
//  public void addConnection(EffectorInput inputSource)
//  {
//    this.inputSource = inputSource;
//  }
//
//
//  public void addConnection()
//  {
//    this.inputSource = inputSource;
//  }
}
