package summerproject;


import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class World implements Entity, Serializable {
	
	/**
	 * Version of the class, need to update every time a new version is made
	 * to show that it is now compatible with older versions. Serialization
	 * is used for saving the state of the game.
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LogManager.getLogger(World.class);
		
	public int testVar = 0;
	
	public World() {
		// I'm drunk right now, but you're going to have to think about
		// how your going to spawn asteroids randomly without making it seem like
		// they are just everywhere and evenly spaced apart. Maybe try playing with some algorithms
		// and seeing the results. You could even try the algorithm that makes it more likely that
		// they spawn next to an asteroid, this will either create clusters, or make a very
		// dense and infinite field of asteroids... will also need to find the ratio
		// between ore containing asteroids and plain rock asteroids
		
		// Could create a home base with a few starter miners and or items.. will figure out when sober
		// this game actually sounds kinda fun when I'm super drunk, like seriously.... I have a 3 day weekend
		// (well sorda) and omg it's soooo nice not having to worry about schoolwork and just thinking about
		// all the possibilities in summer :)... goodnight self, this will be interesting when im sober, haha
		// Good luck on your future programming endeavors.... ooohhhh I had like 6 drinks at least and I 
		// feel ssssosoooooo goood right now omg... its out of this world oohohoh.
	}
	
	public void testMethod(int value) {
		testVar = value;
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.red);
		g.drawString("Playing..", 100, 100);
	}
}
