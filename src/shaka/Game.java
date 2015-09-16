package shaka;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Spencer
 */
public class Game implements GameState {

	private final Character[] characters = new Character[8];
	private Image background;
	private boolean close;

	@Override
	public int getID() {
		return ShakaMap.GAMEID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image(ShakaMap.BACKGROUNDIMAGE);
		characters[0] = new NormalShaka();
		for (int i = 0; i < characters.length && characters[i] != null; i++) {
			characters[i].init(gc, sbg);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw();
		for (int i = 0; i < characters.length && characters[i] != null; i++) {
			characters[i].render(gc, sbg, g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (close) {
			gc.exit();
		}
		for (int i = 0; i < characters.length && characters[i] != null; i++) {
			characters[i].update(gc, sbg, delta);
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
		switch (i) {
			case Input.KEY_ESCAPE:
				close = true;
				break;
		}
		
		for (int j = 0; j < characters.length && characters[j] != null; j++) {
			characters[j].keyPressed(i, c);
		}
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
