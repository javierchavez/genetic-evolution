package vcreature;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.system.JmeContext;
import com.jme3.texture.Texture;
import vcreature.phenotype.PhysicsConstants;


public abstract class AbstractApplication extends SimpleApplication implements ActionListener
{


  private BulletAppState bulletAppState;
  private PhysicsSpace physicsSpace;
  private boolean isCameraRotating = true;
  private float cameraAngle = (float) (Math.PI / 2.0);
  private Vector3f tmpVec3; //

  @Override
  public void simpleInitApp()
  {
    bulletAppState = new BulletAppState();
    stateManager.attach(bulletAppState);
    physicsSpace = bulletAppState.getPhysicsSpace();
    //bulletAppState.setDebugEnabled(true);

    physicsSpace.setGravity(PhysicsConstants.GRAVITY);
    physicsSpace.setAccuracy(PhysicsConstants.PHYSICS_UPDATE_RATE);
    physicsSpace.setMaxSubSteps(4);


    Box floor = new Box(50f, 0.1f, 50f);


    floor.scaleTextureCoordinates(new Vector2f(50, 50));
    Geometry floor_geo = new Geometry("Floor", floor);
    floor_geo.setShadowMode(RenderQueue.ShadowMode.Receive);
    floor_geo.setLocalTranslation(0, -0.11f, 0);
    rootNode.attachChild(floor_geo);

    if (this.context.getType() != JmeContext.Type.Headless)
    {
      initLighting();
      Material floor_mat = new Material(assetManager,
                                        "Common/MatDefs/Misc/Unshaded.j3md");
      Texture floorTexture = assetManager.loadTexture("Textures/FloorTile.png");
      floorTexture.setWrap(Texture.WrapMode.Repeat);
      floor_mat.setTexture("ColorMap", floorTexture);
      floor_geo.setMaterial(floor_mat);
    }

    /* Make the floor physical with mass 0.0f */
    RigidBodyControl floor_phy = new RigidBodyControl(0.0f);
    floor_geo.addControl(floor_phy);
    physicsSpace.add(floor_phy);
    floor_phy.setFriction(PhysicsConstants.GROUND_SLIDING_FRICTION);
    floor_phy.setRestitution(PhysicsConstants.GROUND_BOUNCINESS);
    floor_phy.setDamping(PhysicsConstants.GROUND_LINEAR_DAMPINING,
                         PhysicsConstants.GROUND_ANGULAR_DAMPINING);


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
    DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(
            assetManager,
            2048,
            2);
    dlsr.setLight(sun);
    viewPort.addProcessor(dlsr);
  }


  @Override
  public void onAction(String name, boolean isPressed, float tpf)
  {

  }


  @Override
  public void simpleUpdate(float tpf)
  {
    super.simpleUpdate(tpf);

    if (isCameraRotating && this.context.getType() != JmeContext.Type.Headless)
    {
      //Move camera continously in circle of radius 25 meters centered 10 meters
      //  above the origin.
      cameraAngle += tpf * 2.0 * Math.PI / 60.0; //rotate full circle every minute
      float x = (float) (25.0 * Math.cos(cameraAngle));
      float z = (float) (25.0 * Math.sin(cameraAngle));

      tmpVec3 = new Vector3f(x, 10.0f, z);
      cam.setLocation(tmpVec3);
      cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
    }
  }

  public PhysicsSpace getPhysicsSpace()
  {
    return physicsSpace;
  }


}
