package vcreature;

/**
 * @author Javier Chavez
 * @author Alex Baker
 * @author Dominic Salas
 * @author Carrie Martinez
 * <p>
 * Date November 4, 2015
 * CS 351
 * Genetic Evolution
 */


import com.jme3.bullet.PhysicsSpace;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

import vcreature.genotype.*;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.Neuron;

import java.util.*;


/**
 * A class for synthesizing a genome (genotype) into a creature (phenotype)
 */
public final class GenomeSynthesizer extends Synthesizer<Genome, Creature>
{
  private Genome genome;
  private Creature creature;
  private final PhysicsSpace physicsSpace;
  private final Node rootNode;

  /**
   * Create a new synthesizer with the physics space to synthesize the creature too
   *
   * @param physicsSpace physics space where the creature will be added
   * @param rootNode root node of the creature
   */
  public GenomeSynthesizer(PhysicsSpace physicsSpace, Node rootNode)
  {
    this.physicsSpace = physicsSpace;
    this.rootNode = rootNode;
  }

  @Override
  public Creature encode(Genome typeToConvert)
  {
    this.genome = typeToConvert;
    this.creature = new Creature(physicsSpace, rootNode);

    Queue<Gene> frontier = new LinkedList<>();
    frontier.add(genome.getRoot());
    HashMap<Gene, Gene> cameFrom = new HashMap<>();
    HashMap<Gene, Block> blockParent = new HashMap<>();
    cameFrom.put(genome.getRoot(), null);
    blockParent.put(genome.getRoot(), null);

    Block currentBlock;
    while (!frontier.isEmpty())
    {
      Gene current = frontier.remove();
      List<Gene> neighbors = genome.neighbors(current);

      currentBlock = synthesizeGene(current, blockParent.get(current));

      for (Gene next : neighbors)
      {
        if (!cameFrom.containsKey(next))
        {
          frontier.add(next);
          cameFrom.put(next, current);
          blockParent.put(next, currentBlock);
        }
      }
    }
    creature.placeOnGround();
    return creature;
  }

  private Block synthesizeGene(Gene current, Block parent)
  {
    Block block;
    Vector3f size = new Vector3f();
    current.getDimensions(size);

    if (parent == null)
    {
      block = creature.addRoot(new Vector3f(0, Attributes.VERTICAL_OFFSET, 0), size);
      block.setMaterial(Block.MATERIAL_RED);
    }
    else
    {
      float[] rotations = new float[3];
      current.getRotation(rotations);

      Vector3f parentPivot = new Vector3f();
      current.getEffector().getParent(parentPivot);

      Vector3f currentPivot = new Vector3f();
      current.getEffector().getChild(currentPivot);

      Vector3f pivotAxis = new Vector3f();
      current.getEffector().getPivotAxis(pivotAxis);

      block = creature.addBlock(rotations, size, parent, parentPivot, currentPivot, pivotAxis);
      for (Neuron neuron : synthesizeNeurons(current))
      {
        block.addNeuron(neuron);
      }
    }
    return block;
  }

  private List<Neuron> synthesizeNeurons(Gene gene)
  {
    List<Neuron> neurons = new ArrayList<>();
    for (NeuralNode neuralNode : gene.getEffector().getNeuralNet())
    {
      neurons.add(synthesizeNeuralNode(neuralNode));
    }
    return neurons;
  }

  private Neuron synthesizeNeuralNode(NeuralNode neuralNode)
  {
    Neuron neuron = new Neuron(null, null, null, null, null);

    synthesizeInput(Neuron.A,
                    neuralNode.getInputs().get(NeuralInput.InputPosition.A),
                    neuron);
    synthesizeInput(Neuron.B,
                    neuralNode.getInputs().get(NeuralInput.InputPosition.B),
                    neuron);
    synthesizeInput(Neuron.C,
                    neuralNode.getInputs().get(NeuralInput.InputPosition.C),
                    neuron);
    synthesizeInput(Neuron.D,
                    neuralNode.getInputs().get(NeuralInput.InputPosition.D),
                    neuron);
    synthesizeInput(Neuron.E,
                    neuralNode.getInputs().get(NeuralInput.InputPosition.E),
                    neuron);

    neuron.setOp(neuralNode.getOperators().get(NeuralNode.NeuralOperatorPosition.FIRST), 0);
    neuron.setOp(neuralNode.getOperators().get(NeuralNode.NeuralOperatorPosition.SECOND), 1);
    neuron.setOp(neuralNode.getOperators().get(NeuralNode.NeuralOperatorPosition.THIRD), 2);
    neuron.setOp(neuralNode.getOperators().get(NeuralNode.NeuralOperatorPosition.FOURTH), 3);
    return neuron;
  }

  private EnumNeuronInput synthesizeInput(int position, NeuralInput neuralInput, Neuron neuron)
  {
    EnumNeuronInput input;
    if (neuralInput instanceof TimeInput)
    {
      input = EnumNeuronInput.TIME;
    }
    else if (neuralInput instanceof AngleSensor)
    {
      input = EnumNeuronInput.JOINT;
    }
    else if (neuralInput instanceof HeightSensor)
    {
      input = EnumNeuronInput.HEIGHT;
    }
    else if (neuralInput instanceof TouchSensor)
    {
      input = EnumNeuronInput.TOUCH;
    }
    else
    {
      input = EnumNeuronInput.CONSTANT;
      neuron.setInputValue(position, (Float) neuralInput.getValue());
    }
    neuron.setInputType(position, input);
    return input;
  }

  @Override
  public String toString()
  {
    return null;
  }
}
