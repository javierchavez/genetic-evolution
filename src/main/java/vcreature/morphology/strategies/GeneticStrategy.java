package vcreature.morphology.strategies;


import java.util.List;

public interface GeneticStrategy<T, V extends List<? extends Comparable>>
{
  V run(T part);

  V select(T collection);
}
