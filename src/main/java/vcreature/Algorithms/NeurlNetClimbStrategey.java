package vcreature.Algorithms;

import vcreature.genotype.NeuralNode;

import java.util.ArrayList;


public class NeurlNetClimbStrategey<V> implements HillClimbStrategy<ArrayList<NeuralNode>, V>
{
  NeuronClimbStrategy strategy = new NeuronClimbStrategy<>();

  @Override
  public V climb(ArrayList<NeuralNode> part)
  {
    if (part.size() > 0)
    {
      strategy.climb(part.get(0));
    }
    return null;
  }
}
