package vcreature.genotype;


public class ConstantInput implements NeuralInput<Float>, EffectorInput
{
  private float constant;
  private InputPosition position;

  @Override
  public ConstantInput setInputPosition(InputPosition position)
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
    return constant;
  }

  @Override
  public ConstantInput setValue(Float value)
  {
    constant = value;
    return this;
  }
}
