package vcreature.utils;

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
 * Used to describe an object which can be saved and written to a file by the logger
 */
public interface Savable
{
  /**
   * Writes the object to a StringBuilder.
   *
   * @param s reference
   */
  void write(StringBuilder s);

  /**
   * Given a string read its contents and manipulate this in the context of
   * this.read(s)
   *
   * @param s string containing valid genome string
   */
  void read(StringBuilder s);
}
