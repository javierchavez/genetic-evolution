package vcreature.morphology;

import vcreature.genotype.Gene;
import vcreature.genotype.Genome;


public class EffectorClimbStrategy extends AbstractHillClimbStrategy<Genome, Gene> implements HillClimbStrategy<Genome, Gene>
{

  public EffectorClimbStrategy(float w)
  {
    super(w);
  }



  @Override
  public void climb(Genome part, Gene partHelper)
  {

    partHelper.getEffector().setMaxForce(10f);

    // have your effector ie. part

    // now change it.
  }


}
