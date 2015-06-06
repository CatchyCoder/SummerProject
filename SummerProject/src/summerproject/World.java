package summerproject;


import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class World implements Entity, Serializable {
	
	/*
	 * TODO:
	 * 
	 * Don't render stuff off screen, use max and min x and y values to see if it gets on screen (use Shape max and min values)
	 * Maybe make a static method in world that takes in an image and another Shape and rotates the image at the shape.
	 * 
	 * Later:
	 * name world files based off of date, time, and world version (serialVersionUID)
	 * Don't ever get graphics contexts while in-game, this is resource intensive
	 * and should all be gathered before the game starts
	 */
	
	/**
	 * Version of the class, need to update this number if the class
	 * becomes incompatible with the earlier version. Serialization
	 * is used for saving the state of the game.
	 */
	private static final long serialVersionUID = 3L;
	
	private static final Logger log = LogManager.getLogger(World.class);
	
	private Image background; // Don't need an actual image to draw on since we are using slick2d, everything will just be its own separate image drawn in the container
	
	public Player player = new Player(0, 0);
		
	public World(final int width, final int height) {
		try {
			// Initializing the image
			background = new Image(width, height);
			Graphics g2 = background.getGraphics();
			g2.setBackground(new Color(255, 245, 179));
			g2.clear();
			
			// Drawing on the image
			g2.setColor(Color.green);
			g2.fillRect(0, 0, 100, 100);
			g2.flush();
		} catch (SlickException e) {
			log.error("Failed to create main World image.", e);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		player.update(container, game, delta);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {		
		g.drawImage(background, 0, 0);
		player.render(container, game, g);
	}
	
	public static void rotateImageTo(Image image, float x, float y, float x2, float y2) {
		double height = y2 - y;
		double width = x2 - x;
		// The images starting position is pointing north, therefore 90 degrees is added to account for that
		float angle = (float) Math.toDegrees(Math.atan(height / width)) + 90;
		if(x > x2) angle -= 180;
		image.setRotation(angle);
	}
	
	public static void rotateTo(Image image, float x, float y, Shape shape) {
		rotateImageTo(image, x, y, shape.getX(), shape.getY());
	}
	
	public static long getVersion() {
		return serialVersionUID;
	}
}
