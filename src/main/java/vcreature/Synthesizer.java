package vcreature;


public abstract class Synthesizer<T, V>
{

  /**
   * Encode T into V
   *
   * @param typeToConvert class that will be converted
   * @return new converted object
   */
  public abstract V encode(T typeToConvert);


  /**
   * Decode V into T
   * @param typeToConvert object that needs to be converted
   * @return new converted object
   */
  public abstract T decode(V typeToConvert);
}
