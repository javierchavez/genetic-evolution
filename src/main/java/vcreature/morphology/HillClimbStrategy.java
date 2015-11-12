package vcreature.morphology;


import vcreature.genotype.Genome;

public interface HillClimbStrategy<T extends Genome, V>
{
  enum Strategies {
    EFFECTOR, NEURAL_NET, ROOT_GENE, NEURAL_NODE, LIMB_GENE
  }

  void climb(T part, V partHelper);
}
