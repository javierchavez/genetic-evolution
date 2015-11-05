package vcreature.Algorithms;

import vcreature.genotype.NeuralNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class NeurlNetClimbStrategey<V> implements HillClimbStrategy<ArrayList<NeuralNode>, V>
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
        case 2:
        {
          strategy.climb(part.get(index1));
        }
        case 3:
        {
          strategy.climb(part.get(index1));
          strategy.climb(part.get(index2));
        }
        case 4:
        {
          strategy.climb(part.get(index1));
          strategy.climb(part.get(index2));
          strategy.climb(part.get(index3));
        }
        case 5:

        {
          Collections.shuffle(part);

        }
      }

    }

    return null;
  }
}
