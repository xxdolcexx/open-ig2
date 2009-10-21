package fr.openig.object;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class Planet extends Sprite {

	// Nom de la planète
	private String name;
	
	// Taille de la planete
	private int size;
	
	// La planète est-elle selectionnée
	private boolean selected;

	// Population de la planète
	private int population;

	// Doit-on afficher les actions
	private boolean actioned;
	
	public Planet(String name, BufferedImage bufferedImage, int x, int y, int size) {
		super(bufferedImage, x, y);
		
		this.size = size;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}

	public void setActioned(boolean actioned) {
		this.actioned = actioned;
	}
	
	public boolean isActioned() {
		return actioned;
	}
	
}
