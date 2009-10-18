package fr.openig.object.generic;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class AlphaSprite extends Sprite {

	AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
			0.5f); // 0.5f -> half transparent

	public AlphaSprite(BufferedImage bufferedImage, int x, int y) {
		super(bufferedImage, x, y);
	}

	public void render(Graphics2D g) {
		Composite old = g.getComposite();
		g.setComposite(alpha);
		super.render(g);
		g.setComposite(old);
	}

}
