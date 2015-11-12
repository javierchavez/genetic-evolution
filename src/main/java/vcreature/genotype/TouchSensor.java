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


/**
 * Sensor for touch.
 * This sensor is the genotype representation for the EnumNeuronInput TOUCH
 */
public class TouchSensor extends Sensor<TouchSensor, Float>
{
  /**
   * Senors shall only be instantiated by Genes
   */
  protected TouchSensor(Gene source)
  {
    super(source);
  }

  @Override
  public void write(StringBuilder s)
  {
    s.append("TOUCH");
    s.append("["+getSource().getPosition()+"]").append(":");
    s.append(getValue()).append(",");
  }

  @Override
  public void read(StringBuilder s)
  {
    String value = s.substring(0, s.indexOf(","));
    setValue(Float.parseFloat(s.toString()));
  }
}
