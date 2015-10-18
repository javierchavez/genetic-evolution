package vcreature.genotype;


import vcreature.phenotype.EnumOperator;

import java.util.HashMap;

public class NeuralNode
{
  private EnumOperator neuronFunction;
  private HashMap<Character, NeuronInput> inputs;

  public NeuralNode()
  {
    inputs = new HashMap<>(5);
    inputs.put('A', null);
    inputs.put('B', null);
    inputs.put('C', null);
    inputs.put('D', null);
    inputs.put('E', null);
  }

  public EnumOperator getNeuronFunction()
  {
    return neuronFunction;
  }

  public void setNeuronFunction(EnumOperator neuronFunction)
  {
    this.neuronFunction = neuronFunction;
  }

  public HashMap<Character, NeuronInput> getInputs()
  {
    return this.inputs;
  }

  public void setInput (Character input, NeuronInput value)
  {
    inputs.put(input, value);
  }
}
