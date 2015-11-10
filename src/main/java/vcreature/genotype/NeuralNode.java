package vcreature.genotype;

/**
 * @author Javier Chavez
 * @author Alex Baker
 * @author Dominic Salas
 * @author Carrie Martinez
 * <p>
 * Date November 4, 2015
 * CS 351
 * Genetic Evolution
 */


import vcreature.genotype.NeuralInput.InputPosition;
import vcreature.phenotype.EnumOperator;
import vcreature.phenotype.Neuron;
import vcreature.utils.Savable;

import java.util.HashMap;


/**
 * This class is the representation of a Neuron in the genotype
 */
public class NeuralNode implements EffectorInput, Savable
{
  private HashMap<InputPosition, NeuralInput> inputs;
  private HashMap<NeuralOperatorPosition, EnumOperator> operators;

  /**
   * Create a new instance of the neural node
   */
  public NeuralNode()
  {
    inputs = new HashMap<>(Neuron.TOTAL_INPUTS);
    operators = new HashMap<>(EnumOperator.SIZE);
  }

  /**
   * Get the map of inputs and their position in the neuron
   *
   * @return map of neurons and inputs
   */
  public HashMap<InputPosition, NeuralInput> getInputs()
  {
    return this.inputs;
  }

  /**
   * Sets the input to the specified value for the given position
   *
   * @param input the position in the neuron the value should be saved to
   * @param value the value this position in the neuron should take
   */
  public void setInput (InputPosition input, NeuralInput value)
  {
    inputs.put(input, value);
  }

  /**
   * Get the operators for the neuron
   *
   * @return a map of operators and their positions
   */
  public HashMap<NeuralOperatorPosition, EnumOperator> getOperators()
  {
    return operators;
  }

  /**
   * Set the operator map to an existing one
   *
   * @param operators an existing map of operators to apply to this neuron
   */
  public void setOperators(HashMap<NeuralOperatorPosition, EnumOperator> operators)
  {
    this.operators = operators;
  }

  /**
   * Set the operator for the neuron at the given operator position
   *
   * @param operator operator to apply to the neuron
   * @param operatorPosition the position in the operator list to set the operator
   */
  public void setOperator(EnumOperator operator, NeuralOperatorPosition operatorPosition)
  {
    operators.put(operatorPosition, operator);
  }

  /**
   * This enum represents the different positions an operator can be in a neuron
   */
  public enum NeuralOperatorPosition
  {
    FIRST,SECOND,THIRD,FOURTH
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

  @Override
  public void write(StringBuilder s)
  {
    inputs.get(InputPosition.A).write(s);
    inputs.get(InputPosition.B).write(s);
    inputs.get(InputPosition.C).write(s);
    inputs.get(InputPosition.D).write(s);
    inputs.get(InputPosition.E).write(s);
    s.append(operators.get(NeuralOperatorPosition.FIRST)).append(",");
    s.append(operators.get(NeuralOperatorPosition.SECOND)).append(",");
    s.append(operators.get(NeuralOperatorPosition.THIRD)).append(",");
    s.append(operators.get(NeuralOperatorPosition.FOURTH)).append(",");
  }

  @Override
  public void read(StringBuilder s)
  {
    String type;
    NeuralInput input;
    for (InputPosition position : InputPosition.values())
    {
      type = s.substring(0, s.indexOf(":"));
      input = getInput(type);
      inputs.put(position, input);
      s.delete(0, type.length()+2);
      input.read(s);
    }

    String operator;
    EnumOperator op;
    for (NeuralOperatorPosition position : NeuralOperatorPosition.values())
    {
      operator = s.substring(0, s.indexOf(","));
      op = EnumOperator.valueOf(operator);
      operators.put(position, op);
      s.delete(0, operator.length()+2);
    }
  }

  private NeuralInput getInput(String s)
  {
    switch (s)
    {
      case "TIME":
        return new TimeInput();
      case "ANGLE":
        return new AngleSensor(null);
      case "HEIGHT":
        return new HeightSensor(null);
      case "TOUCH":
        return new TouchSensor(null);
      default:
        return new ConstantInput();
    }
  }
}
