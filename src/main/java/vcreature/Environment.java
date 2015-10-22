package vcreature;


import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import vcreature.phenotype.Creature;

import java.lang.reflect.InvocationTargetException;

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
      currentBeing.update(elapsedSimulationTime);

    }
  }

  public void addToWorld(Being v)
  {
    Class<? extends Creature> creatureClass = v.getPhenotype().getClass();
    try
    {
      creatureClass.getDeclaredConstructor(PhysicsSpace.class,
                                           Node.class).newInstance(
              getBulletAppState().getPhysicsSpace(), getRootNode());
      elapsedSimulationTime = 0.0f;
      currentBeing = v;
    }
    catch (InstantiationException e)
    {
      e.printStackTrace();
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
    catch (InvocationTargetException e)
    {
      e.printStackTrace();
    }
    catch (NoSuchMethodException e)
    {
      e.printStackTrace();
    }


  }

  public void removeFromWorld(Creature creature)
  {

  }


  public void beginEvaluation(Creature creature)
  {
    // add creature to world

    // Evaluation
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
      currentBeing.getPhenotype().remove();
      elapsedSimulationTime = 0.0f;
      currentBeing = null;

    }

  }
}
