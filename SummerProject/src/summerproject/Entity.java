package summerproject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public interface Entity {
	
	/**
	 * Updates this entity, called for each frame update.
	 * 
	 * @param container
	 * @param game
	 * @param delta
	 */
	public abstract void update(GameContainer container, StateBasedGame game, int delta);
	
	/**
	 * Render this entity to the game's graphics context
	 * 
	 * @param container
	 * @param game
	 * @param g
	 */
	public abstract void render(GameContainer container, StateBasedGame game, Graphics g);
	
	public abstract void handleCollision(Entity entity);
}
