package vcreature;


import com.jme3.math.Vector3f;
import vcreature.genotype.*;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.Neuron;

import java.util.*;

public final class GenomeSynthesizer extends Synthesizer<Genome, Creature>
{
  private final static GenomeSynthesizer ourInstance = new GenomeSynthesizer();
  private static Environment env;
  private Genome genome;
  private Creature creature;

  public static GenomeSynthesizer getInstance()
  {
    return ourInstance;
  }

  private GenomeSynthesizer()
  {
  }

  public static GenomeSynthesizer init(Environment environment)
  {
    env = environment;
    return ourInstance;
  }

  @Override
  public Creature encode(Genome typeToConvert)
  {
    this.genome = typeToConvert;
    this.creature = new Creature(env.getBulletAppState().getPhysicsSpace(),
                                     env.getRootNode());

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
    Vector3f size = new Vector3f(current.getLengthX()/2, current.getHeightY()/2, current.getWidthZ()/2);

    if (parent == null)
    {
      // start the creature at the origin
      // creature.placeOnGround() will move it as necessary
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

  @Override
  public Genome decode(Creature typeToConvert)
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
    return creature.toString();
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
    EnumNeuronInput inputA = synthesizeInput(neuralNode.getInputs().get(NeuralInput.InputPosition.A));
    EnumNeuronInput inputB = synthesizeInput(neuralNode.getInputs().get(NeuralInput.InputPosition.B));
    EnumNeuronInput inputC = synthesizeInput(neuralNode.getInputs().get(NeuralInput.InputPosition.C));
    EnumNeuronInput inputD = synthesizeInput(neuralNode.getInputs().get(NeuralInput.InputPosition.D));
    EnumNeuronInput inputE = synthesizeInput(neuralNode.getInputs().get(NeuralInput.InputPosition.E));

    Neuron neuron = new Neuron(inputA, inputB, inputC, inputD, inputE);
    neuron.setInputValue(Neuron.A, (Float) neuralNode.getInputs().get(NeuralInput.InputPosition.A).getValue());
    neuron.setInputValue(Neuron.B, (Float) neuralNode.getInputs().get(NeuralInput.InputPosition.B).getValue());
    neuron.setInputValue(Neuron.C, (Float) neuralNode.getInputs().get(NeuralInput.InputPosition.C).getValue());
    neuron.setInputValue(Neuron.D, (Float) neuralNode.getInputs().get(NeuralInput.InputPosition.D).getValue());
    neuron.setInputValue(Neuron.E, (Float) neuralNode.getInputs().get(NeuralInput.InputPosition.E).getValue());

    neuron.setOp(neuralNode.getOperators().get(NeuralNode.NeuralOperatorPosition.FIRST), 0);
    neuron.setOp(neuralNode.getOperators().get(NeuralNode.NeuralOperatorPosition.SECOND), 1);
    neuron.setOp(neuralNode.getOperators().get(NeuralNode.NeuralOperatorPosition.THIRD), 2);
    neuron.setOp(neuralNode.getOperators().get(NeuralNode.NeuralOperatorPosition.FOURTH), 3);

    return neuron;
  }

  private EnumNeuronInput synthesizeInput(NeuralInput neuralInput)
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
    }
    return input;
  }
}
