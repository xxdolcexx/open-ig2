package fr.openig;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.OpenGLGameLoader;
import com.golden.gamedev.engine.audio.JavaLayerMp3Renderer;

import fr.openig.views.StarMapView;

public class OpenIg extends GameEngine {
	
	/** Logger **/
	private static Logger logger = Logger.getLogger(OpenIg.class);
	
	/** Id des différents screens */
	private static final int STARMAP = 0;
	
	@Override
	protected void initEngine() {
		super.initEngine();

		bsMusic.setBaseRenderer(new JavaLayerMp3Renderer());
	}

	@Override
	public GameObject getGame(int gameId) {
		 switch (gameId) {
         	case STARMAP:
         		return new StarMapView(this);
		 }
		 
		 return null;
	}
	
	public static void main(String[] args) {
		// Récupération du fichier de configuration
		// et des propriétés
		Properties properties = new Properties();
		try {
			if(logger.isDebugEnabled())
				logger.debug("Chargement du fichier de configuration : open-ig2.properties");
			properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("open-ig2.properties"));
		} catch (IOException e) {
			logger.warn("Impossible de charger le fichier de configuration : open-ig2.properties. Utilisation des valeurs par défaut!");
		}
		int width = Integer.parseInt(properties.getProperty("screen.resolution", "640x480").split("x")[0]);
		int height = Integer.parseInt(properties.getProperty("screen.resolution", "640x480").split("x")[1]);
		boolean fullscreen = Boolean.getBoolean(properties.getProperty("screen.fullscreen", "false"));
		
		// Lancement du jeu
		OpenGLGameLoader game = new OpenGLGameLoader();
		game.setup(new OpenIg(), new Dimension(width, height), fullscreen);
		game.start();
    }

}