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

  private Genome genome;

  @Override
  public Genome encode(Creature creature)
  {

    // BUILD THE RIGID GENOME
    genome = new Genome();
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

    // LINK THE NERVOUS SYSTEM
    for (int i = 0; i < creature.getNumberOfBodyBlocks(); i++)
    {
      // get the block
      Block b = creature.getBlockByID(i);
      Gene g = genome.getGenes().get(i);

      for (Neuron neuron : b.getNeuronTable())
      {
        g.getEffector().addNeuralNode(synthesizeNeuron(neuron, b));
      }
    }


    return genome;
  }

  @Override
  public Creature decode(Genome typeToConvert)
  {
    return null;
  }

  private Block synthesizeGene(Gene current)
  {
    return null;
  }


  private Gene synthesizeBlock(Block b)
  {
    Gene gene = new Gene(b.getID());
    gene.setDimensions(b.getSizeY(), b.getSizeX(), b.getHeight());

    if (b.getJoint() != null)
    {
      gene.getEffector().setMaxForce(b.getJointMaxImpulse());
      gene.getEffector().setJointParentIndex(b.getIdOfParent());
      gene.getEffector().setParent(b.getJoint().getPivotA());
      gene.getEffector().setChild(b.getJoint().getPivotB());
      // gene.getEffector().setParentAxis(b.getJoint().getHingeAngle());


    }
    return gene;
  }


  private NeuralNode synthesizeNeuron(Neuron neuron, Block b)
  {
    NeuralNode node = new NeuralNode();

    for (int i = 0; i < Neuron.TOTAL_INPUTS; i++)
    {
      NeuralInput input = synthesizeNeuronInput(neuron, i);
      synthesizeNeuronOperators(node, neuron);
      node.setInput(InputPosition.fromOrdinal(i), input);

    }

    return node;
  }

  private void synthesizeNeuronOperators(NeuralNode nnode, Neuron neuron)
  {
    nnode.setOperator(neuron.getOp(0),
                      NeuralNode.NeuralOperatorPosition.FIRST);
    nnode.setOperator(neuron.getOp(1),
                      NeuralNode.NeuralOperatorPosition.SECOND);
    nnode.setOperator(neuron.getOp(2),
                      NeuralNode.NeuralOperatorPosition.THIRD);
    nnode.setOperator(neuron.getOp(3),
                      NeuralNode.NeuralOperatorPosition.FOURTH);
  }

  private NeuralInput synthesizeNeuronInput(Neuron neuron, int inputPosition)
  {
    if (neuron.getInputType(inputPosition) == EnumNeuronInput.CONSTANT)
    {
      return new ConstantInput().setValue(neuron.getInputValue(inputPosition));
    }
    if (neuron.getInputType(inputPosition) == EnumNeuronInput.TIME)
    {
      return new TimeInput().setValue(neuron.getInputValue(inputPosition));
    }
    if (neuron.getInputType(inputPosition) == EnumNeuronInput.TOUCH)
    {
      int a = neuron.getBlockIdx(inputPosition);
      float val = neuron.getInputValue(inputPosition);
      return genome.getGenes().get(a).getTouchSensor().setValue(val);
    }
    if (neuron.getInputType(inputPosition) == EnumNeuronInput.HEIGHT)
    {
      int a = neuron.getBlockIdx(inputPosition);
      float val = neuron.getInputValue(inputPosition);
      return genome.getGenes().get(a).getHeightSensor().setValue(val);
    }
    if (neuron.getInputType(inputPosition) == EnumNeuronInput.JOINT)
    {
      int a = neuron.getBlockIdx(inputPosition);
      float val = neuron.getInputValue(inputPosition);
      return genome.getGenes().get(a).getAngleSensor().setValue(val);
    }

    return new ConstantInput().setValue(neuron.getInputValue(inputPosition));

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


  @Override
  public String toString()
  {
    return null;
  }

  @Override
  public String synthesizedToString()
  {
    StringBuffer s = new StringBuffer();

    Queue<Gene> frontier = new LinkedList<>();
    frontier.add(genome.getRoot());
    HashMap<Gene, Gene> cameFrom = new HashMap<>();
    cameFrom.put(genome.getRoot(), null);

    while (!frontier.isEmpty())
    {
      Gene current = frontier.remove();
      List<Gene> neighbors = genome.neighbors(current);

      if (neighbors.size() > 0)
      {
        s.append(current.toString()+ "p");
      }

      for (Gene next : neighbors)
      {
        if (!cameFrom.containsKey(next))
        {
          frontier.add(next);
          cameFrom.put(next, current);
          s.append("->" + next.toString());
        }
      }
      s.append("\n");
    }
    return s.toString();
  }
}
