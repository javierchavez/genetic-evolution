package vcreature.genotype;


public class TimeInput implements NeuralInput<Float>
{

  private float time;
  private InputPosition position;

  @Override
  public TimeInput setInputPosition(InputPosition position)
  {
    this.position = position;
    return this;
  }

  @Override
  public InputPosition getInputPosition()
  {
    return position;
  }


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
