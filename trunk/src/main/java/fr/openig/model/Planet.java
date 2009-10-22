package fr.openig.model;

import com.golden.gamedev.object.Sprite;

public class Planet {

	// Nom de la planète
	private String name;
	
	// Coordonnée de la planète
	private double x, y;
	
	// Type de la planète
	private int type;

	// Population de la planète
	private long population;
	
	// Satisfaction
	private float satisfaction;
	
	// Impot
	private long impot;
	
	// Sprite de la planète
	private Sprite sprite;
	
	// La planète est-elle selectionnée
	private boolean isSelected;
	
	// Doit-on afficher les actions de la planètes
	private boolean actioned;

	public float getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(float satisfaction) {
		this.satisfaction = satisfaction;
	}

	public long getImpot() {
		return impot;
	}

	public void setImpot(long impot) {
		this.impot = impot;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}
	
	public boolean isActioned() {
		return actioned;
	}

	public void setActioned(boolean actioned) {
		this.actioned = actioned;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
}
