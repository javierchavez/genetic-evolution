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


import vcreature.utils.Savable;


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

  }
}
