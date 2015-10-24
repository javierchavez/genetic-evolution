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
  private float rotationY = 0f; // rotation of the block
  private float rotationZ = 0f;
  private float rotationX = 0f;
  private float parentX = 0f; // local coordinates of the joint on the parent block
  private float parentY = 0f;
  private float parentZ = 0f;
  private float childX = 0f; // local coordinates of the joint on the child block
  private float childY = 0f;
  private float childZ = 0f;
  private float pivotAxisX = 0f; // joel's new api only requires 1 axis to be specified for the joint
  private float pivotAxisY = 0f;
  private float pivotAxisZ = 0f;


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

  public void setPivotAxis(Vector3f vector3f)
  {
    pivotAxisX = vector3f.x;
    pivotAxisY = vector3f.y;
    pivotAxisZ = vector3f.z;
  }

  public void setRotations(float[] rotations)
  {
    rotationY = rotations[0];
    rotationZ = rotations[1];
    rotationX = rotations[2];
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

  public void getRotation(float[] rotations)
  {
    rotations[0] = rotationY;
    rotations[1] = rotationZ;
    rotations[2] = rotationX;
  }

  public void getPivotAxis(Vector3f vector3f)
  {
    vector3f.x = pivotAxisX;
    vector3f.y = pivotAxisY;
    vector3f.z = pivotAxisZ;
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
