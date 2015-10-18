package vcreature.genotype;


public class ConstantInput implements NeuronInput<Float>, EffectorInput
{
  private float constant;

  @Override
  public Float getValue()
  {
    return constant;
  }

  @Override
  public void setValue(Float value)
  {
    constant = value;
  }
}
