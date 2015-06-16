package summerproject.state;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
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
public class Load extends BasicGameState {
	
	private static final Logger log = LogManager.getLogger(Load.class);
	
	private boolean loaded = false, isNewGame = true, failed = false, rendered = false;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		render(container, game, container.getGraphics());
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		if(!failed) g.drawString("Creating game...", 100, 100);
		else g.drawString(
				"Failed to load game. The save file may be corrupted or\n" + 
				"incompatible with the current game version. Press [Esc]\n" +
				"to return to menu.", 100, 100);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// Update is always called first, so we need to skip the first update call
		// in order to render before updating
		if(!rendered) {
			rendered = true;
			return;
		}
		
		// If an attempt to load has not been made yet, load the game
		if(!loaded && !failed) {
			Play play = (Play) game.getState(State.PLAY.ordinal());
			
			// New game
			if(isNewGame) play.setWorld(new World(container.getWidth(), container.getHeight()));
			// Load game
			else {
				// Load the world
				World world = (World) SerializationUtil.load("world");
				
				// Checking for a failed load
				if(world == null) {
					failed = true;
					return;
				}
				
				play.setWorld(world);
				loaded = true;				
			}
			
			// Resetting load values
			reset();
			game.enterState(State.PLAY.ordinal());
		}
		else {
			// The game must have failed to load, so wait for user input
			
			Input input = container.getInput();
			
			if(input.isKeyPressed(Input.KEY_ESCAPE)) {
				// Resetting load values
				reset();
				input.clearKeyPressedRecord();
				game.enterState(State.MENU.ordinal());
			}
		}
	}
	
	private void reset() {
		loaded = failed = false;
		isNewGame = true;
	}
	
	public void isNewGame(boolean value) {
		isNewGame = value;
	}

	@Override
	public int getID() {
		return State.LOAD.ordinal();
	}
}
