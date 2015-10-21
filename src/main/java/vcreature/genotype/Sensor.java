package vcreature.genotype;

/**
 * Page 13 Slide 26 the sensor is for joint, touch, height
 *
 * @param <T> type of sensor
 * @param <V> sensors value type
 */
public abstract class Sensor<T extends Sensor<?, ?>, V> implements NeuralInput<V>, EffectorInput
{
  private V sensorValue;
  private InputPosition position;

  @Override
  public NeuralInput<V> setInputPosition(InputPosition position)
  {
    this.position = position;
    return this;
  }

  @Override
  public InputPosition getInputPosition()
  {
    return position;
  }



  public V getValue()
  {
    return sensorValue;
  }

  public NeuralInput<V> setValue(V value)
  {
    sensorValue = value;
    return this;
  }

  public void setSensorValue(V value)
  {
    setValue(value);
  }

}
