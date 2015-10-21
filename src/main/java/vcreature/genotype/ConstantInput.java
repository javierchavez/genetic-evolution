package vcreature.genotype;


public class ConstantInput implements NeuralInput<Float>, EffectorInput
{
  private float constant;

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
