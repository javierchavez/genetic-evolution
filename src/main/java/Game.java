
import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;

/**
 * @author Javier
 */
public class Game extends SimpleApplication {

    public static void main(String[] args) {
        final AppSettings appSettings = new AppSettings(true);
        appSettings.setTitle("Biacode - BANG!");
        final Game game = new Game();
        game.setSettings(appSettings);
        game.start();
    }

    @Override
    public void simpleInitApp() {
   
    }

    @Override
    public void simpleUpdate(float tpf) {
        super.simpleUpdate(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        super.simpleRender(rm);
    }
}
