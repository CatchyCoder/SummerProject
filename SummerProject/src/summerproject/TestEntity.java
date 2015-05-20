package summerproject;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

public class TestEntity extends Circle implements Entity {
	
	private static final long serialVersionUID = 1L;

	public TestEntity(float centerPointX, float centerPointY, float radius) {
		super(centerPointX, centerPointY, radius);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		this.x += delta;
		this.y += delta;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.red);
		g.fillOval(this.x, this.y, this.radius, this.radius);
	}

}
