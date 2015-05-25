
package shaka;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
	private final float jumpSpeed, moveSpeed;
	private final int jumps;
	
	private SpriteSheet character;
	private float x = 0, y = 0, ySpeed = 0;
	private float jumpsUsed= 0;
	private List<Hit> hits = new ArrayList<Hit>();
	
	public Character(String file, float jumpSpeed, float moveSpeed, int jumps) throws SlickException {
		this.file = file;
		this.jumpSpeed = jumpSpeed;
		this.moveSpeed = moveSpeed;
		this.jumps = jumps;
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
		
		Iterator<Hit> iterator = hits.iterator();
		while(iterator.hasNext()) {
			Hit hit = iterator.next();
			hit.update(delta);
			if (hit.isDone(y == gc.getHeight() - character.getSprite(0, 0).getHeight(), x == gc.getWidth() - character.getSprite(0, 0).getWidth() || x == 0)) {
				iterator.remove();
			}
		}
		
		boolean stunned = false;
		double xForce = 0, yForce =0;
		for (int i = 0; i < hits.size() && !stunned; i++) {
			stunned =  hits.get(i).isStunned();
			xForce += hits.get(i).getXForce();
			yForce += hits.get(i).getYForce();
		}
		
		if (!stunned) {
			if (hits.isEmpty()) {
				if (gc.getInput().isKeyPressed(Input.KEY_W) && jumpsUsed < jumps && ySpeed >= 0) {
					ySpeed = jumpSpeed;
					jumpsUsed++;
				}

				if (gc.getInput().isKeyDown(Input.KEY_S) && ySpeed < 0) {
					y += delta * moveSpeed;
				}

				if (gc.getInput().isKeyDown(Input.KEY_A)) {
					x -= delta * moveSpeed;
				}

				if (gc.getInput().isKeyDown(Input.KEY_D)) {
					x += delta * moveSpeed;
				}
			} else {
				if (gc.getInput().isKeyDown(Input.KEY_W)) {
					yForce -= Math.abs(yForce) * .2;
				}

				if (gc.getInput().isKeyDown(Input.KEY_S)) {
					yForce += Math.abs(yForce) * .2;
				}

				if (gc.getInput().isKeyDown(Input.KEY_A)) {
					xForce -= Math.abs(xForce) * .2;
				}

				if (gc.getInput().isKeyDown(Input.KEY_D)) {
					xForce += Math.abs(xForce) * .2;
				}
			}
		}
		
		x += xForce * delta;
		y += yForce * delta;
		y += ySpeed * delta;
		ySpeed += ShakaMap.GRAVITY * delta;

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
		hits.add(hit);
	}
	
	public void keyPressed(int i, char c) {
		switch (i) {
			case Input.KEY_E:
				hit(new QHit(Math.PI / 2));
				break;

			case Input.KEY_Q:
				hit(new EHit(0));
				break;
		}
	}
}
