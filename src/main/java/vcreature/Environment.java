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
 * <p>
 * Module description here
 */


import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
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
  private Creature creature = null;
  private Being being = null;
  private Genome genome = null;

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


    breeding = new GeneticAlgorithm(this);
    population = new Population(breeding);

    // initialize population
    generator = new GenomeGenerator(getPhysicsSpace(), rootNode);

    // Fill up the population
    for (int i = 0; i < 25; i++)
    {

      FlappyBird _creature = new FlappyBird(getPhysicsSpace(), rootNode);
      FlappyBird2 _creature2 = new FlappyBird2(getPhysicsSpace(), rootNode);
      FlappyBird3 _creature3 = new FlappyBird3(getPhysicsSpace(), rootNode);
      FlappyBird4 _creature4 = new FlappyBird4(getPhysicsSpace(), rootNode);
      FlappyBird5 _creature5 = new FlappyBird5(getPhysicsSpace(), rootNode);

      Genome _genome = creatureSynthesizer.encode(_creature);
      _creature.remove();
      Being bb = new Being();
      bb.setGenotype(_genome);
      population.add(bb);

      Genome _genome2 = creatureSynthesizer.encode(_creature2);
      _creature2.remove();
      Being bb2 = new Being();
      bb2.setGenotype(_genome2);
      population.add(bb2);

      Genome _genome3 = creatureSynthesizer.encode(_creature3);
      _creature3.remove();
      Being bb3 = new Being();
      bb3.setGenotype(_genome3);
      population.add(bb3);

      Genome _genome4 = creatureSynthesizer.encode(_creature4);
      _creature4.remove();
      Being bb4 = new Being();
      bb4.setGenotype(_genome4);
      population.add(bb4);

      Genome _genome5 = creatureSynthesizer.encode(_creature5);
      _creature5.remove();
      Being bb5 = new Being();
      bb5.setGenotype(_genome5);
      population.add(bb5);

      if(i % 2 == 0)
      {
        Being _randBeing = new Being();
        _randBeing.setGenotype(generator.generateGenome());
        population.add(_randBeing);
      }
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
    if (!newGenerationSpwan && breeding.currGen() == 0)
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

  public void setGenerationSpawn(boolean generationSpawn)
  {
    this.newGenerationSpwan = generationSpawn;
  }

  public static void main(String[] args)
  {
    AppSettings settings = new AppSettings(true);
    settings.setResolution(1024, 768);
    settings.setSamples(4); //activate antialising (softer edges, may be slower.)

    //Set vertical syncing to true to time the frame buffer to coincide with the refresh frequency of the screen.
    //This also throttles the calls to simpleUpdate. Without this throttling, I get 1000+ pfs on my Alienware laptop
    //   Your application will have more work to do than to spend cycles rendering faster than the
    //   capture rate of the RED Camera used to shoot Lord of the Rings.
    settings.setVSync(true);
    settings.setFrequency(60);//Frames per second
    settings.setTitle("Flappy Bird Creature");

    System.out.println("Starting App");

    Environment app = new Environment();
    app.setShowSettings(false);
    app.setSettings(settings);


    app.start(JmeContext.Type.Headless);

    //app.start();
  }


}
