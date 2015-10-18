package vcreature.genotype;


public class TimeInput implements NeuronInput<Float>
{

  private float time;

  @Override
  public Float getValue()
  {
    return time;
  }

  @Override
  public void setValue(Float value)
  {
    this.time = value;
  }
}
