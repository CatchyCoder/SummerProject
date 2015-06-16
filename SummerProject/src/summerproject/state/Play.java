package summerproject.state;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import summerproject.World;

public class Play extends BasicGameState {

	private static final Logger log = LogManager.getLogger(Play.class);
	
	// Will be initialized by the Load state.
	private World world;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		/*
		 * The entire screen is drawn on for each frame, so we don't need to
		 * clear the frame before drawing
		 */
		container.setClearEachFrame(false);
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
		world = null; // Destroy world data to clean up RAM
		System.gc();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		world.render(container, game, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			input.clearKeyPressedRecord();
			game.enterState(State.MENU.ordinal());
		}
		else world.update(container, game, delta);
	}
	
	public void setWorld(World world) {
		this.world = world;
	}

	@Override
	public int getID() {
		return State.PLAY.ordinal();
	}
}
