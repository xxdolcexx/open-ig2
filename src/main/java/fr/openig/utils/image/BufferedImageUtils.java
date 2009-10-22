package fr.openig.utils.image;

import java.awt.Color;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class BufferedImageUtils {

	public static BufferedImage getImageWithTransparency(BufferedImage image, int transparency, int transparencyLevel) {
		if (transparency == Transparency.OPAQUE) {
			return image;
		}

		// Bitmask, or translucent
		int w = image.getWidth();
		int h = image.getHeight();

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				Color color = new Color(image.getRGB(x, y));
				if (((color.getRed() + color.getGreen() + color.getBlue()) / 3) <= transparencyLevel)
					image.setRGB(x, y, 0x00ffffff);
			}
		}

		return image;
	}
	
	

}
