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
	
	// Impot de la planète
	private int impot;

	// Doit-on afficher les actions
	private boolean actioned;
	
	public Planet(BufferedImage bufferedImage, int x, int y) {
		super(bufferedImage, x, y);
		
		size = 500;
		population = 15000;
		impot = 200;
		name = "Andor";
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
	
	public int getImpot() {
		return impot;
	}
	
	public void setImpot(int impot) {
		this.impot = impot;
	}
	
}
