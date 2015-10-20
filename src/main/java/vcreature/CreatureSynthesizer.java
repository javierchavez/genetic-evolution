package vcreature;

import vcreature.genotype.*;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.Neuron;


public class CreatureSynthesizer extends Synthesizer<Creature, Genome>
{
  @Override
  public Genome encode(Creature creature)
  {
    Genome genome = new Genome();
    // root of the genome is index 0
    for (int i = 0; i < creature.getNumberOfBodyBlocks(); i++)
    {
      genome.append(synthesizeBlock(creature.getBlockByID(i)));
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
      effector.setMaxForce(b.getMass()); effector.setMaxLimit(b.getMass());
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
}
