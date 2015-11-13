package vcreature.morphology;

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


import com.jme3.math.Vector3f;
import vcreature.genotype.Gene;
import vcreature.genotype.Genome;
import vcreature.phenotype.Block;


/**
 * This class is a mutation to change the volume of the root node of a computer
 *
 * @param <V> type of gene to mutate
 */
public class RootGeneDimensionDecreaseStrategy<V> extends AbstractHillClimbStrategy<Genome, V> implements HillClimbStrategy<Genome, V>
{
  Vector3f vector3f = new Vector3f();

  public RootGeneDimensionDecreaseStrategy(float w)
  {
    super(w);
  }

  @Override
  public void climb(Genome part, V partHelper)
  {
    float scaleFactor = 0.95f;
    // only mutate dimensions
    part.getRoot().getDimensions(vector3f);

    vector3f.x *= scaleFactor;
    vector3f.y *= scaleFactor;
    vector3f.z *= scaleFactor;

    if (Block.min(vector3f) < 0.5f)
    {
      return;
    }

    if (Block.max(vector3f) > 10*Block.min(vector3f))
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
