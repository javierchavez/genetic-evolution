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
 * <p>
 * Module description here
 */


import vcreature.genotype.NeuralInput.InputPosition;
import vcreature.phenotype.EnumOperator;
import vcreature.phenotype.Neuron;
import vcreature.utils.Savable;

import java.util.HashMap;


/**
 *  This is a Neuron
 */
public class NeuralNode implements EffectorInput, Savable
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
  public void read(String s)
  {

  }

  public enum NeuralOperatorPosition
  {
    FIRST,SECOND,THIRD,FOURTH
  }
}
