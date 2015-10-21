package vcreature;

import vcreature.genotype.*;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.Neuron;

import java.util.*;


public class CreatureSynthesizer extends Synthesizer<Creature, Genome>
{
  @Override
  public Genome encode(Creature creature)
  {
    Genome genome = new Genome();
    // root of the genome is index 0
    for (int i = 0; i < creature.getNumberOfBodyBlocks(); i++)
    {
      // get the block
      Block b = creature.getBlockByID(i);
      // block to gene
      Gene g = synthesizeBlock(b);
      // add gene to genome
      genome.append(g);
      // link gene (directed graph)
      genome.linkGenes(b.getIdOfParent(), i);
    }

    return genome;
  }

  @Override
  public Creature decode(Genome typeToConvert)
  {
    return null;
  }

  @Override
  public String toString()
  {
    return null;
  }

  @Override
  public String synthesizedToString()
  {
    return null;
  }


  private Gene synthesizeBlock(Block b)
  {
    Gene gene = new Gene();
    gene.setDimensions(b.getSizeY(), b.getSizeX(), b.getHeight());

    if (b.getJoint() != null)
    {
      Effector effector = new Effector();
      // get joint angle && orientation (what side the joint is connected to block)
      // or something
      effector.setMaxForce(b.getJointMaxImpulse());
      for (Neuron neuron : b.getNeuronTable())
      {
        effector.addNeuralNode(synthesizeNeuron(neuron));
      }
    } return gene;
  }


  private NeuralNode synthesizeNeuron(Neuron neuron)
  {
    NeuralNode node = new NeuralNode();

    //    node.setNeuronFunction();
    // neuron.getOp()
    //    for (int i = 0; i < EnumNeuronInput.values().length; i++)
    //    {
    //
    //    }


    NeuralInput input = new TimeInput(); input.setValue(11f);

    node.getInputs().put('A', input);

    return node;
  }

  /**
   * BFS to turn a directed graph into array
   */
  public class BFS
  {

    public List<Gene> lenearOrder (Genome graph)
    {
      Queue<Gene> frontier = new LinkedList<>();
      frontier.add(graph.getRoot());
      HashMap<Gene, Gene> cameFrom = new HashMap<>();
      cameFrom.put(graph.getRoot(), null);

      while (!frontier.isEmpty())
      {
        Gene current = frontier.remove();
        List<Gene> neighbors = graph.neighbors(current);



        for (Gene next : neighbors)
        {
          if (!cameFrom.containsKey(next))
          {
            frontier.add(next);
            cameFrom.put(next, current);
          }
        }
      }

      return reconstructPath(cameFrom, graph.getRoot(), graph.getGenes().getLast());
    }

    private List<Gene> reconstructPath (HashMap<Gene, Gene> came_from,
                                        Gene start,
                                        Gene end)
    {
      Gene current = end;
      List<Gene> path = new ArrayList<>();
      path.add(current);

      while (current != start)
      {
        current = came_from.get(current);
        if (current != null)
        {
          path.add(current);
        }
      }

      Collections.reverse(path);

      return path;
    }
  }
}
