package vcreature.genotype;


import vcreature.phenotype.EnumOperator;

import java.util.HashMap;

/**
 * This is a neuron can be either unary or binary
 *
 */
public class NeuralNode implements EffectorInput
{
  private EnumOperator neuronFunction;
  private HashMap<Character, NeuralInput> inputs;

  public NeuralNode()
  {
    inputs = new HashMap<>();
  }

  public EnumOperator getNeuronFunction()
  {
    return neuronFunction;
  }

  public void setNeuronFunction(EnumOperator neuronFunction)
  {
    this.neuronFunction = neuronFunction;
  }

  public HashMap<Character, NeuralInput> getInputs()
  {
    return this.inputs;
  }

  public void setInput (Character input, NeuralInput value)
  {
    inputs.put(input, value);
  }
}
