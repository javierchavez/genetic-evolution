package vcreature.genotype;

/**
 * @author Javier Chavez
 * @author Alex Baker
 * @author Dominic Salas
 * @author Carrie Martinez
 * <p>
 * Date November 4, 2015
 * CS 351
 * Genetic Evolution
 */


import vcreature.utils.Savable;

import java.lang.reflect.InvocationTargetException;


/**
 * Page 13 Slide 26 the sensor is for joint, touch, height.
 *
 * Main point of Sensors in the Genome is to house values. The term Sensor is just
 * a fancy term for Class that holds value
 *
 * @param <T> type of sensor
 * @param <V> sensors value type
 */
public abstract class Sensor<T extends Sensor<?, ?>, V> implements NeuralInput<V>, EffectorInput, Savable
{
  private V sensorValue;
  private Gene source;


  /**
   * Create a new Sensor connected to the source gene
   *
   * @param source the gene which the sensor is connect to
   */
  public Sensor(Gene source)
  {
    this.source = source;
  }

  /**
   * Get the gene this sensor is connected to
   *
   * @return a gene which is connected to the sensor
   */
  public Gene getSource()
  {
    return source;
  }

  /**
   * Get the value of the sensor
   *
   * @return the current value of the sensor
   */
  public V getValue()
  {
    return sensorValue;
  }

  /**
   * Set the value of the sensor
   *
   * @param value set the value V dictates what type of value
   * @return
   */
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
