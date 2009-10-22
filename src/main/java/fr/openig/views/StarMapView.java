package fr.openig.views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.ImageUtil;

import fr.openig.object.Planet;
import fr.openig.object.background.SpacemapBackground;
import fr.openig.object.generic.ControlBarGameObject;
import fr.openig.object.generic.OpenIgGameObject;

public class StarMapView extends OpenIgGameObject {

	/** Logger **/
	private static Logger logger = Logger.getLogger(OpenIgGameObject.class);
	
	// Conteneur
	private PlayField playfield;
	
	// Barre de contrôle principale
	private ControlBarGameObject controlBar;

	public StarMapView(GameEngine parent) {
		super(parent);
		
		// Barre de contrôle principale
		controlBar = new ControlBarGameObject(parent);
	}

	@Override
	public void initResources() {
		setMaskColor(Color.BLACK);
		
		// Affichage du background
		BufferedImage backgroundImage = super.getImage("graphics/starmap.png", false);
		Background background = new SpacemapBackground(backgroundImage, bsInput);
		playfield = new PlayField(background);
		
		// Affichage des planètes
		SpriteGroup planets = playfield.addGroup(new SpriteGroup("planets"));
		planets.add(new Planet(super.getImageWithTransparency("graphics/sprites/planets/n1.png", Transparency.TRANSLUCENT, 10), 90, 90));
		
		// Affichage de la minimap
		/*SpriteGroup miniMap = playfield.addGroup(new SpriteGroup("miniMap"));
		BufferedImage miniMapImage = getImage("graphics/sprites/starmap/mini-map.png");
		miniMap.add(new Sprite(miniMapImage, (getWidth() / 2) - (miniMapImage.getWidth() / 2), 
				getHeight() - miniMapImage.getHeight() - 2));*/
		
		// Vaisseaux
		SpriteGroup spaceShips = playfield.addGroup(new SpriteGroup("spacehips"));
		BufferedImage image = getImage("graphics/sprites/spaceship/spaceship.png", true).getSubimage(0, 0, 30, 1056);
		AnimatedSprite spaceShip = getAnimatedSprite(ImageUtil.splitImages(image, 1, 48), 20, 20);
		spaceShip.setAnimate(true);
		spaceShip.setLoopAnim(true);
		spaceShips.add(spaceShip);
		
		// Musique de fond
		for(int i=1 ; i <= 14 ; i++)
			bsMusic.addMusic("music/ambient/MS_Ambient " + new DecimalFormat("00").format(i) + ".mp2");
		bsMusic.play();
	}
	
	@Override
	public void render(Graphics2D g2d) {
		playfield.render(g2d);
		
		// Affichage des planètes
		for(int i=0 ; i < playfield.getGroup("planets").getSize() ; i ++) {
			Planet planet = (Planet) playfield.getGroup("planets").getSprites()[i];
			if(planet.isSelected()) {
				
				// Dessin de la selection
				g2d.setColor(Color.YELLOW);
				g2d.drawRect((int)planet.getScreenX() + (planet.getWidth() / 3), (int)planet.getScreenY() + (planet.getHeight() / 3), planet.getWidth() / 3, planet.getHeight() / 3);
				
				// Affichage des données de la planètes
				g2d.setColor(new Color(0, 150, 255, 120));
				g2d.fillRoundRect(getWidth() - 200, getHeight() - 80, 190, 70, 10, 10);
				font10.drawString(g2d, planet.getName(), getWidth() - 190, getHeight() - 70);
				font10.drawString(g2d, "Taille : " + planet.getSize(), getWidth() - 190, getHeight() - 60);
				font10.drawString(g2d, "Population : " + planet.getPopulation(), getWidth() - 190, getHeight() - 50);
				font10.drawString(g2d, "Impôt : " + planet.getImpot(), getWidth() - 190, getHeight() - 40);
				
				if(planet.isActioned()) {
					getSprite(getImage("graphics/sprites/generics/planet/star-ico.png", true).getSubimage(49 * 7, 0, 49, 49), 
							(int)planet.getScreenX() + (planet.getWidth() / 3) - 3, 
							(int)planet.getScreenY() - 10
							).render(g2d);
					getSprite(getImage("graphics/sprites/generics/planet/star-ico.png", true).getSubimage(49 * 8, 0, 49, 49), 
							(int)planet.getScreenX() + (planet.getWidth() / 3) + (planet.getWidth() / 3) + 3, 
							(int)planet.getScreenY() + (planet.getHeight() / 3) - 3
							).render(g2d);
				}
			}
		}
		
		controlBar.render(g2d);
		
		// Affichage mode debug
		if(isDebugMode()) {
			font10.drawString(g2d, "Mouse x:" + getMouseX() + " & y:" + getMouseY(), 5, getHeight() - 50);
			font10.drawString(g2d, "Background x:" + playfield.getBackground().getX() + " & y:" + playfield.getBackground().getY(), 5, getHeight() - 40);
		}
	}

	@Override
	public void update(long elapsedTime) {
		Point2D starMapMove = new Point();
		
		playfield.update(elapsedTime);
		
		// Récupération des input utilisateur
		// Clavier
		if(keyDown(KeyEvent.VK_RIGHT)) {
			starMapMove.setLocation(3, 0);
		}
		if(keyDown(KeyEvent.VK_LEFT)) {
			starMapMove.setLocation(-3, 0);
		}
		if(keyDown(KeyEvent.VK_DOWN)) {
			starMapMove.setLocation(0, 3);
		}
		if(keyDown(KeyEvent.VK_UP)) {
			starMapMove.setLocation(0, -3);
		}
		// Souris (click gauche)
		if(click()) {
			if(logger.isDebugEnabled())
				logger.debug("Clique gauche @ x = " + getMouseX() + " and y = " + getMouseY());
			
			for(int i=0 ; i < playfield.getGroup("planets").getSize() ; i ++) {
				Planet planet = (Planet) playfield.getGroup("planets").getSprites()[i];
				if(checkPosMouse(planet, true))
					planet.setSelected(true);
				else {
					planet.setSelected(false);
					planet.setActioned(false);
				}
			}
		}
		// Souris (click droit)
		if(rightClick()) {
			if(logger.isDebugEnabled())
				logger.debug("Clique droit @ x = " + getMouseX() + " and y = " + getMouseY());
			for(int i=0 ; i < playfield.getGroup("planets").getSize() ; i ++) {
				
				Planet planet = (Planet) playfield.getGroup("planets").getSprites()[i];
				if(checkPosMouse(planet, true) && planet.isSelected())
					planet.setActioned(true);
				else
					planet.setActioned(false);
			}
		}
		
		controlBar.update(elapsedTime);
		
		// Gestion de la carte
		playfield.getBackground().setLocation(playfield.getBackground().getX() + starMapMove.getX(), playfield.getBackground().getY() + starMapMove.getY());
	}

}
