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
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		world.render(container, game, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
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
