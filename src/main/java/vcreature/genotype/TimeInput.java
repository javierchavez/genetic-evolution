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
 * This class represent the EnumNeuronInput TIME in the genotype
 */
public class TimeInput implements NeuralInput<Float>, Savable
{
  private float time;

  @Override
  public Float getValue()
  {
    return time;
  }

  @Override
  public TimeInput setValue(Float value)
  {
    this.time = value;
    return this;
  }

  @Override
  public void write(StringBuilder s)
  {
    s.append(time).append(",");
  }

  @Override
  public void read(String s)
  {
    String value = s.substring(0, s.indexOf(","));
    setValue(Float.parseFloat(value));
    s.replaceFirst(value+",", "");
  }
}
