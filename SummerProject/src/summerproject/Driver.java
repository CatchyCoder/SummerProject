package summerproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Driver {
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	
	public static void main(String[] args) {
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
		
		if(isJar) {
			// Setting the library path if running in a jar, the path is based off the OS
			System.setProperty("org.lwjgl.librarypath", nativePath);
			log.debug("Executable jar detected.");
		}
		else log.debug("IDE detected.");
		
		// Creating the game component
		try {
			AppGameContainer gameContainer = new AppGameContainer(new Game("Clay's Game"));
			gameContainer.setDisplayMode(640, 480, false);
			gameContainer.setIcon("res/icons/icon_32.png");
			gameContainer.start();
			
		} catch(SlickException e) {
			log.error(e, e);
			log.debug("an exception should have been thrown");
		}
	}
}
