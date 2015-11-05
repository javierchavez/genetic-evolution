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


import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;


/**
 * Class description here
 */
public class Game extends MainSim implements ActionListener
{
  private static Environment environment;
  private float elapTime = 0f;
  Population population;

  public Environment getEnvironment()
  {
    return environment;
  }

  @Override
  public void simpleInitApp()
  {
    super.simpleInitApp();
    // environment is the physics space.
    // it shouldnt really be used outside of this class.
    //    environment = new Environment(getStateManager().getState(BulletAppState.class),
    //                              assetManager,
    //                              rootNode);
    //
    //    //example
    //    FlappyBird bird = new FlappyBird(environment.getBulletAppState().getPhysicsSpace(), rootNode);
    //    population = new Population(environment);
    //    population.initPop();
    //    population.add(new Being(bird));

    // evolution is the entire population with stats about it.
    // it has an environment... think of it like a population at a given time or at a specific breeding instance.
    // evolution = new Evolution(environment);


    //evolution.getPopulation().add(new Being(bird));

    //evolution.start();
    initKeys();
  }

  private void initKeys()
  {
    inputManager.addMapping("Update Creature",  new KeyTrigger(KeyInput.KEY_U));

    // Add the names to the action listener.
    inputManager.addListener(this,"Update Creature");
  }

  @Override
  public void onAction(String name, boolean isPressed, float timePerFrame)
  {
    super.onAction(name, isPressed, timePerFrame);

    if (isPressed && name.equals("Update Creature"))
    {
//      beingIndx++;
//      environment.removeFromWorld(); // bug in creature.remove(); ????
//      Being being = evolution.getBeing(beingIndx);
//      environment.addToWorld(being);
//
//      Being being = evolution.getBeing(beingIndx);
//      environment.addToWorld(being);
    }
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

    Game app = new Game();
    app.setShowSettings(false);
    app.setSettings(settings);
    app.start(JmeContext.Type.Headless);
  }

  @Override
  public void simpleUpdate(float deltaSeconds)
  {
    //super.simpleUpdate(deltaSeconds);
    elapTime += deltaSeconds;
    if (elapTime >= 5)
    {
      population.update();
      elapTime = 0;
    }
//     environment.update(deltaSeconds);
  }
}
