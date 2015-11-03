package vcreature.genotype;


import vcreature.utils.Savable;

public class ConstantInput implements NeuralInput<Float>, EffectorInput, Savable
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


  @Override
  public void write(StringBuilder s)
  {
    s.append(constant).append(",");
  }

  @Override
  public void read(String s)
  {

  }
}
