package shaka;

import java.io.File;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Spencer
 */
public class Shaka extends StateBasedGame {

	public Shaka() {
		super("Shaka");
	}
	
	public static void main(String[] args) {
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "natives"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.libarypath", System.getProperty("org.lwjgl.librarypath"));

		try {
			AppGameContainer app = new AppGameContainer(new Shaka());
			app.setDisplayMode(1920, 1080, true);
			app.setTargetFrameRate(60);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
		}
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		
	}
}
