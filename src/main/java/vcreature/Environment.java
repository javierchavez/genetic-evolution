package vcreature;


/**
 * Wrapper to hold physics this should only belong to evolution.
 */
public class Environment
{
//
//  static boolean creatureInWorld;
//  private BulletAppState bulletAppState;
//  private AssetManager assetManager;
//  private Node rootNode;
//  private Being currentBeing;
//  private float currF;
//  private static float elapsedSimulationTime = 0.0f;
//
//
//  public Environment(BulletAppState bulletAppState, AssetManager assetManager, Node rootNode)
//  {
//
//    // keep a reference to the bullet engine state, the asset manager and
//    // the root node
//    this.bulletAppState = bulletAppState;
//    this.assetManager = assetManager;
//    this.rootNode = rootNode;
//    creatureInWorld = false;
//    this.currentBeing = null;
//  }
//
//
//  public BulletAppState getBulletAppState()
//  {
//    return bulletAppState;
//  }
//
//  public AssetManager getAssetManager()
//  {
//    return assetManager;
//  }
//
//  public Node getRootNode()
//  {
//    return rootNode;
//  }
//
//
//  public void update(float tpf)
//  {
//
//    if (creatureInWorld)
//    {
//      elapsedSimulationTime += tpf;
//      // System.out.println(elapsedSimulationTime);
//      currentBeing.getPhenotype().updateBrain(elapsedSimulationTime);
//      currF = currentBeing.getPhenotype().getFitness();
//    }
//  }
//
//  public void addToWorld(Being v)
//  {
//    if (!creatureInWorld)
//    {
//      System.out.println("someone already here Add");
//      return;
//    }
//    v.createPhenotype(this);
//    currentBeing = v;
//    elapsedSimulationTime = 0.0f;
//    // creatureInWorld = true;
//  }
//
//  /**
//   * Race condition
//   * @param v
//   * @return
//   */
//  public synchronized float beginEvaluation(Being v)
//  {
//    if (creatureInWorld)
//    {
//      System.out.println("someone already here BeginEval" );
//      creatureInWorld = true;
//      return -1;
//    }
//
//    v.createPhenotype(this);
//    currentBeing = v;
//    elapsedSimulationTime = 0.0f;
//    creatureInWorld = true;
//
//
//    while (true)
//    {
//      creatureInWorld = true;
//      if (elapsedSimulationTime >= 2.00)
//      {
//        System.out.println("done");
//         removeFromWorld();
//
//        return currF;
//      }
//      else if(elapsedSimulationTime ==0)
//      {
//
//        System.out.println("d");
//      }
//
//    }
//  }
//
//
//  public void removeFromWorld()
//  {
//
//    if (currentBeing != null)
//    {
//      currentBeing.removePhenotype();
//      elapsedSimulationTime = 0.0f;
//      currentBeing = null;
//      creatureInWorld = false;
//    }
//
//  }
}
