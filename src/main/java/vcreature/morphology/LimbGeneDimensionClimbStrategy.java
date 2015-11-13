package vcreature.morphology;

import com.jme3.math.Vector3f;
import vcreature.genotype.Gene;
import vcreature.genotype.Genome;
import vcreature.phenotype.Block;


public class LimbGeneDimensionClimbStrategy<V> extends AbstractHillClimbStrategy<Genome, V> implements HillClimbStrategy<Genome, V>
{
  Vector3f vector3f = new Vector3f();

  public LimbGeneDimensionClimbStrategy(float v)
  {
    super(v);
  }

  @Override
  public void climb(Genome part, V partHelper)
  {
    float scaleFactor = 0.95f;
    // only mutate dimensions

    for (Gene g : part.leafs())
    {
      g.getDimensions(vector3f);

      vector3f.x *= scaleFactor;
      vector3f.y *= scaleFactor;
      vector3f.z *= scaleFactor;

      if (Block.min(vector3f) < 0.5f)
      {
        continue;
      }

      if (Block.max(vector3f) > 10 * Block.min(vector3f))
      {
        continue;
      }

      g.setDimensions(vector3f);

      g.getEffector().getChild(vector3f);
      vector3f.x *= scaleFactor;
      vector3f.y *= scaleFactor;
      vector3f.z *= scaleFactor;
      g.getEffector().setChild(vector3f);
    }
  }
}
