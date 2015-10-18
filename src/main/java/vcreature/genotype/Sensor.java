package vcreature.genotype;


public abstract class Sensor<T extends Sensor<?, ?>, V>
{

  private V sensorValue;

  public V getValue()
  {
    return sensorValue;
  }

  public void setSensorValue(V value)
  {
    this.sensorValue = value;
  }
}
