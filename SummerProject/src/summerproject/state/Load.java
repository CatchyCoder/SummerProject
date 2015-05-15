package summerproject.state;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import summerproject.SerializationUtil;
import summerproject.World;

/**
 * Used to prepare the Play state before actually playing.
 * Loads and/or generates the game world and performs any other
 * operations needed before playing.
 * 
 * @author Clay Kuznia
 *
 */
public class Load extends BasicGameState implements Runnable {
	
	private static final Logger log = LogManager.getLogger(Load.class);
	
	private boolean isNewGame = true;
	private Thread thread;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// Using a separate thread to load the world, and the main thread
		// to update the screen.
		thread = new Thread(this);
		thread.setName("world-generation");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("Creating game...", 100, 100);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Play play = (Play) game.getState(State.PLAY.ordinal());
		
		// New game
		if(isNewGame) play.setWorld(new World());
		// Load a game
		else {
			// TODO: this is a fake load
			//World world = new World();
			//world.testMethod(999);
			//SerializationUtil.save(world, "world");
			World world = (World) SerializationUtil.load("world");
			play.setWorld(world);
			log.trace("loaded value = " + world.testVar);
		}
		
		game.enterState(State.PLAY.ordinal());
	}
	
	public void isNewGame(boolean value) {
		isNewGame = value;
	}

	@Override
	public int getID() {
		return State.LOAD.ordinal();
	}

	@Override
	public void run() {
		
	}
}
