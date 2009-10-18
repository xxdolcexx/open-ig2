package fr.openig.engine;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.golden.gamedev.GameEngine;

import fr.openig.graphics.OpenIgBaseLoader;
import fr.openig.object.Galaxy;
import fr.openig.object.Planet;
import fr.openig.object.Sun;

public class StarMapEngine {
	
	/** Logger **/
	private static Logger logger = Logger.getLogger(OpenIgBaseLoader.class);
	
	/** Instance **/
	private static StarMapEngine instance;
	
	// List des différentes galaxies
	private List<Galaxy> galaxies = new ArrayList<Galaxy>();
	
	// Résolution d'affichage
	protected int height;
	protected int width;
	
	// Generateur aléatoire
	private Random random = new Random();
	
	public static synchronized StarMapEngine getInstance(GameEngine gameEngine) {
		if(instance == null)
			instance = new StarMapEngine(gameEngine);
		
		return instance;
	}

	private StarMapEngine(GameEngine gameEngine) {
		width = gameEngine.getWidth();
		height = gameEngine.getHeight();
	}
	
	public void init() {
		Galaxy galaxy = generateGalaxy();
		galaxies.add(galaxy);
	}
	
	private Galaxy generateGalaxy() {
		Galaxy galaxy = new Galaxy();
		
		// Génération du soleil
		Sun sun = generateSun();
		galaxy.setSun(sun);
		
		// Génération des planètes
		// On détermine d'abord un nombre aléatoire pour les planètes du système
		int nbPlanets = 1 + random.nextInt(sun.getSize() / 4);
		for(int i = 0 ; i < nbPlanets ; i++) {
			galaxy.addPlanet(generatePlanet(sun));
		}
		
		return galaxy;
	}
	
	private Planet generatePlanet(Sun sun) {
		// Génération des coordonnées
		int sunX = (int)sun.getX() + random.nextInt(200);
		int sunY = (int)sun.getY() + random.nextInt(200);
		
		// Génération de la taille
		int allSize[] = { 9, 10, 12, 13, 15, 17, 20, 24, 30 };
		int sizeMax = sun.getSize();
		int size = 0;
		for(int i = 0 ; i < allSize.length ; i++) {
			if(allSize[i] == sizeMax)
				size = allSize[0 + random.nextInt(i)];
		}
		
		// Génération du type de planète
		String types[] = { "alien", "cratered", "desert", "earth", "ice", "liquid", "rocky" } ;
		String type = types[0 + random.nextInt(7)];
		
		if(logger.isDebugEnabled())
			logger.debug("Planète à x:" + sunX + " & y:" + sunY + " de taille de : " + size + " et de type " + type);
		
		// Récupération de l'image
		StringBuffer image = new StringBuffer("graphics/sprites/planets/planet_");
		image.append(type).append("_");
		image.append(size).append("x").append(size).append(".png");
		BufferedImage[] bufferedImages = OpenIgBaseLoader.getInstance().getStoredImages("graphics/sprites/planets/n1.png");
		return new Planet(bufferedImages, sunX, sunY);
	}
	
	private Sun generateSun() {
		// Génération des coordonnées
		int sunX = 20 + random.nextInt(width - 20 - 20);
		int sunY = 20 + random.nextInt(height - 20 - 20);

		// Génération de la taille
		int allSize[] = { 9, 10, 12, 13, 15, 17, 20, 24, 30 };
		int size = allSize[0 + random.nextInt(9)];
		
		if(logger.isDebugEnabled())
			logger.debug("Soleil à x:" + sunX + " & y:" + sunY + " et taille de : " + size);
		
		// Récupération de l'image
		StringBuffer image = new StringBuffer("graphics/sprites/planets/planet_alien_");
		image.append(size).append("x").append(size).append(".png");
		BufferedImage[] bufferedImages = OpenIgBaseLoader.getInstance().getStoredImages(image.toString());
		return new Sun(bufferedImages, sunX, sunY, size);
	}

	public List<Galaxy> getGalaxies() {
		return galaxies;
	}

	public void setGalaxies(List<Galaxy> galaxies) {
		this.galaxies = galaxies;
	}
	
}
