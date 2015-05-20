package summerproject;


import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class World implements Entity, Serializable {
	
	/*
	 * TODO:
	 * 
	 * Later:
	 * name world files based off of date, time, and world version (serialVersionUID)
	 */
	
	/**
	 * Version of the class, need to update this number if the class
	 * becomes incompatible with the earlier version. Serialization
	 * is used for saving the state of the game.
	 */
	private static final long serialVersionUID = 2L;
	
	private static final Logger log = LogManager.getLogger(World.class);
	
	public TestEntity entity = new TestEntity(0, 0, 50);
		
	public World() {
		// You're going to have to think about
		// how your going to spawn asteroids randomly without making it seem like
		// they are just everywhere and evenly spaced apart. Maybe try playing with some algorithms
		// and seeing the results. You could even try the algorithm that makes it more likely that
		// they spawn next to an asteroid, this will either create clusters, or make a very
		// dense and infinite field of asteroids... will also need to find the ratio
		// between ore containing asteroids and plain rock asteroids
		// Could create a home base with a few starter miners and or items..
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		entity.update(container, game, delta);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.red);
		g.drawString("Playing.. test", 100, 100);
		
		entity.render(container, game, g);
	}
	
	public static long getVersion() {
		return serialVersionUID;
	}
}
