package vcreature.Algorithms;


import vcreature.genotype.NeuralNode;
import vcreature.phenotype.EnumOperator;

public class NeuronClimbStrategy<V> implements HillClimbStrategy<NeuralNode, V>
{

  @Override
  public V climb(NeuralNode part)
  {


    EnumOperator pos = part.getOperators().get(NeuralNode.NeuralOperatorPosition.FIRST);

    if (pos == EnumOperator.IDENTITY)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FIRST, EnumOperator.ABS);
    }



    return null;
  }
}
