package vcreature.genotype;

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
