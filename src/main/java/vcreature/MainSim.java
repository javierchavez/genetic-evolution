package vcreature;


import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import vcreature.Algorithms.GeneticAlgorithm;
import vcreature.genotype.Genome;
import vcreature.genotype.GenomeGenerator;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.PhysicsConstants;

import java.util.Random;

public class MainSim extends SimpleApplication implements ActionListener
{
  private BulletAppState bulletAppState;
  private PhysicsSpace physicsSpace;
  private float cameraAngle = (float)(Math.PI/2.0);
  private float elapsedSimulationTime = 0.0f;
  
  //Temporary vectors used on each frame. They here to avoid instanciating new vectors on each frame
  private Vector3f tmpVec3; //
  //private FlappyBird myCreature;
  private boolean isCameraRotating = true;
  private Population population;
   private Creature creature;
  private CreatureSynthesizer creatureSynthesizer;
  private GenomeSynthesizer genomeSynthesizer;
  private boolean add = true;
  private GeneticAlgorithm breeding;
  Evolution evolution;
  GenomeGenerator generator;


  @Override
  public void simpleInitApp()
  {
    /**
     * Set up Physics
     */
    bulletAppState = new BulletAppState();
    stateManager.attach(bulletAppState);
    physicsSpace = bulletAppState.getPhysicsSpace();
    //bulletAppState.setDebugEnabled(true);
    
    physicsSpace.setGravity(PhysicsConstants.GRAVITY);
    physicsSpace.setAccuracy(PhysicsConstants.PHYSICS_UPDATE_RATE);
    physicsSpace.setMaxSubSteps(4);


    //Set up inmovable floor
    Box floor = new Box(50f, 0.1f, 50f);
    Material floor_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    Texture floorTexture = assetManager.loadTexture("Textures/FloorTile.png");

    floorTexture.setWrap(Texture.WrapMode.Repeat);
    floor_mat.setTexture("ColorMap", floorTexture);

    floor.scaleTextureCoordinates(new Vector2f(50, 50));
    Geometry floor_geo = new Geometry("Floor", floor);
    floor_geo.setMaterial(floor_mat);
    floor_geo.setShadowMode(ShadowMode.Receive);
    floor_geo.setLocalTranslation(0, -0.11f, 0);
    rootNode.attachChild(floor_geo);

    /* Make the floor physical with mass 0.0f */
    RigidBodyControl floor_phy = new RigidBodyControl(0.0f);
    floor_geo.addControl(floor_phy);
    physicsSpace.add(floor_phy);
    floor_phy.setFriction(PhysicsConstants.GROUND_SLIDING_FRICTION);
    floor_phy.setRestitution(PhysicsConstants.GROUND_BOUNCINESS);
    floor_phy.setDamping(PhysicsConstants.GROUND_LINEAR_DAMPINING, 
            PhysicsConstants.GROUND_ANGULAR_DAMPINING);



    Block.initStaticMaterials(assetManager);
    initLighting();
    initKeys();

    flyCam.setDragToRotate(true);

    creatureSynthesizer = new CreatureSynthesizer();
    genomeSynthesizer = new GenomeSynthesizer(physicsSpace, rootNode);


    FlappyBird flappyBird = new FlappyBird(physicsSpace, rootNode);
    Genome genome = creatureSynthesizer.encode(flappyBird);
    flappyBird.remove();
    //    creature = genomeSynthesizer.encode(genome);

    breeding = new GeneticAlgorithm(this);
    population = new Population(breeding);
//    population.initPop();
    Being b = new Being();
    b.setGenotype(genome);
    population.add(b);


    generator = new GenomeGenerator();
    generator.generateGenome();
//
    for (int i = 0; i < 100; i++)
    {
      Being bb = new Being();
      Genome g = generator.generateGenome();
      bb.setGenotype(g);
      population.add(bb);
    }


//     creature = bird;
//    bird.remove();

    evolution = new Evolution(population);

    
  }

  
  private void initLighting()
  {
    //  ust add a light to make the lit object visible!
    DirectionalLight sun = new DirectionalLight();
    sun.setDirection(new Vector3f(0, -10, -2).normalizeLocal());
    sun.setColor(ColorRGBA.White);
    rootNode.addLight(sun);

    //Without ambient light, the seen looks like outerspace with razer sharp black shadows.
    AmbientLight ambient = new AmbientLight();
    ambient.setColor(ColorRGBA.White.mult(0.3f));
    rootNode.addLight(ambient);
    
    // SHADOW
    // the second parameter is the resolution. Experiment with it! (Must be a power of 2)
    DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(assetManager, 2048, 2);
    dlsr.setLight(sun);
    viewPort.addProcessor(dlsr);
  }
  
  
  
