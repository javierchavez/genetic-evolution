package vcreature.genotype;


import vcreature.utils.Savable;

public class TimeInput implements NeuralInput<Float>, Savable
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

  @Override
  public void write(StringBuilder s)
  {
    s.append(time).append(",");
  }

  @Override
  public void read(String s)
  {

  }
}
