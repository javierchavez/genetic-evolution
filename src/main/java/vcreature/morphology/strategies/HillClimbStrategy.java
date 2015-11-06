package vcreature.morphology.strategies;


public interface HillClimbStrategy<T, V>
{
  enum Strategies {
    EFFECTOR, NEURAL_NET, ROOT_GENE, NEURAL_NODE, LIMB_GENE
  }

  V climb(T part);
}
