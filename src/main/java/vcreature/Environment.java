package vcreature;


import vcreature.Algorithms.GeneticAlgorithm;
import vcreature.genotype.Genome;
import vcreature.genotype.GenomeGenerator;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;

import java.util.Random;

/**
 *  Environment housing all the applications main functionality.
 *
 */
public class Environment extends AbstractApplication
{

  private float elapsedSimulationTime = 0.0f;

  // Main population
  private Population population;

  // Wrapper for population and controlling populations
  private Evolution evolution;


  // Turning creatures into DNA and Blocks
  private CreatureSynthesizer creatureSynthesizer;
  private GenomeSynthesizer genomeSynthesizer;

  // Current creature, being, genome
  private Creature creature;
  private Being being = null;
  private Genome genome;

  // Used for crossing
  private GeneticAlgorithm breeding;

  // Generate random genomes
  private GenomeGenerator generator;

  // Generation currently spawning
  boolean newGenerationSpwan = false;


  static boolean creaturePresent = false;

  // Amount of time creatures are left in environment
  private static int EVALUATION_TIME = 6; // seconds

  // Used for getting random subpopulation for crossing
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

    // initialize population
    generator = new GenomeGenerator(getPhysicsSpace(), rootNode);

    // Fill up the population
    for (int i = 0; i < 100; i++)
    {
      FlappyBird daBird = new FlappyBird(getPhysicsSpace(), rootNode);
      Genome genotype = creatureSynthesizer.encode(daBird);

      // generator.generateGenome();
      daBird.remove();

      Being bb = new Being();

      bb.setGenotype(genotype);
      population.add(bb);
    }

    // set the population to a evolution
    evolution = new Evolution(population);


  }


  /* Use the main event loop to trigger repeating actions. */
  @Override
  public void simpleUpdate(float deltaSeconds)
  {
    super.simpleUpdate(deltaSeconds);

    elapsedSimulationTime += deltaSeconds;

    // A being is in queue ready for evaluation
    if (beingAdded)
    {
      genome = being.getGenotype();

      creature = genomeSynthesizer.encode(genome);
      beingAdded = false;
      elapsedSimulationTime = 0;
    }

    // check if the evaluation is complete
    if (creature != null && elapsedSimulationTime >= EVALUATION_TIME)
    {
      being.setFitness(creature.getFitness());
      being.setUnderEvaluation(false);
      creature.remove();
      creature = null;
    }

    // On cross populations when one is complete.
    // avoid adding more than more to engine
    if (elapsedSimulationTime > 5 && !newGenerationSpwan || breeding.currGen() == 200)
    {
      System.out.println("New generation kicked off");
      newGenerationSpwan = true;
      new Thread(() -> {
        evolution.crossSubpopulation(genRandDim(evolution.getSubs().size()));
      }).start();
    }

    // update the brain
    if (creature != null)
    {
      creature.updateBrain(elapsedSimulationTime);
    }

  }

  /**
   * Add a being into the environment for evaluation.
   *
   * @param v Being with genotype to be added to physics space.
   */
  public void beginEvaluation(Being v)
  {
    System.out.println("Creature being evaluated.");

    this.being = v;
    being.setUnderEvaluation(true);
    beingAdded = true;
    genome = being.getGenotype();
  }

  private int genRandDim(int max)
  {
    return random.nextInt(max - 1) + 0;
  }



}
