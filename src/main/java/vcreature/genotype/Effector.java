package vcreature.genotype;



import java.util.ArrayList;

public class Effector
{
  private float minLimit;
  private float maxLimit;
  private float maxForce;
  private ArrayList<NeuralNode> neuralNet;

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
}
