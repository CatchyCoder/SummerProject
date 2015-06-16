package summerproject;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Circle implements Entity {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LogManager.getLogger(Player.class);
	
	private float speed, dSpeed;
	private Image image;
	
	// Used to see how much the player has moved from it's start point
	// This way objects can be correctly rendered around the player
	// since the player is always in the center of the world
	private int offsetX = 0, offsetY = 0;
	
	public Player(final int CONT_WIDTH, final int CONT_HEIGHT) {
		super(0, 0, 0);
		speed = 0.3F;
		dSpeed = (float) Math.sqrt(speed * speed / 2.0);
		
		try {
			// Getting a file's absolute path is more robust than a mere hardcoded String path
			File img = new File("res/icons/icon_32.png");
			image = new Image(img.getAbsolutePath());
			this.setRadius(Math.max(image.getWidth(), image.getHeight()) / 2);
			this.setCenterX(CONT_WIDTH / 2);
			this.setCenterY(CONT_HEIGHT / 2);
		} catch (SlickException e) {
			log.error("Error loading player image.", e);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		Input input = container.getInput();
		
		boolean up = input.isKeyDown(Input.KEY_W);
		boolean down = input.isKeyDown(Input.KEY_S);
		boolean left = input.isKeyDown(Input.KEY_A);
		boolean right = input.isKeyDown(Input.KEY_D);
		
		// Checking if more than one key is pressed,
		// If so, diagonal speed is used
		float currentSpeed = speed;
		if(isMultiKey(up, down, left, right)) currentSpeed = dSpeed;
		
		// Incorporating time passed
		currentSpeed *= delta;
		
		// Player movement (x and y never actually changes)
		if(up) offsetY -= currentSpeed;
		if(down) offsetY += currentSpeed;
		if(left) offsetX -= currentSpeed;
		if(right) offsetX += currentSpeed;
		
		// Rotating player to look at the mouse
		int mouseX = container.getInput().getAbsoluteMouseX();
		int mouseY = container.getInput().getAbsoluteMouseY();
		World.rotateImageTo(image, this.getCenterX(), this.getCenterY(), mouseX, mouseY);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(image, this.x, this.y);
	}
	
	@Override
	public void handleCollision(Entity entity) {
		
	}
	
	public boolean isMultiKey(boolean up, boolean down, boolean left, boolean right) {
		return (up && left) || (up && right) || (down && left) || (down && right);
	}
	
	public void clearOffset() {
		offsetX = offsetY = 0;
	}
	
	public int getOffsetX() {
		return offsetX;
	}
	
	public int getOffsetY() {
		return offsetY;
	}
}
