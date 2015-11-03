package vcreature.genotype;


import vcreature.utils.Savable;

public interface NeuralInput<V> extends Savable
{
  enum InputPosition
  {
    A, B, C, D, E;
    private static InputPosition[] allValues = values();
    public static InputPosition fromOrdinal(int n) {return allValues[n];}

    }

  V getValue();

  NeuralInput<V> setValue(V value);
}
