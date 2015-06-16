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

public class Menu extends BasicGameState {
	
	private static final Logger log = LogManager.getLogger(Menu.class);
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		container.setTargetFrameRate(120);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		container.setClearEachFrame(true);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("Menu: Press [Esc] to play.", 100, 100);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			input.clearKeyPressedRecord();
			Load load = (Load) game.getState(State.LOAD.ordinal());
			load.isNewGame(true);
			game.enterState(State.LOAD.ordinal());
		}
	}

	@Override
	public int getID() {
		return State.MENU.ordinal();
	}
}
