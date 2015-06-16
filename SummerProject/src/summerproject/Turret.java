package summerproject;

import java.io.File;
import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import summerproject.state.Menu;

public class Turret extends Circle implements Entity {

	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LogManager.getLogger(Turret.class);
	
	private Image image;
	
	public Turret(float x, float y) {
		super(x, y, 0);
		
		try {
			// Getting a file's absolute path is more robust than a mere hardcoded String path
			File img = new File("res/entity/turret.png");
			image = new Image(img.getAbsolutePath());
			this.setRadius(Math.max(image.getWidth(), image.getHeight()));
			
		} catch (SlickException e) {
			log.error("Error loading turret image.", e);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		Input input = container.getInput();
		
		int mouseX = input.getAbsoluteMouseX();
		int mouseY = input.getAbsoluteMouseY();
		
		World.rotateImageTo(image, this.getCenterX(), this.getCenterY(), mouseX, mouseY);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(image, this.x, this.y);
	}

	@Override
	public void handleCollision(Entity entity) {
		
	}
}
