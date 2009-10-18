package fr.openig.object;

import java.awt.image.BufferedImage;

import fr.openig.object.generic.OpenIgAnimatedSprite;

@SuppressWarnings("serial")
public class Sun extends OpenIgAnimatedSprite {

	// Taille du soleil
	private int size;
	
	public Sun(BufferedImage[] bufferedImages, int x, int y, int size) {
		super(bufferedImages, x, y);
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
