package vcreature;

/**
 * @author Javier Chavez
 * @author Alex Baker
 * @author Dominic Salas
 * @author Cari Martinez
 * <p>
 * Date November 4, 2015
 * CS 351
 * Genetic Evolution
 */


import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import vcreature.Algorithms.GeneticAlgorithm;
import vcreature.Algorithms.HillClimbing;
import vcreature.genotype.Genome;
import vcreature.genotype.GenomeGenerator;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.utils.Logger;

import java.time.LocalDateTime;
import java.util.Random;


/**
 *  Environment housing all the applications main functionality.
 */
public class Environment extends AbstractApplication
{
  private float elapsedSimulationTime = 0.0f;
  private float totalSimTime = 0.0f;
  private float fitnessChangePerMinute = 0;

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
  private HillClimbing mutating;

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

  private double logStartTime = 0.0;
  private Logger popLogger = new Logger("population-stats-"+LocalDateTime.now().toString().replace(":", "")+".txt");
  private Logger evoLogger = new Logger("population-"+LocalDateTime.now().toString().replace(":", "")+".txt");

  float tempbestFitness = 0f;
  BitmapText hudText;
  private boolean pauseEvaluation = false;


  @Override
  public void simpleInitApp()
  {
    super.simpleInitApp();

    Block.initStaticMaterials(assetManager);

    creatureSynthesizer = new CreatureSynthesizer();
    genomeSynthesizer = new GenomeSynthesizer(getPhysicsSpace(), rootNode);


    breeding = new GeneticAlgorithm(this);
    mutating = new HillClimbing(this);
    population = new Population(breeding, mutating);

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
    logStartTime = System.currentTimeMillis();

    hudText = new BitmapText(guiFont, false);
    hudText.setSize(guiFont.getCharSet().getRenderedSize());      // font size
    hudText.setColor(ColorRGBA.Green);// font color
    hudText.setText("Current best fitness " + breeding.getBestFitness() + "\nFitness change from start " + (breeding.getBestFitness() - breeding.getFirstGenAvgFitness()) + "\nFitness change per minute " + breeding.getCurrentGenAverageFitness());             // the text
    //hudText.setLocalTranslation(300, hudText.getLineHeight(), 0); // position
    hudText.setLocalTranslation(20, hudText.getLineHeight() * 36, 0); // position
    guiNode.attachChild(hudText);

    tempbestFitness = evolution.getBestFitness();
    initKeys();
  }



  private void initKeys()
  {
    inputManager.addMapping("Quit",  new KeyTrigger(KeyInput.KEY_Q));
    inputManager.addMapping("Best",  new KeyTrigger(KeyInput.KEY_B));

    // Add the names to the action listener.
    inputManager.addListener(this, "Best");
    inputManager.addListener(this,"Quit");
    inputManager.addListener(this,"Toggle Camera Rotation");
  }

  @Override
  public void onAction(String name, boolean isPressed, float timePerFrame)
  {
    if (isPressed && name.equals("Next"))
    {

    }
    else if (isPressed && name.equals("Best"))
    {

      if (!pauseEvaluation)
      {
        pauseEvaluation=true;
        if (creature!=null)
        {
          creature.remove();
          creature = null;
        }
        creature = genomeSynthesizer.encode(evolution.getBest().getGenotype());

      }
      else if (pauseEvaluation)
      {

        pauseEvaluation = false;
        creature.remove();
        creature = null;
      }

      elapsedSimulationTime = 0.0f;
    }
    else if (isPressed && name.equals("Quit"))
    {
      System.out.format("Creature Fitness (Maximium height of lowest point) = %.3f meters]\n", creature.getFitness());
      System.exit(0);
    }
  }

  /* Use the main event loop to trigger repeating actions. */
  @Override
  public void simpleUpdate(float deltaSeconds)
  {
    super.simpleUpdate(deltaSeconds);

    elapsedSimulationTime += deltaSeconds;

    hudText.setText("Current best fitness " + evolution.getBestFitness() + "\nFitness change from start " + evolution.fitnessChange() + "\nFitness change per minute " + fitnessChangePerMinute);             // the text

    if (!pauseEvaluation)
    {

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


        // System.out.println("Current best fitness " + evolution.getBestFitness() + "\nFitness change from start " + evolution.fitnessChange() + "\nFitness change per minute " + fitnessChangePerMinute);             // the text);

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
    }
    // update the brain
    if (creature != null)
    {
      creature.updateBrain(elapsedSimulationTime);
    }

    totalSimTime += deltaSeconds;
    if(totalSimTime >= 60f)
    {
      float newBest = evolution.getBestFitness();
      fitnessChangePerMinute= tempbestFitness - newBest;
      tempbestFitness =  newBest;
      totalSimTime = 0;
    }


    if ((System.currentTimeMillis() - logStartTime) > Attributes.LOG_INTERVAL)
    {
      evoLogger.export(evolution);
      popLogger.export(population);
      logStartTime = System.currentTimeMillis();
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

  /**
   * Set whether to spawn a new generation
   *
   * @param generationSpawn true | false
   */
  public void setGenerationSpawn(boolean generationSpawn)
  {
    this.newGenerationSpwan = generationSpawn;
  }

  private int genRandDim(int max)
  {
    return random.nextInt(max - 1) + 0;
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

    if (args.length > 0)
    {

      if (args[0].equalsIgnoreCase("headless"))
      {
        app.start(JmeContext.Type.Headless);
      }
      else
      {
        app.start();
      }
    }
    else
    {
      app.start();
    }
  }


}
