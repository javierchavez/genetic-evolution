package vcreature.genotype;


import vcreature.genotype.NeuralInput.InputPosition;
import vcreature.phenotype.EnumOperator;
import vcreature.phenotype.Neuron;

import java.util.HashMap;

/**
 *  This is a Neuron
 */
public class NeuralNode implements EffectorInput
{
  private HashMap<InputPosition, NeuralInput> inputs;
  private HashMap<NeuralOperatorPosition, EnumOperator> operators;

  public NeuralNode()
  {
    inputs = new HashMap<>(Neuron.TOTAL_INPUTS);
    operators = new HashMap<>(EnumOperator.SIZE);
  }



  public HashMap<InputPosition, NeuralInput> getInputs()
  {
    return this.inputs;
  }

  public void setInput (InputPosition input, NeuralInput value)
  {
    inputs.put(input, value);
  }

  public HashMap<NeuralOperatorPosition, EnumOperator> getOperators()
  {
    return operators;
  }

  public void setOperators(HashMap<NeuralOperatorPosition, EnumOperator> operators)
  {
    this.operators = operators;
  }

  public void setOperator(EnumOperator operator, NeuralOperatorPosition operatorPosition)
  {
    operators.put(operatorPosition,operator);
  }

  public enum NeuralOperatorPosition
  {
    FIRST,SECOND,THIRD,FOURTH
  }
}
