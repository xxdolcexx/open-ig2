package fr.openig.object.generic;

import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.Date;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.font.BitmapFont;

import fr.openig.engine.OpenIgTimer;

public class ControlBarGameObject extends OpenIgGameObject {

	// Les différents status de la barre de control
	public final static int PAUSE = 0;
	public final static int PLAY = 1;
	public final static int FORWARD = 2;
	public final static int FAST_FORWARD = 3;
	
	// Status de la barre de controle
	// Par défaut, en lecture
	private int status = PLAY;
	
	// Argent
	private long money;
	
	// Timer du jeu
	private OpenIgTimer openIgTimer;
	
	// Conteneur
	private PlayField playfield;
	
	public ControlBarGameObject(GameEngine parent) {
		super(parent);
		
		initResources();
	}

	@Override
	public void initResources() {
		// Variable du jeu
		// Argent
		money = 0;
		// Timer
		openIgTimer = OpenIgTimer.getInstance(new Date());
		openIgTimer.start();
		
		playfield = new PlayField();
		
		SpriteGroup controlBarSpriteGroup = playfield.addGroup(new SpriteGroup("controlBar"));
		controlBarSpriteGroup.add(getSprite(getImage("graphics/sprites/starmap/main-bar.png", false).getSubimage(77, 20, 79, 18), 0, 0));
		controlBarSpriteGroup.add(getSprite(getImage("graphics/sprites/starmap/main-bar.png", false).getSubimage(1, 1, 524, 18), 74, 0));
		controlBarSpriteGroup.add(getSprite(getImage("graphics/sprites/starmap/main-bar.png", false).getSubimage(0, 40, 42, 18), 600, 0));
	}

	@Override
	public void render(Graphics2D g2d) {
		// Affichage de la barre principale
		playfield.render(g2d);
		
		switch (status) {
			case PAUSE:
				getSprite(getImage("graphics/sprites/starmap/main-bar.png", false).getSubimage(0, 20, 14, 18), 0, 0).render(g2d);
				break;
			case PLAY:
				getSprite(getImage("graphics/sprites/starmap/main-bar.png", false).getSubimage(15, 20, 14, 18), 14, 0).render(g2d);
				break;
			case FORWARD:
				getSprite(getImage("graphics/sprites/starmap/main-bar.png", false).getSubimage(30, 20, 22, 18), 29, 0).render(g2d);
				break;
			case FAST_FORWARD:
				getSprite(getImage("graphics/sprites/starmap/main-bar.png", false).getSubimage(50, 20, 24, 18), 49, 0).render(g2d);
				break;
		}
		
		// Affichage de l'argent
		font10.drawString(g2d, "$" + money, 94, 6);
		
		// Affichage de la date
		font10.drawString(g2d, new DecimalFormat("00").format(openIgTimer.getDayOfMonth()) + " / " + new DecimalFormat("00").format(openIgTimer.getMonth()) + " / " + new DecimalFormat("0000").format(openIgTimer.getYear()), BitmapFont.CENTER, 170, 6, 90);
	}

	@Override
	public void update(long elapsedTime) {
		if(click() && checkPosMouse(playfield.getGroup("controlBar").getSprites()[0], true)) {
			if(getMouseX() <= 10 && getMouseX() >=4) {
				status = PAUSE;
				openIgTimer.pause();
			}
			
			if(getMouseX() <= 24 && getMouseX() >=16) {
				status = PLAY;
				openIgTimer.setSpeed(PLAY);
			}
			
			if(getMouseX() <= 43 && getMouseX() >=32) {
				status = FORWARD;
				openIgTimer.setSpeed(FORWARD);
			}
			
			if(getMouseX() <= 68 && getMouseX() >=53) {
				status = FAST_FORWARD;
				openIgTimer.setSpeed(FAST_FORWARD);
			}
		}
		
		// Gestion de l'argent
		calculateMoney();
	}
	
	private void calculateMoney() {
		if(status == PAUSE)
			return;
		
		money++;
	}

}
