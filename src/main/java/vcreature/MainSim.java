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


import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import vcreature.genotype.Genome;
import vcreature.phenotype.Creature;
import vcreature.translations.GenomeSynthesizer;
import vcreature.utils.Statistics;

/**
 * Environment housing all the applications main functionality.
 */
public class MainSim extends AbstractApplication implements ActionListener
{

  private static int INDEX = 0;
  private float cameraAngle = (float) (Math.PI / 2.0);
  private float elapsedSimulationTime = 0.0f;

  //Temporary vectors used on each frame. They here to avoid instanciating new vectors on each frame
  private Vector3f tmpVec3; //
  private Creature myCreature;
  private boolean isCameraRotating = true;
  private Environment environment;
  private GenomeSynthesizer genomeSynthesizer;
  private BitmapText hudText;
  private BitmapText statsText;
  private boolean showStats = false;


  @Override
  public void simpleInitApp()
  {

    super.simpleInitApp();
    hudText = new BitmapText(guiFont, false);
    statsText = new BitmapText(guiFont, false);
    hudText.setSize(guiFont.getCharSet().getRenderedSize());      // font size
    statsText.setSize(guiFont.getCharSet().getRenderedSize());      // font size
    hudText.setColor(ColorRGBA.Green);// font color
    statsText.setColor(ColorRGBA.LightGray);
    hudText.setLocalTranslation(settings.getWidth()*.80f, hudText.getLineHeight() * 36, 0); // position
    statsText.setLocalTranslation(settings.getWidth()*.05f, hudText.getLineHeight() * 36, 0); // position
    guiNode.attachChild(hudText);

    genomeSynthesizer = new GenomeSynthesizer(getPhysicsSpace(), getRootNode());

    initKeys();

    flyCam.setDragToRotate(true);

  }


  private void initKeys()
  {
    inputManager.addMapping("Quit", new KeyTrigger(KeyInput.KEY_Q));
    inputManager.addMapping("Toggle Camera Rotation", new KeyTrigger(KeyInput.KEY_P));
    inputManager.addMapping("Change Creature", new KeyTrigger(KeyInput.KEY_C));
    inputManager.addMapping("Iterate", new KeyTrigger(KeyInput.KEY_N));
    inputManager.addMapping("Evaluation", new KeyTrigger(KeyInput.KEY_E));
    inputManager.addMapping("Stats", new KeyTrigger(KeyInput.KEY_S));

    // Add the names to the action listener.
    inputManager.addListener(this, "Quit");
    inputManager.addListener(this, "Toggle Camera Rotation");
    inputManager.addListener(this, "Change Creature");
    inputManager.addListener(this, "Iterate");
    inputManager.addListener(this, "Evaluation");
    inputManager.addListener(this, "Stats");
  }

  public void onAction(String name, boolean isPressed, float timePerFrame)
  {

    if (isPressed && name.equals("Toggle Camera Rotation"))
    {
      isCameraRotating = !isCameraRotating;
    }
    else if (isPressed && name.equals("Stats"))
    {
      if (environment.getStats() != null && !showStats)
      {
        hudText.removeFromParent();
        guiNode.attachChild(statsText);
      }
      else
      {

        statsText.removeFromParent();
        guiNode.attachChild(hudText);
      }
      showStats = !showStats;
    }
    else if(isPressed && name.equals("Iterate"))
    {
      if (myCreature != null)
      {
        myCreature.remove();
      }

      INDEX++;
      if (INDEX == environment.getPopulation().getBeings().size() - 1)
      {
        INDEX = 0;
      }
      hudText.setText("Creature: [" + INDEX + "] out of: " + environment.getPopulation().getBeings().size());
      Genome genome = environment.getPopulation().getBeings().get(INDEX).getGenotype();
      if (genome != null)
      {
        myCreature = genomeSynthesizer.encode(genome);
      }


      cameraAngle = (float) (Math.PI / 2.0);
      elapsedSimulationTime = 0.0f;

    }
    else if (isPressed && name.equals("Evaluation"))
    {
      if (myCreature != null)
      {
        myCreature.remove();
      }

      if (environment.getPopulation() != null)
      {

        Being being = environment.getPopulation().getActive();
        Genome genome;
        if (being !=null)
        {
          genome = being.getGenotype().clone();
          myCreature = genomeSynthesizer.encode(genome);
          hudText.setText("Current: " + being.getFitness());
        }
        else
        {
          hudText.setText("No one being evaluated.");
        }

      }
      else
      {
        hudText.setText("No best creature yet...");
      }

      cameraAngle = (float) (Math.PI / 2.0);
      elapsedSimulationTime = 0.0f;
    }

    else if (isPressed && name.equals("Change Creature"))
    {
      if (myCreature != null)
      {
        myCreature.remove();
      }

      if (environment.getStats().getBestBeing() != null)
      {

        Genome genome = environment.getStats().getBestBeing().getGenotype().clone();
        myCreature = genomeSynthesizer.encode(genome);
        hudText.setText("Best fitness: " + environment.getStats().getBestFitness());
      }
      else
      {
        hudText.setText("No best creature yet...");
      }

      cameraAngle = (float) (Math.PI / 2.0);
      elapsedSimulationTime = 0.0f;
    }
    else if (isPressed && name.equals("Quit"))
    {
      System.out.format("Creature Fitness (Maximium height of lowest point) = %.3f meters]\n", myCreature.getFitness());
      System.exit(0);
    }

  }


  /* Use the main event loop to trigger repeating actions. */
  @Override
  public void simpleUpdate(float deltaSeconds)
  {
    super.simpleUpdate(deltaSeconds);
    elapsedSimulationTime += deltaSeconds;

    if (myCreature != null)
    {
      myCreature.updateBrain(elapsedSimulationTime);
    }
    if (showStats)
    {
      Statistics stats = environment.getStats();
      statsText.setText(stats.toString());
    }
  }


  public void setPopulation(Environment environment)
  {
    this.environment = environment;
  }

  public static void main(String[] args)
  {
    AppSettings settings = new AppSettings(true);
    Environment env = new Environment(4);
    env.setSettings(settings);
    env.start(JmeContext.Type.Headless);

    settings.setResolution(1024, 768);
    settings.setSamples(4); //activate antialising (softer edges, may be slower.)
    settings.setVSync(true);
    settings.setFrequency(60);//Frames per second



    MainSim app = new MainSim();
    app.setDisplayFps(false);       // to hide the FPS
    app.setDisplayStatView(false);
    app.setPopulation(env);
    app.setShowSettings(false);
    app.setSettings(settings);
    app.start();
  }

}