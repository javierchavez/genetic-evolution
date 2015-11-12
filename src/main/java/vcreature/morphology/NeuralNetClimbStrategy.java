package vcreature.morphology;

import vcreature.genotype.*;
import vcreature.phenotype.EnumOperator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class NeuralNetClimbStrategy extends AbstractHillClimbStrategy<Genome, Gene> implements HillClimbStrategy<Genome, Gene>
{
  Random rand = new Random();

  public NeuralNetClimbStrategy(float w)
  {
    super(w);
  }



  @Override
  public void climb(Genome genome, Gene partHelper)
  {
    /**TODO persist numbers*/
    ArrayList<NeuralNode> part = partHelper.getEffector().getNeuralNet();
    if (part.size() >= 1)
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
            new NeuronClimbStrategy(.10f).climb(node);
          }

        }
        break;
        case 2:
        {
          new NeuronClimbStrategy(.10f).climb(part.get(index1));
        }
        break;
        case 3:
        {
          new NeuronClimbStrategy(.10f).climb(part.get(index1));
          new NeuronClimbStrategy(.10f).climb(part.get(index2));
        }
        break;
        case 4:
        {
          new NeuronClimbStrategy(.10f).climb(part.get(index1));
          new NeuronClimbStrategy(.10f).climb(part.get(index2));
          new NeuronClimbStrategy(.10f).climb(part.get(index3));
        }
        break;
        case 0:

        {
          Collections.shuffle(part);

        }
        break;
      }

    }
    else
    {
      System.out.println("Adding");
      // lets add a neuron
      ArrayList<NeuralNode> partNets = new ArrayList<>(); // = partHelper.getEffector().getNeuralNet();
      for (Gene g : genome.neighbors(partHelper))
      {
        NeuralNode node = new NeuralNode();
        node.setInput(NeuralInput.InputPosition.A, new TimeInput());
        node.setInput(NeuralInput.InputPosition.C, new ConstantInput(4f));
        node.setInput(NeuralInput.InputPosition.D, new ConstantInput(-Float.MAX_VALUE));

        if (rand.nextFloat()/100f >= .30)
        {
          node.setOperator(EnumOperator.SIN, NeuralNode.NeuralOperatorPosition.FIRST);
        }

        partNets.add(node);
        partNets.add(node);

        g.getEffector().setNeuralNet(partNets);
        partNets.clear();
      }


    }


  }
}
