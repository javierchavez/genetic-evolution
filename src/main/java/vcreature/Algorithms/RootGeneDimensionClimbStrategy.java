package vcreature.Algorithms;


import com.jme3.math.Vector3f;
import vcreature.genotype.Gene;
import vcreature.genotype.Genome;
import vcreature.phenotype.Block;

import java.util.Random;

public class RootGeneDimensionClimbStrategy<V> implements HillClimbStrategy<Genome, V>
{
  Vector3f vector3f = new Vector3f();
  @Override
  public V climb(Genome part)
  {
    Random rand = new Random();
    float scaleFactor = rand.nextBoolean() ? 1.05f : 0.95f;
    // only mutate diminution
    part.getRoot().getDimensions(vector3f);

    vector3f.x *= scaleFactor;
    vector3f.y *= scaleFactor;
    vector3f.z *= scaleFactor;

    if (vector3f.x < 0.5f || vector3f.y < 0.5f || vector3f.z < 0.5f)
    {
      return null;
    }

    if (Block.max(vector3f) > 10*Block.min(vector3f))
    {
      return null;
    }

    part.getRoot().setDimensions(vector3f);

    for (Gene neighbor : part.neighbors(part.getRoot()))
    {
      neighbor.getEffector().getParent(vector3f);
      vector3f.x *= scaleFactor;
      vector3f.y *= scaleFactor;
      vector3f.z *= scaleFactor;
      neighbor.getEffector().setParent(vector3f);
    }
    return null;
  }
}
