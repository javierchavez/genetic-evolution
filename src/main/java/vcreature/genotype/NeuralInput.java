package vcreature.genotype;


public interface NeuralInput<V>
{
  enum InputPosition
  {
    A, B, C, D, E;
    private static InputPosition[] allValues = values();
    public static InputPosition fromOrdinal(int n) {return allValues[n];}

    }

  NeuralInput<V> setInputPosition(InputPosition position);

  InputPosition getInputPosition();

  V getValue();

  NeuralInput<V> setValue(V value);
}
