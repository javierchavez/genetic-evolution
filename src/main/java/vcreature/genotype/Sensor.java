package vcreature.genotype;

import java.lang.reflect.InvocationTargetException;

/**
 * Page 13 Slide 26 the sensor is for joint, touch, height
 *
 * @param <T> type of sensor
 * @param <V> sensors value type
 */
public abstract class Sensor<T extends Sensor<?, ?>, V> implements NeuralInput<V>, EffectorInput
{
  private V sensorValue;
  private Gene source;


  public Sensor(Gene source)
  {
    this.source = source;
  }

  public Gene getSource()
  {
    return source;
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

  @Override
  public T clone()
  {
    try
    {
      Sensor newSensor = getClass().getDeclaredConstructor(Gene.class).newInstance(source);
      return (T) newSensor;
    }
    catch (InstantiationException e)
    {
      e.printStackTrace();
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
    catch (IllegalArgumentException e)
    {
      e.printStackTrace();
    }
    catch (SecurityException e)
    {
      e.printStackTrace();
    }
    catch (InvocationTargetException e)
    {
      e.printStackTrace();
    }
    catch (NoSuchMethodException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
