package summerproject;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

public class IronOre extends Circle implements Entity {

	private static final Logger log = LogManager.getLogger(IronOre.class);
	
	private Image image;
	
	public IronOre(float centerPointX, float centerPointY) {
		super(centerPointX, centerPointY, 0);
		
		try {
			// Getting a file's absolute path is more robust than a mere hardcoded String path
			File img = new File("res/ore/iron.png");
			image = new Image(img.getAbsolutePath());
			this.setRadius(Math.max(image.getWidth(), image.getHeight()));
			
			// Rotating ore to a random angle
			int angle = (int)(Math.random() * 360);
			image.rotate(angle);
		} catch (SlickException e) {
			log.error("Error loading iron ore image.", e);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(image, this.x, this.y);
	}

	@Override
	public void handleCollision(Entity entity) {
		
	}
}