  private void initKeys() {
    inputManager.addMapping("Quit",  new KeyTrigger(KeyInput.KEY_Q));
    inputManager.addMapping("Toggle Camera Rotation",  new KeyTrigger(KeyInput.KEY_P));

    // Add the names to the action listener.
    inputManager.addListener(this,"Quit");
    inputManager.addListener(this,"Toggle Camera Rotation");
  }
  
  public void onAction(String name, boolean isPressed, float timePerFrame) 
  {
    if (isPressed && name.equals("Toggle Camera Rotation"))
    { isCameraRotating = !isCameraRotating;
    }
    else if (name.equals("Quit"))
    { //System.out.format("Creature Fitness (Maximium height of lowest point) = %.3f meters]\n", myCreature.getFitness());
      System.exit(0);
    }
  }


  boolean inSim =false;
  boolean newGenerationSpwan = false;
  boolean flipFlop = true;
  Being b = null;
  
  /* Use the main event loop to trigger repeating actions. */
  @Override
  public void simpleUpdate(float deltaSeconds)
  {
    elapsedSimulationTime += deltaSeconds;

    //we need to spawn a thread to handlethis.


    if (flipFlop && elapsedSimulationTime > 11)
    {
      flipFlop = false;
      // creature = new FlappyBird(physicsSpace, rootNode);
      // creature.remove();
      Genome genome = null;

      if (inSim)
      {
        b.setFitness(creature.getFitness());
        if (!breeding.getQueue().isEmpty())
        {
          breeding.getQueue().poll();
        }
        // System.out.println(creature.getFitness());
        creature.remove();
//        if (evolution.generations() < 30 && newGenerationSpwan)
//        {
//          newGenerationSpwan = false;
//        }
      }


      if (!breeding.getQueue().isEmpty())
      {
        b = (Being) breeding.getQueue().peek();
        if (b != null)
        {
          System.out.println("Creature being evaluated.");
          inSim = true;
          genome = b.getGenotype();

          creature = genomeSynthesizer.encode(genome);
          elapsedSimulationTime = 0;
        }
      }
      else
      {
        if (!newGenerationSpwan || breeding.currGen() >= 200)
        {
          System.out.println("New generation kicked off");
          newGenerationSpwan = true;
          new Thread(new Runnable()
          {
            @Override
            public void run()
            {
              evolution.getSubs().get(genRandDim(evolution.getSubs().size())).nextGeneration();
            }
          }).start();
        }

      }
      flipFlop = true;

    }
    if (creature != null)
    {
      creature.updateBrain(elapsedSimulationTime);
    }

    //print("simpleUpdate() elapsedSimulationTime=", (float)elapsedSimulationTime);
    //print("simpleUpdate() joint1.getHingeAngle()=", joint1.getHingeAngle());
    // myCreature.updateBrain(elapsedSimulationTime);

    if (isCameraRotating)
    {
      //Move camera continously in circle of radius 25 meters centered 10 meters
      //  above the origin. 
      cameraAngle += deltaSeconds * 2.0 * Math.PI / 60.0; //rotate full circle every minute
      float x = (float) (25.0 * Math.cos(cameraAngle));
      float z = (float) (25.0 * Math.sin(cameraAngle));
    
      tmpVec3 = new Vector3f(x, 10.0f, z);
      cam.setLocation(tmpVec3);
      cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
    }
  }
 
  
 

  
  private void print(String msg, float x)
  {
    String className = this.getClass().getSimpleName();
    System.out.format("%s.%s %.3f\n", className, msg, x);
  }
  
  private void print(String msg, Vector3f vector)
  {
    String className = this.getClass().getSimpleName();
    System.out.format("%s.%s [%.3f, %.3f, %.3f]\n", className, msg, vector.x, vector.y, vector.z);
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

    MainSim app = new MainSim();
    app.setShowSettings(false);
    app.setSettings(settings);

    //app.start(JmeContext.Type.OffscreenSurface);
    app.start();
  }
  Random random = new Random();
  private int genRandDim(int max)
  {
    return random.nextInt(max - 1) + 0;
  }
}
