package fr.openig.views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.font.BitmapFont;
import com.golden.gamedev.util.ImageUtil;

import fr.openig.object.Planet;
import fr.openig.object.background.SpacemapBackground;
import fr.openig.object.generic.OpenIgGameObject;

public class StarMapView extends OpenIgGameObject {

	/** Logger **/
	private static Logger logger = Logger.getLogger(OpenIgGameObject.class);
	
	// Conteneur
	private PlayField playfield;
	
	// Font en taille 10
	private GameFont font10;

	// Argent
	private long money;
	
	// Date
	private Calendar calendar = new GregorianCalendar();

	public StarMapView(GameEngine parent) {
		super(parent);
	}

	@Override
	public void initResources() {
		// Variable du jeu
		money = 0;
		calendar.setTime(new Date());
		//setMaskColor(Color.BLACK);
		
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
		
		// Chargement de la font
		BufferedImage fontImage = super.getImage("graphics/sprites/generics/font/charset.png");
		font10 = fontManager.getFont(ImageUtil.splitImages(fontImage.getSubimage(0, 24, 252, 32), 42, 4),
		        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnop" +
		        "qrstuvwxyz?![]'\"+-:;.,1234567890%& /ß ÄÖÜä" +
		 		"öüßêéèàEÃçÇôûùòìàóñÑµ¿úííóõôôüüÜ#@*<>_$");
		
		// Musique de fond
		bsMusic.play("music/MS_Ambient 01.mp2");
		bsMusic.setLoop(false);
	}
	
	@Override
	public void render(Graphics2D g2d) {
		playfield.render(g2d);
		
		// Affichage de la barre principale
		getSprite(getSubImage(super.getImage("graphics/sprites/starmap/main-bar.png", false), 77, 20, 79, 18), 0, 0).render(g2d);
		getSprite(getSubImage(super.getImage("graphics/sprites/starmap/main-bar.png", false), 1, 1, 524, 18), 74, 0).render(g2d);
		getSprite(getSubImage(super.getImage("graphics/sprites/starmap/main-bar.png", false), 0, 40, 42, 18), 600, 0).render(g2d);
		
		// Affichage de l'argent
		font10.drawString(g2d, "$" + money, 94, 6);
		
		// Affichage de la date
		font10.drawString(g2d, calendar.get(Calendar.DAY_OF_MONTH) + " / " + calendar.get(Calendar.MONTH) + " / " + calendar.get(Calendar.YEAR), BitmapFont.CENTER, 170, 6, 90);
		
		// Affichage des planètes
		for(int i=0 ; i < playfield.getGroup("planets").getSize() ; i ++) {
			Planet planet = (Planet) playfield.getGroup("planets").getSprites()[i];
			if(planet.isSelected()) {
				
				// Dessin de la selection
				g2d.setColor(Color.YELLOW);
				g2d.drawRect((int)planet.getX() + (planet.getWidth() / 3), (int)planet.getY() + (planet.getHeight() / 3), planet.getWidth() / 3, planet.getHeight() / 3);
				
				// Affichage des données de la planètes
				g2d.setColor(Color.BLUE);
				//g2d.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
			}
		}
		
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
		// Souris
		if(click()) {
			if(logger.isDebugEnabled())
				logger.debug("Clique gauche @ x = " + getMouseX() + " and y = " + getMouseY());
			
			for(int i=0 ; i < playfield.getGroup("planets").getSize() ; i ++) {
				Planet planet = (Planet) playfield.getGroup("planets").getSprites()[i];
				if(checkPosMouse(planet, true))
					planet.setSelected(true);
				else
					planet.setSelected(false);
			}
		}
		
		
		// Gestion de l'argent
		money++;
		
		// Gestion de la date
		calendar.add(Calendar.MINUTE, (int)elapsedTime);
		
		// Gestion de la carte
		playfield.getBackground().setLocation(playfield.getBackground().getX() + starMapMove.getX(), playfield.getBackground().getY() + starMapMove.getY());
		
		// Gestion de la musique
		System.out.println(bsMusic.);
	}

}
