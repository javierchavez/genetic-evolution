package vcreature;

import vcreature.genotype.*;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;


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

  private Chromosome synthesizeBlock(Block blockByID)
  {
    Chromosome chromosome = new Chromosome();

    // set the height
    chromosome.h = new HeightAllele().setValue(blockByID.getSizeY());
    // set the width
    chromosome.w = new WidthAllele().setValue(blockByID.getSizeX());
    // set the length
    chromosome.l = new LengthAllele().setValue(blockByID.getSize());
    // set the index
    chromosome.i = new IndexAllele().setValue(blockByID.getID());

    // create a site (for the joint)
    Site s = new Site();

    s.childIndex = blockByID.getID();
    s.parentIndex = blockByID.getIdOfParent();
    // set the site
    chromosome.j = new JointSiteAllele().setValue(s);
    // set the angle (orientation)
    chromosome.o = new JointAngleAllele().setValue(blockByID.getJointAngle());

    // need to turn all the neurons into inputs and operators
    for (int i = 0; i < blockByID.getNeuronTable().size(); i++)
    {
      for (int j = 0; j < 5; j++)
      {
        blockByID.getNeuronTable().get(i);

      }
    }
    return chromosome;
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

}
