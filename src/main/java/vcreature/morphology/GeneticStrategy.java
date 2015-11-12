package vcreature.morphology;


import vcreature.Being;

import java.util.ArrayList;

public interface GeneticStrategy<T, V extends ArrayList<? extends Being>>
{
  enum GA_TYPE{
    CROSSOVER, CROSSOVER_MUTATION
  }

  V run(T ... part);
}
