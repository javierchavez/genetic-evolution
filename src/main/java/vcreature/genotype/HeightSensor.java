package vcreature.genotype;


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
