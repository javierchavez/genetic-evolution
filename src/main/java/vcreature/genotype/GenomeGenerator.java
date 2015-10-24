package vcreature.genotype;


import java.util.Random;

import com.jme3.math.Vector3f;

import vcreature.phenotype.EnumNeuronInput;


/**
 * Generate a genome
 */
public class GenomeGenerator
{
  GenomeGeneratorParameters params = new GenomeGeneratorParameters();
  Genome genome = new Genome();

  public GenomeGenerator()
  {
    generateGenome();
  }

  public void clearGenome()
  {
    genome = new Genome();
  }

  public Genome generateGenome()
  {
    clearGenome();
    Gene gene = generateGene();
    genome.append(gene);
    genome.setRoot(gene);
    return genome;
  }

  private void addGenes(Gene parent)
  {
    int attempts = 0;
    Random rand = new Random();
    Gene gene;

    while (attempts < params.MAX_GENERATION_ATTEMPTS)
    {
      if (genome.neighbors(parent).size() <= params.MAX_CHILDREN && rand.nextFloat() < params.CHILD_SPAWN_CHANCE)
      {
        gene = generateGene(parent);
        genome.append(gene);
        if (rand.nextFloat() < params.RECURSE_CHANCE)
        {
          addGenes(gene);
        }
      }
      attempts += 1;
    }
  }

  private Gene generateGene()
  {
    Random rand = new Random();
    Gene gene = new Gene(genome.size());
    Vector3f size = genRandSize(rand);
    gene.setDimensions(size.x, size.z, size.y);
    return gene;
  }

  private Gene generateGene(Gene parent)
  {
    Random rand = new Random();
    Gene gene = new Gene(genome.size());

    Vector3f size = genRandSize(rand);
    gene.setDimensions(size.x, size.z, size.y);
    return gene;
  }

  private float genRandDim(Random rand)
  {
    return params.MIN_BLOCK_DIM + (rand.nextFloat() * (params.MAX_BLOCK_DIM - params.MIN_BLOCK_DIM));
  }

  private Vector3f genRandSize(Random rand)
  {
    float width = genRandDim(rand); // x dimension
    float height = genRandDim(rand); // y dimension
    float depth = genRandDim(rand); // z dimension
    return new Vector3f(width/2, height/2, depth/2);
  }

  private EnumNeuronInput getRandInput(Random rand)
  {
    return EnumNeuronInput.values()[rand.nextInt(EnumNeuronInput.values().length)];
  }
}
