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

  @Override
  public NeuralNode clone()
  {
    NeuralNode neuron = new NeuralNode();

    neuron.setInput(InputPosition.A, inputs.get(InputPosition.A));
    neuron.setInput(InputPosition.B, inputs.get(InputPosition.B));
    neuron.setInput(InputPosition.C, inputs.get(InputPosition.C));
    neuron.setInput(InputPosition.D, inputs.get(InputPosition.D));
    neuron.setInput(InputPosition.E, inputs.get(InputPosition.E));

    neuron.setOperator(operators.get(NeuralOperatorPosition.FIRST), NeuralOperatorPosition.FIRST);
    neuron.setOperator(operators.get(NeuralOperatorPosition.SECOND), NeuralOperatorPosition.SECOND);
    neuron.setOperator(operators.get(NeuralOperatorPosition.THIRD), NeuralOperatorPosition.THIRD);
    neuron.setOperator(operators.get(NeuralOperatorPosition.FOURTH), NeuralOperatorPosition.FOURTH);
    return neuron;
  }

  public enum NeuralOperatorPosition
  {
    FIRST,SECOND,THIRD,FOURTH
  }
}
