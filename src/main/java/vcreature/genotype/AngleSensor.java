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


public class AngleSensor extends Sensor<AngleSensor, Float>
{
  /**
   * Senors shall only be instantiated by Genes
   */
  protected AngleSensor(Gene source)
  {
    super(source);
  }
}
