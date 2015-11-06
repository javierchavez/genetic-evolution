package vcreature.morphology.strategies;

import vcreature.genotype.NeuralNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class NeuralNetClimbStrategy<V> implements HillClimbStrategy<ArrayList<NeuralNode>, V>
{
  NeuronClimbStrategy strategy = new NeuronClimbStrategy<>();

  @Override
  public V climb(ArrayList<NeuralNode> part)
  {

    if(part.size() >= 1)
    {
      Random rnd = new Random();
      int index1 = rnd.nextInt(part.size());
      int index2 = rnd.nextInt(part.size());
      int index3 = rnd.nextInt(part.size());
      int x = rnd.nextInt(5);

      switch (x)
      {
        case 1:
        {
          for (NeuralNode node : part)
          {
            strategy.climb(node);
          }

        }
        break;
        case 2:
        {
          strategy.climb(part.get(index1));
        }
        break;
        case 3:
        {
          strategy.climb(part.get(index1));
          strategy.climb(part.get(index2));
        }
        break;
        case 4:
        {
          strategy.climb(part.get(index1));
          strategy.climb(part.get(index2));
          strategy.climb(part.get(index3));
        }
        break;
        case 0:

        {
          Collections.shuffle(part);

        }
        break;
      }

    }

    return null;
  }
}
