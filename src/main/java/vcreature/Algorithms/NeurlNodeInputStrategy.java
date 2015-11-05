package vcreature.Algorithms;


import vcreature.genotype.NeuralInput;
import vcreature.genotype.NeuralNode;

public class NeurlNodeInputStrategy<V> implements HillClimbStrategy<NeuralNode, V>
{
  @Override
  public V climb(NeuralNode part)
  {
    // change the inputs of the node


    part.getInputs().get(NeuralInput.InputPosition.A);
    return null;
  }
}
