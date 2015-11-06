package vcreature.translations;

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


/**
 * Base class for converting T -> V
 *
 * @param <T>
 * @param <V>
 */
public abstract class Synthesizer<T, V>
{

  /**
   * Encode T into V
   *
   * @param typeToConvert class that will be converted
   * @return new converted object
   */
  public abstract V encode(T typeToConvert);

  public abstract String toString();
}
