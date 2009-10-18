package fr.openig.graphics;

import java.awt.Color;

import org.apache.log4j.Logger;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;

public class OpenIgBaseLoader extends BaseLoader {

	/** Logger **/
	private static Logger logger = Logger.getLogger(OpenIgBaseLoader.class);
	
	/** Instance **/
	public static OpenIgBaseLoader instance;
	
	public synchronized static OpenIgBaseLoader getInstance(BaseIO baseIO, Color color) {
		if(instance == null)
			instance = new OpenIgBaseLoader(baseIO, color);
		
		return instance;
	}
	
	public synchronized static OpenIgBaseLoader getInstance() {
		if(instance == null)
			throw new RuntimeException("OpenIgBaseLoader is null");
		
		return instance;
	}
	
	private OpenIgBaseLoader(BaseIO baseIO, Color color) {
		super(baseIO, color);
		
		if(logger.isDebugEnabled())
			logger.debug("Début du chargement des données...");
		
		if(logger.isDebugEnabled())
			logger.debug("Chargement de la bitmap de fond");
		getImage("graphics/starmap.png");
		
		if(logger.isDebugEnabled())
			logger.debug("Chargement du sprite de la barre de contrôle principale");
		getImage("graphics/sprites/starmap/main-bar.png");
		
		if(logger.isDebugEnabled())
			logger.debug("Chargement des sprites des planètes");
		getImage("graphics/sprites/planets/n1.png");
		getImage("graphics/sprites/planets/n2.png");
		getImage("graphics/sprites/planets/n3.png");
		getImage("graphics/sprites/planets/n4.png");
		getImage("graphics/sprites/planets/n5.png");
		getImage("graphics/sprites/planets/n6.png");
		getImage("graphics/sprites/planets/n7.png");
		getImage("graphics/sprites/planets/n8.png");
		
		if(logger.isDebugEnabled())
			logger.debug("Chargement du sprite de la mini map");
		getImage("graphics/sprites/starmap/mini-map.png");
		
		if(logger.isDebugEnabled())
			logger.debug("Chargement du sprite des fonts");
		getImage("graphics/sprites/generics/font/charset.png");
		
		if(logger.isDebugEnabled())
			logger.debug("Chargement des sprites de la souris");
		getImages("graphics/sprites/generics/mouse/mouse-selection.png", 1, 40);
		
		if(logger.isDebugEnabled())
			logger.debug("Fin du chargement des données.");
	}
	
}
