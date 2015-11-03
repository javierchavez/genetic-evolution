package vcreature.genotype;


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
