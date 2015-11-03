package vcreature.utils;



public interface Savable
{
  // StringBuilder getString(Savable savable);

  void write(StringBuilder s);

  void read(String s);
}
