package fr.openig;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.engine.input.AWTInput;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import fr.openig.object.SpaceShip;
import fr.openig.object.background.SpacemapBackground;

public class CopyOfOpenIgCOPY extends Game implements MouseWheelListener {
	
	/** Logger **/
	private static Logger logger = Logger.getLogger(CopyOfOpenIgCOPY.class);
	
	private PlayField playfield;

	// =========================================================================
	// Game skeleton
	// =========================================================================
	
	@Override
	public void initResources() {
		// Variable globale
		setMaskColor(Color.BLACK);
		((AWTInput) bsInput).getComponent().addMouseWheelListener(this);
		
		// Background
		BufferedImage backgroundImage = getImage("graphics/starmap.png");
		Background background = new SpacemapBackground(backgroundImage, backgroundImage.getWidth(), 662);
		playfield = new PlayField(background);
		
		// Chargement des sprites des planètes
		//SpriteGroup planets = playfield.addGroup(new SpriteGroup("Planet"));
		//planets.add(new Planet("Planète 1", getImages("graphics/sprites/planets/planet.png", 1, 64, true), 100, 100));
		
		// Chargement des sprites des vaisseaux
		/*SpriteGroup spaceShips = playfield.addGroup(new SpriteGroup("Space Ship"));
		spaceShips.add(new SpaceShip("Human fleet", getImages("graphics/sprites/spaceship/spaceship-human.png", 1, 48, true), 350, 200));
		spaceShips.add(new SpaceShip("Vulcan fleet", getImages("graphics/sprites/spaceship/spaceship-vulcan.png", 1, 48, true), 250, 100));
		*/
		// Chargement des sprites du pointeur de la souris
		SpriteGroup mouse = playfield.addGroup(new SpriteGroup("Mouse"));
		mouse.add(new Sprite(getImage("graphics/sprites/generics/mouse/mouse-selection.png", true), 150, 100));
		mouse.getActiveSprite().setActive(false);
	}

	@Override
	public void render(Graphics2D g) {
		playfield.render(g);
		
		SpriteGroup spaceShipGroup = playfield.getGroup("Space Ship");
		for(int i = 0 ; i < spaceShipGroup.getSize() ; i ++) {
			SpaceShip spaceShip = (SpaceShip)spaceShipGroup.getSprites()[i];
			if(spaceShip.isSelected()) {
				// Dessin du cadre autour d'un vaisseau selectionné
				Sprite mouse = playfield.getGroup("Mouse").getSprites()[0];
				mouse.setLocation(spaceShip.getX(), spaceShip.getY());
				mouse.setActive(true);
				
				if(spaceShip.getStatus() == SpaceShip.MOVING) {
					// Dessin de la flèche de mouvement
					g.setColor(Color.YELLOW);
					g.drawLine((int)spaceShip.getX() + spaceShip.getWidth() / 2
							, (int)spaceShip.getY() + spaceShip.getHeight() / 2
							, spaceShip.getDestX(), spaceShip.getDestY());
				}
			} else {
				if(spaceShip.getStatus() == SpaceShip.MOVING) {
					Sprite mouse = playfield.getGroup("Mouse").getSprites()[0];
					mouse.setLocation(spaceShip.getX(), spaceShip.getY());
					mouse.setActive(false);
				}
			}
		}
		
	}

	@Override
	public void update(long elapsedTime) {
		if(click()) {
			if(logger.isDebugEnabled())
				logger.debug("Clique gauche @ x = " + getMouseX() + " and y = " + getMouseY());
			
			SpriteGroup spaceShipGroup = playfield.getGroup("Space Ship");
			for(int i = 0 ; i < spaceShipGroup.getSize() ; i ++) {
				SpaceShip spaceShip = (SpaceShip)spaceShipGroup.getSprites()[i];
				if(checkPosMouse(spaceShip, true))
					spaceShip.setSelected(true);
				else
					spaceShip.setSelected(false);
			}
		}
		
		if(rightClick()) {
			if(logger.isDebugEnabled())
				logger.debug("Clique droit @ x = " + getMouseX() + " and y = " + getMouseY());
			
			SpriteGroup spaceShipGroup = playfield.getGroup("Space Ship");
			for(int i = 0 ; i < spaceShipGroup.getSize() ; i ++) {
				SpaceShip spaceShip = (SpaceShip)spaceShipGroup.getSprites()[i];
				if(spaceShip.isSelected()) {
					if(!spaceShip.moveTo(elapsedTime, getMouseX(), getMouseY(), 0.1d)) {
						spaceShip.setDestX(getMouseX() + (int)playfield.getBackground().getX());
						spaceShip.setDestY(getMouseY() + (int)playfield.getBackground().getX());
					}
				}
			}
		}
		
		SpriteGroup spaceShipGroup = playfield.getGroup("Space Ship");
		for(int i = 0 ; i < spaceShipGroup.getSize() ; i ++) {
			SpaceShip spaceShip = (SpaceShip)spaceShipGroup.getSprites()[i];
			if(spaceShip.getStatus() == SpaceShip.MOVING) {
				spaceShip.moveTo(elapsedTime);
			}
		}
		
		if(keyDown(KeyEvent.VK_RIGHT)) {
			playfield.getBackground().move(1, 0);
			
		}
		
		if(keyDown(KeyEvent.VK_LEFT)) {
			playfield.getBackground().move(-1, 0);
		}
		
		if(keyDown(KeyEvent.VK_UP)) {
			playfield.getBackground().move(0, -1);
		}
		
		if(keyDown(KeyEvent.VK_DOWN)) {
			playfield.getBackground().move(0, 1);
		}
		
		playfield.update(elapsedTime);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println(e.getWheelRotation());
	}

	// =========================================================================
	// Starting point
	// =========================================================================
	public static void main(String[] args) throws IOException {
		GameLoader game = new GameLoader();
		game.setup(new CopyOfOpenIgCOPY(), new Dimension(640, 480), false);
		game.start();
	}

}