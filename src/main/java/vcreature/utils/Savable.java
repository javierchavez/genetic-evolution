package vcreature.utils;



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
  void read(String s);
}
