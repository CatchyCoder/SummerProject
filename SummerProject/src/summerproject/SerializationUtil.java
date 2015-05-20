package summerproject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SerializationUtil {
	
	private static final Logger log = LogManager.getLogger(SerializationUtil.class);

	/**
     * Deserialize to Object from given file. We use the general Object so as
     * that it can work for any Java Class.
     */
    public static Object load(String fileName) {
    	try {
    		FileInputStream fis = new FileInputStream(fileName);
    		BufferedInputStream bis = new BufferedInputStream(fis);
    		ObjectInputStream ois = new ObjectInputStream(bis);
    		Object obj = ois.readObject();
    		ois.close();
    		
    		log.debug("Successfully loaded from \"" + fileName + "\".");
    		return obj;
    	} catch(InvalidClassException e) {
    		// Load failed because the save file is not compatible with the current version
			log.error("Save file \"" + fileName + "\" is not compatible with current game version.", e);
    	} catch(Exception e) {
    		log.error("Failed to load from \"" + fileName + "\"", e);
    	}
    	
		return null;
    }
 
    /**
     * Serialize the given object and save it to a given file.
     */
    public static void save(Object obj, String fileName) {
    	try {
    		FileOutputStream fos = new FileOutputStream(fileName);
    		BufferedOutputStream bos = new BufferedOutputStream(fos);
    		ObjectOutputStream oos = new ObjectOutputStream(bos);
    		oos.writeObject(obj);
    		oos.close();
    		
    		log.debug("Successfully saved " + obj.getClass().getName() + " to \"" + fileName + "\".");
    	} catch(IOException e) {
    		log.error("Failed to save to \"" + fileName + "\".\n", e);
    	}
    }
}
