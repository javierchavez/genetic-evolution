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


/**
 * Sensor for Height
 */
public class HeightSensor extends Sensor<HeightSensor, Float>
{

  /**
   * Senors shall only be instantiated by Genes
   */
  protected HeightSensor(Gene source)
  {
    super(source);
  }
}
