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
 * <p>
 * Module description here
 */


import vcreature.utils.Savable;


/**
 * A nice way to store inputs for neurons
 *
 * @param <V>
 */
public interface NeuralInput<V> extends Savable
{
  /**
   * A Neuron can have 5 inputs each input has a specifc diferentiating name
   * A,B,C,D,E.
   *
   */
  enum InputPosition
  {
    A, B, C, D, E;
    private static InputPosition[] allValues = values();

    /**
     * Create a Inputs based on int value
     * @param n index of input 0-4
     * @return enum
     */
    public static InputPosition fromOrdinal(int n)
    {
      return allValues[n];
    }

  }

  /**
   * Get the value that is associated with the input.
   *
   * @return V
   */
  V getValue();

  /**
   * Set the value of the input
   *
   * @param value set the value V dictates what type of value
   * @return input with value specified
   */
  NeuralInput<V> setValue(V value);
}
