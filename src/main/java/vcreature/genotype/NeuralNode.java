package vcreature.genotype;


import vcreature.phenotype.EnumOperator;

import java.util.HashMap;

public class NeuralNode
{
  private EnumOperator neuronType;
  private HashMap<Character, NeuronInput> inputs;

  public NeuralNode()
  {
  }

  public EnumOperator getNeuronType()
  {
    return neuronType;
  }

  public void setNeuronType(EnumOperator neuronType)
  {
    this.neuronType = neuronType;
  }
}
