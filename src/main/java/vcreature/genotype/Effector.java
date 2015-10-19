package vcreature.genotype;


import java.util.ArrayList;

public class Effector
{
  private float minLimit;
  private float maxLimit;
  private float maxForce;

  // Slide 27 Each effector (degree of freedom of each joint) has a
  // sequence of 0 to n rules called the effector's rule table
  private ArrayList<NeuralNode> neuralNet;

  // Sims 3.3 first sentence
  // private EffectorInput inputSource;

  public Effector()
  {
    this.minLimit = this.maxLimit = this.maxForce = 0;
    neuralNet = new ArrayList<>();
  }

  public float getMinLimit()
  {
    return minLimit;
  }

  public void setMinLimit(float minLimit)
  {
    this.minLimit = minLimit;
  }

  public float getMaxLimit()
  {
    return maxLimit;
  }

  public void setMaxLimit(float maxLimit)
  {
    this.maxLimit = maxLimit;
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
