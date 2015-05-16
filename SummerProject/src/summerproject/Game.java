package summerproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import summerproject.state.Load;
import summerproject.state.Menu;
import summerproject.state.Pause;
import summerproject.state.Play;
import summerproject.state.State;

public class Game extends StateBasedGame {
	
	private static final Logger log = LogManager.getLogger(Game.class);

	public Game(String name) {
		super(name);
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {		
		// Initializing each state
		this.addState(new Menu());
		this.addState(new Load());
		this.addState(new Play());
		this.addState(new Pause());
		this.enterState(State.MENU.ordinal());
	}
}
