
package shaka;

/**
 *
 * @author Spencer
 */
public abstract class Hit {
	
	private final int duration, stun;
	private final double xForce, yForce;
	
	private int time = 0;
	
	public Hit(int duration, double force, int stun, double direction) {
		this.duration = duration;
		xForce = force * Math.cos(direction);
		yForce = force * Math.sin(direction);
		this.stun = stun;
	}
	
	public void update(int delta) {
		time += delta;
	}
	
	public boolean isDone(boolean onGround, boolean onWall) {
		return time > duration || (onGround && Math.abs(xForce) < .001 && !isStunned()) || (onWall && Math.abs(yForce) < .001 && !isStunned());
	}
	
	public boolean isStunned() {
		return stun >= time;
	}
	
	public double getXForce() {
		return xForce;
	}
	
	public double getYForce() {
		return yForce;
	}
}
