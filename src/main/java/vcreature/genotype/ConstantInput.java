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


import vcreature.utils.Savable;


/**
 * This class is used to represent the EnumNeuronInput CONSTANT in the genotype
 */
public class ConstantInput implements NeuralInput<Float>, EffectorInput, Savable
{
  private float constant;

  @Override
  public Float getValue()
  {
    return constant;
  }

  @Override
  public ConstantInput setValue(Float value)
  {
    constant = value;
    return this;
  }


  @Override
  public void write(StringBuilder s)
  {
    s.append("CONSTANT");
    s.append("[]").append(":");
    s.append(getValue()).append(",");
  }

  @Override
  public void read(StringBuilder s)
  {
    setValue(Float.parseFloat(s.toString()));
  }
}
