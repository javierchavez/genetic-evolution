package vcreature;


import vcreature.Algorithms.GeneticAlgorithm;
import vcreature.genotype.Genome;
import vcreature.genotype.GenomeGenerator;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;

import java.util.Random;

/**
 * Wrapper to hold physics this should only belong to evolution.
 */
public class Environment extends AbstractApplication
{

  private float elapsedSimulationTime = 0.0f;


  private Population population;
  private CreatureSynthesizer creatureSynthesizer;
  private GenomeSynthesizer genomeSynthesizer;

  private Creature creature;
  private Being being = null;
  private Genome genome;


  private GeneticAlgorithm breeding;
  private Evolution evolution;
  private GenomeGenerator generator;

  boolean newGenerationSpwan = false;


  static boolean creaturePresent = false;
  private static int EVALUATION_TIME = 11; // seconds
  private Random random = new Random();
  private boolean beingAdded;


  @Override
  public void simpleInitApp()
  {
    super.simpleInitApp();

    Block.initStaticMaterials(assetManager);


    creatureSynthesizer = new CreatureSynthesizer();
    genomeSynthesizer = new GenomeSynthesizer(getPhysicsSpace(), rootNode);


    FlappyBird flappyBird = new FlappyBird(getPhysicsSpace(), rootNode);
    Genome genome = creatureSynthesizer.encode(flappyBird);
    flappyBird.remove();

    breeding = new GeneticAlgorithm(this);
    population = new Population(breeding);
    //    population.initPop();
    Being b = new Being();
    b.setGenotype(genome);
    population.add(b);

    // random generator
    generator = new GenomeGenerator();
    generator.generateGenome();

    // init population
    for (int i = 0; i < 100; i++)
    {
      FlappyBird daBird = new FlappyBird(getPhysicsSpace(), rootNode);
      Genome genotype = creatureSynthesizer.encode(daBird);
      daBird.remove();

      Being bb = new Being();

      bb.setGenotype(genotype);
      population.add(bb);
    }

    evolution = new Evolution(population);


  }


  /* Use the main event loop to trigger repeating actions. */
  @Override
  public void simpleUpdate(float deltaSeconds)
  {
    super.simpleUpdate(deltaSeconds);

    elapsedSimulationTime += deltaSeconds;


    if (beingAdded)
    {
      genome = being.getGenotype();

      creature = genomeSynthesizer.encode(genome);
      beingAdded = false;
      elapsedSimulationTime = 0;
    }

    if (elapsedSimulationTime >= EVALUATION_TIME)
    {
      being.setFitness(creature.getFitness());
      being.setUnderEvaluation(false);
      creature.remove();
    }
    if (elapsedSimulationTime > 5 && !newGenerationSpwan || breeding.currGen() == 200)
    {
      System.out.println("New generation kicked off");
      newGenerationSpwan = true;
      new Thread(() -> {
        evolution.crossSubpopulation(genRandDim(evolution.getSubs().size()));
      }).start();
    }

    if (creature != null)
    {
      creature.updateBrain(elapsedSimulationTime);
    }


  }


  public void beginEvaluation(Being v)
  {

    this.being = v;

    System.out.println("Creature being evaluated.");

    being.setUnderEvaluation(true);
    beingAdded = true;
    genome = being.getGenotype();

  }

  private int genRandDim(int max)
  {
    return random.nextInt(max - 1) + 0;
  }



}
