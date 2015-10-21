package vcreature.genotype;


import java.util.ArrayList;

public class Effector
{
  private float maxForce;
  private int jointParentIndex;


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
