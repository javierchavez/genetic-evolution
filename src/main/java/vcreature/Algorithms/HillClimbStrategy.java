package vcreature.Algorithms;


public interface HillClimbStrategy<T, V>
{
  enum Strategies {
    LIMB, NEURON, EFFECTOR
  }

  V climb(T part);
}
