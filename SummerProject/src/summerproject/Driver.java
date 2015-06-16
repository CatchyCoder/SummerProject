package summerproject;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Driver {
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	
	public static void main(String[] args) {
		log.debug("\n\nNew session started.");
		
		// Detecting operating system
		final String OS = System.getProperty("os.name").toLowerCase();
		String nativePath = "native";
		if(OS.contains("windows")) nativePath += "/windows";
		else if(OS.contains("mac")) nativePath += "/macosx";
		else if(OS.contains("linux")) nativePath += "/linux";
		else if(OS.contains("solaris")) nativePath += "/solaris";
		else {
			// OS not supported
			log.error("Sorry, your operating system, " + OS + ", is not supported. Exiting application.");
			System.exit(1);
		}
		
		log.debug(OS + " detected.");
		// Detecting if running in an executable jar, or running in an IDE
		boolean isJar = Driver.class.getResource("Driver.class").toString().toLowerCase().startsWith("jar");
		
		// Setting the library path if running in a jar, the path is based off the OS
		if(isJar) {
			log.debug("Executable jar detected.");
			
			// Checking if the "native" folder exists
			File nativeFolder = new File(nativePath);
			if(nativeFolder.exists()) {
				System.setProperty("org.lwjgl.librarypath", nativeFolder.getAbsolutePath());
			} else {
				log.error("Could not find native folder. Exiting application.");
				System.exit(2);
			}
			
			// Checking to make sure the "res" folder exists
			File resFolder = new File("res");
			if(!resFolder.exists()) {
				log.error("Could not find resource folder. Exiting applictation");
				System.exit(3);
			}
		}
		else log.debug("IDE detected.");
		
		// Creating the game component
		try {
			Game game = new Game("Unknown Game");
			AppGameContainer gameContainer = new AppGameContainer(game);
			gameContainer.setDisplayMode(720, 720, false);
			gameContainer.setIcon("res/icons/icon_32.png");
			gameContainer.setVerbose(false);
			gameContainer.start();
			
		} catch(SlickException e) {
			log.error("Error creating game. An exception should have been thrown.", e);
		}		
	}
}
