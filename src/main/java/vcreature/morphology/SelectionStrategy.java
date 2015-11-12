package vcreature.morphology;

import java.util.List;


public interface SelectionStrategy <T, V extends List<? extends Comparable>>
{
  V select(T collection);
}
