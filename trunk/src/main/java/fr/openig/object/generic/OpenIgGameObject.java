package fr.openig.object.generic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;

public abstract class OpenIgGameObject extends GameObject {

	// Mode debug ou non
	private boolean debugMode = false;
	
	// Font
	protected GameFont font10;
	
	public OpenIgGameObject(GameEngine parent) {
		super(parent);
		
		if(!parent.isDistribute())
			debugMode = true;
		
		// Chargement de la font
		BufferedImage fontImage = super.getImage("graphics/sprites/generics/font/charset.png");
		font10 = fontManager.getFont(ImageUtil.splitImages(fontImage.getSubimage(0, 24, 252, 32), 42, 4),
		        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnop" +
		        "qrstuvwxyz?![]'\"+-:;.,1234567890%& /ß ÄÖÜä" +
		 		"öüßêéèàEÃçÇôûùòìàóñÑµ¿úííóõôôüüÜ#@*<>_$");
	}
	
	@Override
	public abstract void initResources();

	@Override
	public abstract void render(Graphics2D g2d);

	@Override
	public abstract void update(long elapsedTime);
	
	public BufferedImage getImageWithTransparency(String image, int transparency, int transparencyLevel){
		BufferedImage img = getImage(image);
		
	     if(transparency == Transparency.OPAQUE){
	          return img;
	     }
	     
	     //Bitmask, or translucent
	     int w = img.getWidth();
	     int h = img.getHeight();
	     
	     for(int y =0 ; y<h ; y++){
	    	 for(int x=0 ; x<w ; x++){
	    		 Color color = new Color(img.getRGB(x,y));
	    		 if(((color.getRed() + color.getGreen() + color.getBlue()) / 3) <= transparencyLevel)
	    			 img.setRGB(x, y, 0x00ffffff);
	    	 }
	     }
	     
	     return img;
	}
	
	public Sprite getSprite(BufferedImage image, double x, double y) {
		return new Sprite(image, x, y);
	}
	
	public AnimatedSprite getAnimatedSprite(BufferedImage[] images, int x, int y) {
		return new AnimatedSprite(images, x, y);
	}
	
	public BufferedImage getSubImage(BufferedImage image, int x, int y, int w, int h) {
		return image.getSubimage(x, y, w, h);
	}
	
	public boolean isDebugMode() {
		return debugMode;
	}

}
