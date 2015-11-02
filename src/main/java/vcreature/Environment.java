package vcreature;


import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import vcreature.phenotype.Creature;

/**
 *
 */
public class Environment
{

  boolean creatureInWorld;
  private BulletAppState bulletAppState;
  private AssetManager assetManager;
  private Node rootNode;
  private Being currentBeing;
  private float currF;
  private float elapsedSimulationTime = 0.0f;
  CameraNode camNode;
  Camera cam;


  public Environment(BulletAppState bulletAppState, AssetManager assetManager, Node rootNode)
  {

    // keep a reference to the bullet engine state, the asset manager and
    // the root node
    this.bulletAppState = bulletAppState;
    this.assetManager = assetManager;
    this.rootNode = rootNode;
    this.creatureInWorld = false;
    this.currentBeing = null;
  }


  public BulletAppState getBulletAppState()
  {
    return bulletAppState;
  }

  public AssetManager getAssetManager()
  {
    return assetManager;
  }

  public Node getRootNode()
  {
    return rootNode;
  }


  public void update(float tpf)
  {

    if (currentBeing != null)
    {
      elapsedSimulationTime += tpf;
      // System.out.println(elapsedSimulationTime);
      currentBeing.getPhenotype().updateBrain(elapsedSimulationTime);
      currF = currentBeing.getPhenotype().getFitness();
    }
    else
    {
      elapsedSimulationTime += tpf;
    }
  }

  public void addToWorld(Being v)
  {
    if (currentBeing != null)
    {
      System.out.println("someone already here");
      return;
    }
    v.createPhenotype(this);
    currentBeing = v;
    elapsedSimulationTime = 0.0f;
  }

  public void removeFromWorld(Creature creature)
  {

  }

  public synchronized float beginEvaluation(Being v)
  {
    if (currentBeing != null)
    {
      System.out.println("someone already here");
      return -1;
    }

    // add creature to world
    addToWorld(v);
    float initTime = elapsedSimulationTime;
    // elapsedSimulationTime += .000001;

    while (true)
    {
        //System.out.println(elapsedSimulationTime);
      if (elapsedSimulationTime >= 2.00)
      {
        System.out.println("done");
         removeFromWorld();
        return currF;
      }

    }
  }


  private void focusFlyCam(Creature currentCreature)
  {

  }


  public void setCamNode(CameraNode camNode)
  {
    this.camNode = camNode;

  }


  public void setCamera(Camera camera)
  {
    this.cam = camera;

  }

  public Camera getCamera()
  {
    return cam;
  }

  public void removeFromWorld()
  {

    if (currentBeing != null)
    {
      currentBeing.removePhenotype();
      elapsedSimulationTime = 0.0f;
      currentBeing = null;
    }

  }
}
