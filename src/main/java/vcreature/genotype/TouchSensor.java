package vcreature.genotype;

/**
 * Sensor for touch.
 *
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
}
