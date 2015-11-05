package vcreature.Algorithms;


import com.jme3.math.Vector3f;
import vcreature.genotype.Genome;

public class RootGeneDimensionClimbStrategy<V> implements HillClimbStrategy<Genome, V>
{
  Vector3f vector3f = new Vector3f();
  @Override
  public V climb(Genome part)
  {
    // only mutate diminution
    part.get(0).getDimensions(vector3f);

    return null;
  }
}
