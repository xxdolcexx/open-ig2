package fr.openig.engine;

import org.apache.log4j.Logger;

import fr.openig.model.Universe;
import fr.openig.utils.xml.OpenIgXmlParser;

public class UniverseEngine {

	/** Logger **/
	private static Logger logger = Logger.getLogger(UniverseEngine.class);
	
	// Instance de l'objet
	private static UniverseEngine instance;
	
	// Univers du jeu
	private Universe universe;
	
	public static synchronized UniverseEngine getInstance() {
		if(instance == null)
			instance =  new UniverseEngine();
		
		return instance;
	}
	
	private UniverseEngine() {
		init();
	}
	
	private void init() {
		if(logger.isInfoEnabled())
			logger.info("Chargement des données statiques");
		
		universe = OpenIgXmlParser.parse(this.getClass().getResourceAsStream("/data.xml"));
	}

	public Universe getUniverse() {
		return universe;
	}

	public void setUniverse(Universe universe) {
		this.universe = universe;
	}
	
}
