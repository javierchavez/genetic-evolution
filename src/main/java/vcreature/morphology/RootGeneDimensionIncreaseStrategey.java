package vcreature.morphology;

import com.jme3.math.Vector3f;
import vcreature.genotype.Gene;
import vcreature.genotype.Genome;
import vcreature.phenotype.Block;


public class RootGeneDimensionIncreaseStrategey <V> extends AbstractHillClimbStrategy<Genome, V> implements HillClimbStrategy<Genome, V>
{

  Vector3f vector3f = new Vector3f();

  public RootGeneDimensionIncreaseStrategey(float w)
  {
    super(w);
  }

  @Override
  public void climb(Genome part, V partHelper)
  {
    float scaleFactor = 1.05f;
    // only mutate dimensions
    part.getRoot().getDimensions(vector3f);

    vector3f.x *= scaleFactor;
    vector3f.y *= scaleFactor;
    vector3f.z *= scaleFactor;

    if (Block.min(vector3f) < 0.5f)
    {
      return;
    }

    if (Block.max(vector3f) > 10 * Block.min(vector3f))
    {
      return;
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
    return;
  }
}