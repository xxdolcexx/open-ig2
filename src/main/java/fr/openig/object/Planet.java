package fr.openig.object;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class Planet extends Sprite {

	// Taille de la planete
	private int size;
	
	// La planète est-elle selectionnée
	private boolean selected;
	
	public Planet(BufferedImage bufferedImage, int x, int y) {
		super(bufferedImage, x, y);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
