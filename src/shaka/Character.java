
package shaka;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Spencer
 */
public abstract class Character {
	
	private final String file;
	private final float jumpSpeed, moveSpeed, friction, acceleration;
	private final int jumps;
	
	private SpriteSheet character;
	private float x = 0, y = 0, ySpeed = 0, xSpeed = 0;
	private float jumpsUsed= 0;
	private Hit hit;
	
	public Character(String file, float jumpSpeed, float moveSpeed, int jumps, float friction, float acceleration) throws SlickException {
		this.file = file;
		this.jumpSpeed = jumpSpeed;
		this.moveSpeed = moveSpeed;
		this.jumps = jumps;
		this.friction = friction;
		this.acceleration = acceleration;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		character = new SpriteSheet(file, ShakaMap.CHARACTERSIZE, ShakaMap.CHARACTERSIZE);
		x = gc.getWidth() / 2;
		y = gc.getHeight() / 2;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		character.getSprite(y == gc.getHeight() - character.getSprite(0, 0).getHeight() ? 0 : 1, 0).draw((int) x, (int) y);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		boolean stunned = false;
		
		if (isHit()) {
			hit.update(delta);
			if (hit.isDone(y == gc.getHeight() - character.getSprite(0, 0).getHeight(), x == gc.getWidth() - character.getSprite(0, 0).getWidth() || x == 0)) {
				hit = null;
			} else {
				stunned =  hit.isStunned();
			}
		}
		
		if (!stunned) {
				if (gc.getInput().isKeyPressed(Input.KEY_W) && jumpsUsed < jumps && ySpeed >= 0) {
					ySpeed = jumpSpeed;
					jumpsUsed++;
				}

				if (xSpeed > -moveSpeed && gc.getInput().isKeyDown(Input.KEY_A)) {
					xSpeed -= acceleration * delta;
					if (xSpeed < -moveSpeed) {
						xSpeed = -moveSpeed;
					}
				}

				if (xSpeed < moveSpeed && gc.getInput().isKeyDown(Input.KEY_D)) {
					xSpeed += acceleration * delta;
					if (xSpeed > moveSpeed) {
						xSpeed = moveSpeed;
					}
				}
		}
		
		x += xSpeed * delta;
		y += ySpeed * delta;
		ySpeed += ShakaMap.GRAVITY * delta;
		
		if (xSpeed > friction * delta) {
			xSpeed -= friction * delta;
		} else if (xSpeed < -friction * delta) {
			xSpeed += friction * delta;
		} else {
			xSpeed = 0;
		}

		if (x < 0) {
			x = 0;
		} else if (x > gc.getWidth() - character.getSprite(0, 0).getWidth()) {
			x = gc.getWidth() - character.getSprite(0, 0).getWidth();
		}

		if (y >= gc.getHeight() - character.getSprite(0, 0).getHeight()) {
			y = gc.getHeight() - character.getSprite(0, 0).getHeight();
			ySpeed = 0;
			jumpsUsed = 0;
		}
	}
	
	public void hit(Hit hit) {
		this.hit = hit;
		xSpeed += hit.getXForce();
		ySpeed += hit.getYForce();
	}
	
	public boolean isHit() {
		return hit != null;
	}
	
	public void keyPressed(int i, char c) {
		switch (i) {
			case Input.KEY_E:
				hit(new QHit(Math.PI / 3));
				break;

			case Input.KEY_Q:
				hit(new EHit(1.2*Math.PI));
				break;
		}
	}
}
