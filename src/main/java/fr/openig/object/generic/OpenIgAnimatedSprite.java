package fr.openig.object.generic;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.AnimatedSprite;

@SuppressWarnings("serial")
public class OpenIgAnimatedSprite extends AnimatedSprite {
	

	public OpenIgAnimatedSprite(BufferedImage[] bufferedImages, int x, int y) {
		super(bufferedImages, x, y);
		
		setAnimate(true);
		setLoopAnim(true);
	}
	
	

}
