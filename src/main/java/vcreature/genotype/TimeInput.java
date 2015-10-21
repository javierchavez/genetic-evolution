package vcreature.genotype;


public class TimeInput implements NeuralInput<Float>
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
}
