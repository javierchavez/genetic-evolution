package vcreature;

import vcreature.genotype.*;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.Neuron;
import vcreature.genotype.NeuralInput.InputPosition;

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
      // TODO is this correct? Directed graph normally point opposite
      // genome.linkGenes(b.getIdOfParent(), i);

      // So I am doing it this way for a more proper graph
      genome.linkGenes(i, b.getIdOfParent());
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
      effector.setJointParentIndex(b.getIdOfParent());
    }
    return gene;
  }


  private NeuralNode synthesizeNeuron(Neuron neuron)
  {
    NeuralNode node = new NeuralNode();

    for (int i = 0; i < Neuron.TOTAL_INPUTS; i++)
    {
      NeuralInput input = synthesizeNeuronInput(neuron, i);
      synthesizeNeuronOperators(node, neuron);
      node.setInput(input.getInputPosition(), input);

    }

    return node;
  }

  private void synthesizeNeuronOperators(NeuralNode nnode, Neuron neuron)
  {
    nnode.setOperator(neuron.getOp(0), NeuralNode.NeuralOperatorPosition.FIRST);
    nnode.setOperator(neuron.getOp(1), NeuralNode.NeuralOperatorPosition.SECOND);
    nnode.setOperator(neuron.getOp(2), NeuralNode.NeuralOperatorPosition.THIRD);
    nnode.setOperator(neuron.getOp(3), NeuralNode.NeuralOperatorPosition.FOURTH);
  }

  private NeuralInput synthesizeNeuronInput(Neuron neuron, int position)
  {
    if (neuron.getInputType(position) == EnumNeuronInput.CONSTANT)
    {
      return new ConstantInput()
              .setValue(neuron.getInputValue(position))
              .setInputPosition(InputPosition.fromOrdinal(position));
    }
    if (neuron.getInputType(position) == EnumNeuronInput.TIME)
    {
      return new TimeInput()
              .setValue(neuron.getInputValue(position))
              .setInputPosition(InputPosition.fromOrdinal(position));
    }
    if (neuron.getInputType(position) == EnumNeuronInput.TOUCH)
    {
      return new TouchSensor()
              .setValue(neuron.getInputValue(position))
              .setInputPosition(InputPosition.fromOrdinal(position));
    }
    if (neuron.getInputType(position) == EnumNeuronInput.HEIGHT)
    {
      return new HeightSensor()
              .setValue(neuron.getInputValue(position))
              .setInputPosition(InputPosition.fromOrdinal(position));
    }
    if (neuron.getInputType(position) == EnumNeuronInput.JOINT)
    {
      return new AngleSensor()
              .setValue(neuron.getInputValue(position))
              .setInputPosition(InputPosition.fromOrdinal(position));
    }

    return new ConstantInput()
            .setValue(neuron.getInputValue(position))
            .setInputPosition(InputPosition.fromOrdinal(position));

  }


  /**
   * BFS to turn a directed graph into array
   */
  public class BFS
  {

    public List<Gene> lenearOrder(Genome graph)
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

      return reconstructPath(cameFrom,
                             graph.getRoot(),
                             graph.getGenes().getLast());
    }

    private List<Gene> reconstructPath(HashMap<Gene, Gene> came_from,
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
