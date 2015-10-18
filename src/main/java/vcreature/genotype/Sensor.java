package vcreature.genotype;

/**
 * Page 13 Slide 26 the sensor is for joint, touch, height
 *
 * @param <T> type of sensor
 * @param <V> sensors value type
 */
public abstract class Sensor<T extends Sensor<?, ?>, V> implements NeuronInput<V>, EffectorInput
{

  private V sensorValue;

  public V getValue()
  {
    return sensorValue;
  }

  public void setValue(V value)
  {
    sensorValue = value;
  }

  public void setSensorValue(V value)
  {
    setValue(value);
  }

}
