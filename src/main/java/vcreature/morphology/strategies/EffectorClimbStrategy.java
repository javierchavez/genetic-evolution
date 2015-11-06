package vcreature.morphology.strategies;

import vcreature.genotype.Effector;


public class EffectorClimbStrategy<V> implements HillClimbStrategy<Effector, V>
{

  @Override
  public V climb(Effector part)
  {
    // have your effector ie. part

    // now change it.
    part.setMaxForce(10f);

    return null;
  }


}
