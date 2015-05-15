package summerproject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

@SuppressWarnings("serial")
public class TestEntity extends Circle implements Entity {
	
	public TestEntity(float centerPointX, float centerPointY, float radius) {
		super(centerPointX, centerPointY, radius);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
