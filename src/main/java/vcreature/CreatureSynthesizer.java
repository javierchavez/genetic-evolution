package vcreature;

import com.jme3.math.Vector3f;
import vcreature.genotype.*;
import vcreature.genotype.NeuralInput.InputPosition;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.Neuron;

import java.lang.reflect.Field;


public class CreatureSynthesizer extends Synthesizer<Creature, Genome>
{

  private Genome genome;
  Creature  c;

  @Override
  public Genome encode(Creature creature)
  {
    c = creature;
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
      Gene g = genome.get(i);

      for (Neuron neuron : b.getNeuronTable())
      {
        g.getEffector().addNeuralNode(synthesizeNeuron(neuron, b));
      }
    }


    return genome;
  }

  private Block synthesizeGene(Gene current)
  {
    return null;
  }


  private Gene synthesizeBlock(Block b)
  {
    Gene gene = new Gene(b.getID());
    gene.setDimensions(b.getSizeX(), b.getSize(), b.getSizeY());


    if (b.getJoint() != null)
    {
      gene.getEffector().setMaxForce(b.getJointMaxImpulse());
      gene.getEffector().setJointParentIndex(b.getIdOfParent());
      gene.getEffector().setParent(b.getJoint().getPivotA());
      gene.getEffector().setChild(b.getJoint().getPivotB());

      try
      {
        Field field = b.getJoint().getClass().getDeclaredField("axisA");
        field.setAccessible(true);
        Vector3f axis = (Vector3f) field.get(b.getJoint());
        gene.getEffector().setPivotAxis(axis);
      }
      catch (NoSuchFieldException e)
      {
        e.printStackTrace();
        gene.getEffector().setPivotAxis(Vector3f.UNIT_Y);
      }
      catch (IllegalAccessException e)
      {
        e.printStackTrace();
        gene.getEffector().setPivotAxis(Vector3f.UNIT_Y);
      }

      /*
       * Need to figure out how to get axisA from the joint
       * when a block is added it needs an axisA... the last argument i.e. Vector3f.UNIT_Z
       *
       * Block leg2  = addBlock(eulerAngles, leg2Size,torso, pivotC,  pivotD, Vector3f.UNIT_Z);
       */
      //System.out.println(b.getGeometry())

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
      return genome.get(a).getTouchSensor().setValue(val);
    }
    if (neuron.getInputType(inputPosition) == EnumNeuronInput.HEIGHT)
    {
      int a = neuron.getBlockIdx(inputPosition);
      float val = neuron.getInputValue(inputPosition);
      return genome.get(a).getHeightSensor().setValue(val);
    }
    if (neuron.getInputType(inputPosition) == EnumNeuronInput.JOINT)
    {
      int a = neuron.getBlockIdx(inputPosition);
      float val = neuron.getInputValue(inputPosition);
      return genome.get(a).getAngleSensor().setValue(val);
    }

    return new ConstantInput().setValue(neuron.getInputValue(inputPosition));

  }




  @Override
  public String toString()
  {
    return null;
  }

}
