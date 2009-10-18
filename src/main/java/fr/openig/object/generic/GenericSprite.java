package fr.openig.object.generic;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class GenericSprite extends Sprite {

	private String spriteLabel;
	private boolean selected;
	
	public GenericSprite(String spriteLabel, BufferedImage bufferedImage, int x, int y) {
		super(bufferedImage, x, y);
		setSpriteLabel(spriteLabel);
	}

	public String getSpriteLabel() {
		return spriteLabel;
	}

	public void setSpriteLabel(String spriteLabel) {
		this.spriteLabel = spriteLabel;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
