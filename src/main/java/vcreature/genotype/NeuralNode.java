package vcreature.genotype;


import vcreature.phenotype.EnumOperator;
import vcreature.genotype.NeuralInput.InputPosition;

import java.util.HashMap;

/**
 *
 *
 */
public class NeuralNode implements EffectorInput
{
  private HashMap<InputPosition, NeuralInput> inputs;
  private HashMap<NeuralOperatorPosition, EnumOperator> operators;

  public NeuralNode()
  {
    inputs = new HashMap<>();
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
