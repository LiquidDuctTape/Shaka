
package shaka;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game implements GameState {

	private Image background, shaka;
	private float x, y;
	
	@Override
	public int getID() {
		return ShakaMap.GAMEID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image(ShakaMap.BACKGROUNDIMAGE);
		shaka = new Image(ShakaMap.SHAKAIMAGE);
		x = gc.getWidth() / 2;
		y = gc.getHeight() / 2;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
		background.draw();
		shaka.draw(x, y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (gc.getInput().isKeyDown(Input.KEY_W)) {
			y -= delta * 2;
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_S)) {
			y += delta * 2;
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_A)) {
			x -= delta * 2;
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_D)) {
			x += delta * 2;
		}
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void mouseWheelMoved(int i) {
		
	}

	@Override
	public void mouseClicked(int i, int i1, int i2, int i3) {
		
	}

	@Override
	public void mousePressed(int i, int i1, int i2) {
		
	}

	@Override
	public void mouseReleased(int i, int i1, int i2) {
		
	}

	@Override
	public void mouseMoved(int i, int i1, int i2, int i3) {
		
	}

	@Override
	public void mouseDragged(int i, int i1, int i2, int i3) {
		
	}

	@Override
	public void setInput(Input input) {
		
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void inputEnded() {
		
	}

	@Override
	public void inputStarted() {
		
	}

	@Override
	public void keyPressed(int i, char c) {
		
	}

	@Override
	public void keyReleased(int i, char c) {
		
	}

	@Override
	public void controllerLeftPressed(int i) {
		
	}

	@Override
	public void controllerLeftReleased(int i) {
		
	}

	@Override
	public void controllerRightPressed(int i) {
		
	}

	@Override
	public void controllerRightReleased(int i) {
		
	}

	@Override
	public void controllerUpPressed(int i) {
		
	}

	@Override
	public void controllerUpReleased(int i) {
		
	}

	@Override
	public void controllerDownPressed(int i) {
		
	}

	@Override
	public void controllerDownReleased(int i) {
		
	}

	@Override
	public void controllerButtonPressed(int i, int i1) {
		
	}

	@Override
	public void controllerButtonReleased(int i, int i1) {
		
	}
	
}
